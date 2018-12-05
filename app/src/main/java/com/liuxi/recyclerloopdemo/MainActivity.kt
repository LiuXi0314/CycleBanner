package com.liuxi.recyclerloopdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.liuxi.cyclebanner.CycleBannerHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager
        var adapter = ImageAdapter()
        recyclerView.adapter = adapter
        var cycleBannerHelper = CycleBannerHelper()
        cycleBannerHelper.setFirstItemPos(200)
        cycleBannerHelper.attachToRecyclerView(recyclerView)

        adapter.setData(createData())
    }

    private fun createData(): List<Model> {
        var list = ArrayList<Model>()
        list.add(Model(R.drawable.p1))
        list.add(Model(R.drawable.p2))
        list.add(Model(R.drawable.p3))
        list.add(Model(R.drawable.p4))

        return list
    }

    //grpc protobof


}
