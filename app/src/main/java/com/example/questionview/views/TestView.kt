package com.example.questionview.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi


/**
 * Created by zhgq on 2020/1/2
 * Describe：自定义view的流程
 * 1.构造函数
 * 2.onMeasure 用于测量view的大小
 * 3.onSizeChanged 用于确定view大小
 * 4.onDraw 画出view
 * 5.在自定义ViewGroup的时候有个方法Onlayout() 用于确定子view的位置
 */
class TestView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paint = Paint()

    init {
        initPaint()
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var wSize = MeasureSpec.getSize(widthMeasureSpec)
        var wMode = MeasureSpec.getMode(widthMeasureSpec)

        var hSize = MeasureSpec.getSize(heightMeasureSpec)
        var hMode = MeasureSpec.getMode(heightMeasureSpec)

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {

        //点
        canvas!!.drawPoint(200f, 200f, paint)
        canvas.drawPoints(floatArrayOf(500f, 500f, 500f, 600f, 500f, 700f), paint)

        //线
        canvas.drawLine(200f,200f,500f,600f,paint)
        canvas.drawLines(floatArrayOf(100f,200f,200f,200f,100f,300f,200f,300f),paint)

        //矩形Rect和RectF两种最大的区别事精度不同 前者事int  后者事float
        canvas.drawRect(100f, 100f, 800f, 400f, paint)

        //圆角矩形
        canvas.drawRoundRect(100f,100f,800f,400f,30f,30f,paint)

        //圆
        canvas.drawCircle(100f,200f,150f,paint)

    }

    private fun initPaint() {
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 10f
    }


}