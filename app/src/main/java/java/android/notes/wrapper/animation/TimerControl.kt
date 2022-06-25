package java.android.notes.wrapper.animation

import android.view.View
import java.android.notes.listeners.IItemsClickListener

class TimerControl {
    private var timer: Timer? = null
    fun start(view: View?, adapterPosition: Int, listener: IItemsClickListener?) {
        if (timer == null) {
            timer = Timer(2000, 10)
            timer!!.setParams(view, listener, adapterPosition)
            timer!!.start()
        } else {
            if (timer!!.isFinish) {
                timer!!.setParams(view, listener, adapterPosition)
                timer!!.reset()
                timer!!.start()
            } else {
                timer!!.changeDirection()
            }
        }
    }
}