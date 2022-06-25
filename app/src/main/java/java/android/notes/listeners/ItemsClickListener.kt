package java.android.notes.listeners

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import java.android.notes.wrapper.fragments.core.NotesAdapter
import java.android.notes.wrapper.animation.TimerControl
import java.android.notes.wrapper.helpers.CreateFragment
import java.android.notes.saveout.IPreferences
import java.android.notes.R
import android.widget.TextView
import androidx.core.content.ContextCompat
import java.android.notes.core.Control

class ItemsClickListener(
    var control: Control,
    var activity: AppCompatActivity,
    var adapter: NotesAdapter
) : IItemsClickListener {
    var timerControl = TimerControl()
    override fun onOpenClick(index: Int) {
        control.openNoteOutList(index)
        CreateFragment.createNoteFragent(activity)
    }

    override fun onDeleteClick(index: Int, view: ImageView?) {
        //removeNote(index); без анимации
        timerControl.start(view, index, this)

        //((IWebStore)activity).getWebStore().del(control.notes.getNote(index));
    }

    override fun removeNote(index: Int) {
        control.removeNoteOutList(index)
        adapter.notifyItemRemoved(index)
        (activity as IPreferences).putStringControl() // save out
    }

    override fun onLongItemClick(view: View?) {
        val popupMenu = PopupMenu(activity, view)
        activity.menuInflater.inflate(R.menu.menu_notes_context, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.headLineRed -> {
                    (view!!.findViewById<View>(R.id.headLineToList) as TextView).setTextColor(Color.RED)
                    return@OnMenuItemClickListener true
                }
                R.id.headLineBlue -> {
                    (view!!.findViewById<View>(R.id.headLineToList) as TextView).setTextColor(Color.BLUE)
                    return@OnMenuItemClickListener true
                }
                R.id.headLineDeepBlue -> {
                    (view!!.findViewById<View>(R.id.headLineToList) as TextView).setTextColor(
                        ContextCompat.getColor(
                            view.context, R.color.deep_blue
                        )
                    )
                    return@OnMenuItemClickListener true
                }
            }
            true
        })
        popupMenu.show()
    }
}