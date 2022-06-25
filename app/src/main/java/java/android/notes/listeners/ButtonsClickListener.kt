package java.android.notes.listeners

import android.content.res.Configuration
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import java.android.notes.R
import java.android.notes.core.Control

class ButtonsClickListener(
    private val activity: AppCompatActivity,
    private val view: View,
    private val control: Control,
    private val rv: RecyclerView
) : View.OnClickListener {
    private var imageDirectionId = R.drawable.list_down
    private fun init(view: View) {
        if (activity.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            view.findViewById<View>(R.id.imageViewDownUpList).setOnClickListener(this)
        }
    }

    override fun onClick(view: View) {
        // пока одна кнопка поэтому swith отсутствует
        if (imageDirectionId == R.drawable.list_up) {
            //(view as ImageView).setImageResource(R.drawable.list_down.also {
                //imageDirectionId = it
            //})
            rv.smoothScrollToPosition(control.notes.numberOfNotes() - 1)
        } else {
            //(view as ImageView).setImageResource(R.drawable.list_up.also { imageDirectionId = it })
            rv.smoothScrollToPosition(0)
        }
    }

    init {
        init(view)
    }
}