package com.liuxi.recyclerloopdemo

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.view.WindowManager
import com.liuxi.recyclerloopdemo.lib.PagerSnapScaleHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        fun getScreenWidth(context: Context): Int {
            var wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            var outMetrics = DisplayMetrics()
            wm.defaultDisplay.getMetrics(outMetrics);
            return outMetrics.widthPixels
        }

        fun getScreenHeight(context: Context): Int {
            var wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            var outMetrics = DisplayMetrics()
            wm.defaultDisplay.getMetrics(outMetrics);
            return outMetrics.heightPixels
        }


        /**
         * dip转px
         */
        fun dip2px(context: Context, dpValue: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (dpValue * scale + 0.5f).toInt()
        }


        /**
         * convert sp to its equivalent px
         *
         * 将sp转换为px
         */
        fun sp2px(
            context: Context
            , spValue: Float
        ): Int {
            val fontScale = context.resources.displayMetrics.scaledDensity
            return (spValue * fontScale + 0.5f).toInt()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.adapter = ImageAdapter()
        PagerSnapScaleHelper().attachToRecyclerView(recyclerView)
    }


}
