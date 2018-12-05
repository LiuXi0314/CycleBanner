package com.liuxi.recyclerloopdemo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView

/**
 *
 * @Author liuxi
 *
 * @Email xiaoxixizhizhi@gmail.
 *
 *
 */
class ImageAdapter : RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false) as ImageView)
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var lp = holder.itemView.layoutParams
        var screenWidth = MainActivity.getScreenWidth(holder.itemView.context)
        var w = screenWidth - MainActivity.dip2px(holder.itemView.context,100f)
        var h = w * 1920 / 1080
        lp.width = w
        lp.height = h

        when (position % 4) {
            0 -> (holder.itemView as ImageView).setImageResource(R.drawable.p1)
            1 -> (holder.itemView as ImageView).setImageResource(R.drawable.p2)
            2 -> (holder.itemView as ImageView).setImageResource(R.drawable.p3)
            3 -> (holder.itemView as ImageView).setImageResource(R.drawable.p4)
        }




    }


}