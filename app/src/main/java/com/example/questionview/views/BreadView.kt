package com.example.questionview.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.example.questionview.data.BreadBean


/**
 * Created by zhgq on 2020/1/2
 * Describe：
 */
class BreadView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    var mPaint = Paint()
    private val mColors = intArrayOf(
        -0x330100, -0x9b6a13, -0x1cd9ca, -0x800000, -0x7f8000, -0x7397, -0x7f7f80,
        -0x194800, -0x830400
    )
    var mStartAngle: Float = 0f //初始角度
    private var mData: ArrayList<BreadBean>? = null
    private var mWidth = 0
    private var mHeight: Int = 0

    init {
        mPaint.style = Paint.Style.FILL
        mPaint.isAntiAlias = true
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var currentStartAngle = mStartAngle
        canvas!!.translate((mWidth / 2).toFloat(), (mHeight / 2).toFloat())
        val r = mWidth.coerceAtMost(mHeight) / 2 * 0.8
        val rect = RectF(-r.toFloat(), (-r).toFloat(), r.toFloat(), r.toFloat())

        for (i in mData!!.indices) {
            val pie: BreadBean = mData!![i]
            mPaint.color = pie.color
            canvas.drawArc(rect, currentStartAngle, pie.angle, true, mPaint)
            currentStartAngle += pie.angle
        }


    }

    fun setStartAngle(mStartAngle: Int) {
        this.mStartAngle = mStartAngle.toFloat()
        invalidate()

    }

    fun setData(mData: ArrayList<BreadBean>) {
        this.mData = mData
        initData(mData)
    }

    private fun initData(mData: ArrayList<BreadBean>) {
        if (mData.size == 0)   // 数据有问题 直接返回
            return
        var sumValue =0f

        for (i in mData.indices){
            val pie=mData[i]
            sumValue+=pie.value
            val j=i%mColors.size
            pie.color=mColors[j]

        }
        var sumangle=0f
        for (i in mData.indices){
            val pie=mData[i]
            val percentage=pie.value/sumValue
            val angle=percentage*360
            pie.percentage=percentage
            pie.angle=angle
            sumangle+=angle
        }


    }


}