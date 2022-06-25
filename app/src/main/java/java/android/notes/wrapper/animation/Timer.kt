package java.android.notes.wrapper.animation


import android.os.CountDownTimer
import java.android.notes.listeners.IItemsClickListener
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import android.widget.ImageView
import java.android.notes.R

class Timer(millisInFuture: Long, countDownInterval: Long) :
    CountDownTimer(millisInFuture, countDownInterval) {
    private var view: View? = null
    private var listener: IItemsClickListener? = null
    private var index = 0

    // drawing
    private var i = 0
    private var direction = 1
    private val spiral = Spiral()

    // get
    //
    var isFinish = false
        private set

    // set
    fun setParams(view: View?, listener: IItemsClickListener?, index: Int) {
        this.view = view
        this.index = index
        this.listener = listener
    }

    fun setView(view: View?) {
        this.view = view
    }

    fun changeDirection() {
        direction = if (direction == 1) {
            -1
        } else {
            +1
        }
    }

    fun reset() {
        i = 0
        direction = 1
    }

    override fun onTick(l: Long) {
        isFinish = false
        val imageView = view as ImageView?
        val bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        i += direction
        spiral.reset()
        for (a in 0..i-1) {
            spiral.process()
            paint.color = Color.RED
            canvas.drawCircle(50 + spiral.x.toFloat(), 50 + spiral.y.toFloat(), 15f, paint)
        }
        imageView!!.setImageBitmap(bitmap)
        if (i == 70) {
            listener!!.removeNote(index)
        }
    }

    override fun onFinish() {
        (view as ImageView?)!!.setImageResource(R.drawable.delete)
        isFinish = true
    }
}