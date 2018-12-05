package com.liuxi.recyclerloopdemo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.liuxi.cyclebanner.CycleAdapter

/**
 *
 * @Author liuxi
 *
 * @Email xiaoxixizhizhi@gmail.
 *
 *
 */
class ImageAdapter : CycleAdapter<Model, ImageAdapter.Holder>() {

    override fun createView(parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
    }

    override fun createViewHolder(view: View): Holder {
        return Holder(view)
    }

    override fun onBind(holder: Holder?, model: Model?, position: Int) {
        if(model!= null) {
            (holder?.itemView as ImageView).setImageResource(model?.id)
        }
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {

    }


}