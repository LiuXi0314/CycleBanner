package com.liuxi.recyclerloopdemo.lib

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View

/**
 * 防止卡片在第一页和最后一页因无法"居中"而一直循环调用onScrollStateChanged-->SnapHelper.snapToTargetExistingView-->onScrollStateChanged
 * Created by jameson on 9/3/16.
 */
class PagerSnapScaleHelper(
    private val scale: Float = 0.8f,
    private val paddingVertical: Float = 5f,
    private val paddingHorizontal: Float = 5f,
    private val edgeItemWidth: Float = 40f//两边漏出的宽度

) : PagerSnapHelper() {

    private lateinit var mContext: Context
    private lateinit var mLayoutManager: LinearLayoutManager

    private var mRecyclerWidth: Int = 0
    private var mChildWidth: Int = 0
    private var mRecyclerHeight: Int = 0
    private var mChildHeight: Int = 0
    private var mItemWidth: Int = 0
    private var mCurrentPos: Int = 0
    private var mCurrentDiff: Int = 0


    @Throws(IllegalStateException::class)
    override fun attachToRecyclerView(recyclerView: RecyclerView?) {
        super.attachToRecyclerView(recyclerView)
        if (recyclerView == null) return

        mContext = recyclerView.context
        mLayoutManager = LinearLayoutManager(recyclerView.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.addItemDecoration(Decoration(paddingHorizontal, paddingVertical))
        recyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                mCurrentDiff += dx
                calculateCurrentItemPos()
                scrolled(recyclerView, dx, dy)
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
//                d("state=$newState   mLayoutManager.findFirstCompletelyVisibleItemPosition()=${mLayoutManager.findFirstCompletelyVisibleItemPosition()}")
                when (newState) {
                    0 -> {
                        //滑动事件结束,记录当前Pos
                        var pos = mLayoutManager.findFirstCompletelyVisibleItemPosition()
                        if (pos > 0) {
                            mCurrentPos = pos
                        }
                    }
                }
            }
        })

        recyclerView.post {
            mRecyclerWidth = recyclerView.width
            mRecyclerHeight = recyclerView.height
            mItemWidth = mRecyclerWidth - dip2px(recyclerView.context, edgeItemWidth * 2 + paddingHorizontal * 4)
        }

    }

    private fun calculateCurrentItemPos() {
        if (mItemWidth <= 0) return
        var pageChanged = Math.abs(mCurrentDiff - mCurrentPos * mItemWidth) >= mItemWidth
        if (pageChanged) {
            mCurrentPos = mCurrentDiff / mItemWidth
        }
    }

    private fun scrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        d("dx=$dx  total width = $mRecyclerWidth    total height = $mRecyclerHeight   itemWidth=$mItemWidth  ")
        d("firstPos = ${mLayoutManager.findFirstVisibleItemPosition()}  lastPos = ${mLayoutManager.findLastVisibleItemPosition()}  current = ${mLayoutManager.findFirstCompletelyVisibleItemPosition()}   ")
        var maxDiff = dip2px(recyclerView.context, paddingHorizontal * 2) + mItemWidth
        var diff = Math.abs(mCurrentDiff - mCurrentPos * maxDiff)

        var percent = Math.max(diff / maxDiff * 1.0, 0.001)
        var leftView = when (mCurrentPos > 0) {
            true -> {
                mLayoutManager.findViewByPosition(mCurrentPos - 1)
            }
            false -> {
                null
            }
        }

        var currentView = mLayoutManager.findViewByPosition(mCurrentPos)
        var rightView = mLayoutManager.findViewByPosition(mCurrentPos + 1)

        currentView?.scaleY = ((scale - 1) * percent + 1).toFloat()

        leftView?.scaleY = (scale + (1 - scale) * percent).toFloat()

        rightView?.scaleY = (scale + (1 - scale) * percent).toFloat()


    }

    internal class Decoration(private val paddingHorizontal: Float, private val paddingVertical: Float) :
        RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.top = dip2px(view.context, paddingVertical)
            outRect.bottom = outRect.top
            outRect.left = dip2px(view.context, paddingHorizontal)
            outRect.right = outRect.left
        }
    }

    companion object {
        /**
         * dip转px
         */
        fun dip2px(context: Context, dpValue: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (dpValue * scale + 0.5f).toInt()
        }
    }

}

fun d(string: String) {
    Log.d("LEO", string)
}
