package com.example.questionview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.questionview.data.BreadBean
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val mutableList = ArrayList<BreadBean>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bread_view.setStartAngle(20)
        for (i in 0..8) {
            mutableList.add(BreadBean("我是$i",1f,1f,-0x1cd9ca,1f))
        }
        bread_view.setData(mutableList)
    }


}
