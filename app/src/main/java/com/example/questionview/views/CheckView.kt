package com.example.questionview.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.view.View
import com.example.questionview.R


/**
 * Created by zhgq on 2020/1/3
 * Describe：
 */
class CheckView(context: Context,attributeSet: AttributeSet) : View(context,attributeSet) {

    private val ANIM_NULL = 0 //动画状态-没有

    private val ANIM_CHECK = 1 //动画状态-开启

    private val ANIM_UNCHECK = 2 //动画状态-结束

    var mWidth=0
    var mHeight=0
    private var mHandler: Handler? = null
    private var mPaint=Paint()
    private var mBitmap:Bitmap?=null
    private var animCurrentPage = -1 // 当前页码

    private val animMaxPage = 13 // 总页数

    private var animDuration = 500 // 动画时长

    private var animState = ANIM_NULL // 动画状态


    private var isCheck = false // 是否只选中状态

    init {
        inits()
    }

    private fun inits(){
        mPaint.color= 0xffFF5317.toInt()
        mPaint.style=Paint.Style.FILL
        mPaint.isAntiAlias=true

        mBitmap= BitmapFactory.decodeResource(context.resources, R.mipmap.codekit)
        mHandler = @SuppressLint("HandlerLeak")
        object : Handler() {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                if (animCurrentPage in 0 until animMaxPage) {
                    invalidate()
                    if (animState == ANIM_NULL)
                        return
                    if (animState == ANIM_CHECK) {

                        animCurrentPage++
                    } else if (animState == ANIM_UNCHECK) {
                        animCurrentPage--
                    }

                    this.sendEmptyMessageDelayed(0, (animDuration / animMaxPage).toLong())
            }else{
                    animCurrentPage = -1
                    invalidate()
                    animState = ANIM_NULL
                }
            }
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth=w
        mHeight=h
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas!!.translate((mWidth/2).toFloat(), (mHeight/2).toFloat())

        canvas.drawCircle(0f,0f,240f,mPaint)

        // 得出图像边长
        // 得出图像边长
        val sideLength: Int? = mBitmap?.height

        // 得到图像选区 和 实际绘制位置
        // 得到图像选区 和 实际绘制位置
        val src =
            sideLength?.times(animCurrentPage)?.let {
                Rect(
                    it,
                    0,
                    sideLength * (animCurrentPage + 1),
                    sideLength
                )
            }
        val dst = Rect(-200, -200, 200, 200)

        // 绘制
        mBitmap?.let { canvas.drawBitmap(it,src,dst,null) }
    }

    /**
     * 选择
     */
    fun check() {
//        if (animState !== ANIM_NULL || isCheck) return
        animState = ANIM_CHECK
        animCurrentPage = 0
        mHandler!!.sendEmptyMessageDelayed(0, animDuration / animMaxPage.toLong())
        isCheck = true
    }

    /**
     * 取消选择
     */
    fun unCheck() {
        if (animState !== ANIM_NULL || !isCheck) return
        animState = ANIM_UNCHECK
        animCurrentPage = animMaxPage - 1
        mHandler!!.sendEmptyMessageDelayed(0, animDuration / animMaxPage.toLong())
        isCheck = false
    }

    /**
     * 设置动画时长
     * @param animDuration
     */
    fun setAnimDuration(animDuration: Int) {
        if (animDuration <= 0) return
        this.animDuration = animDuration
    }

    /**
     * 设置背景圆形颜色
     * @param color
     */
    override fun setBackgroundColor(color: Int) {
        mPaint.color = color
    }

}