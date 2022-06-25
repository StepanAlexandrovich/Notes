package java.android.notes.listeners

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import java.android.notes.wrapper.fragments.core.NotesAdapter
import java.android.notes.R
import java.android.notes.core.Control
import java.android.notes.wrapper.helpers.CreateFragment

class MenuListener(
    private val activity: AppCompatActivity,
    private val item: MenuItem,
    private val control: Control,
    private val adapter: NotesAdapter
) {
    fun menuSwitch(): Boolean {
        when (item.itemId) {
            R.id.action_add_note -> {
                control.createNote()
                CreateFragment.createNoteFragmentPortrait(activity)
                return true
            }
            R.id.action_sorted_notes -> {
                control.sortedNotes()
                adapter.notifyDataSetChanged()
                return true
            }
        }
        return activity.onOptionsItemSelected(item)
    }
}