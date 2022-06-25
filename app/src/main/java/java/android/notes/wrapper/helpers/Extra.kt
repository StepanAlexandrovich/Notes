package java.android.notes.wrapper.helpers

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import java.android.notes.wrapper.fragments.core.NoteFragment

object Extra {
    fun beingNoteFragment(activity: AppCompatActivity): Boolean {
        val fragments = activity.supportFragmentManager.fragments
        for (fragment in fragments) {
            if (fragment is NoteFragment && fragment.isVisible()) {
                return true
            }
        }
        return false
    }

    fun initToolbar(activity: AppCompatActivity, toolbarId: Int) {
        val toolbar = activity.findViewById<Toolbar>(toolbarId)
        activity.setSupportActionBar(toolbar)
    }
}