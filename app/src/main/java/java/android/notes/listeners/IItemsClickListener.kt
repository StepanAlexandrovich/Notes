package java.android.notes.listeners

import android.view.View
import android.widget.ImageView

interface IItemsClickListener {
    fun onOpenClick(index: Int)
    fun onDeleteClick(index: Int, view: ImageView?)
    fun onLongItemClick(view: View?)
    fun removeNote(index: Int)
}