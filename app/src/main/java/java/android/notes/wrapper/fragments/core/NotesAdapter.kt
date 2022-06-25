package java.android.notes.wrapper.fragments.core

import androidx.recyclerview.widget.RecyclerView
import java.android.notes.wrapper.fragments.core.NotesAdapter.NoteViewHolder
import java.android.notes.listeners.IItemsClickListener
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import java.android.notes.R
import android.widget.TextView
import android.view.View.OnLongClickListener
import android.widget.ImageView
import java.android.notes.core.Note

class NotesAdapter() : RecyclerView.Adapter<NoteViewHolder>() {
    private var listener: IItemsClickListener? = null
    private var list: List<Note>? = null
    fun setList(list: List<Note>?) {
        this.list = list
    }

    fun setListener(listener: IItemsClickListener?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.headLineToList).text =
            list!!.get(position).headline
        holder.itemView.findViewById<TextView>(R.id.dateToList).text = list!!.get(position).date
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //////////////////////////////////////////////////////////////////////////////////////////////////////////

        init {

            //this.itemView = itemView
            val shortClick: View.OnClickListener = View.OnClickListener { view ->
                when (view.id) {
                    R.id.noteInformation -> listener!!.onOpenClick(adapterPosition)
                    R.id.imageViewDelete -> listener!!.onDeleteClick(
                        adapterPosition,
                        view as ImageView
                    )
                }
            }
            itemView.findViewById<View>(R.id.noteInformation).setOnClickListener(shortClick)
            itemView.findViewById<View>(R.id.imageViewDelete).setOnClickListener(shortClick)
            itemView.findViewById<View>(R.id.noteInformation).setOnLongClickListener(
                OnLongClickListener { view ->
                    listener!!.onLongItemClick(view)
                    true
                })
        }
    }
}