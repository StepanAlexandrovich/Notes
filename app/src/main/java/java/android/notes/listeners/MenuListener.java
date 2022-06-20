package java.android.notes.listeners;

import android.content.ClipData;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import java.android.notes.R;
import java.android.notes.core.Control;
import java.android.notes.wrapper.fragments.core.NotesAdapter;
import java.android.notes.wrapper.helpers.CreateFragment;

public class MenuListener {
    private AppCompatActivity activity;
    private MenuItem item;
    private Control control;
    private NotesAdapter adapter;

    public MenuListener(AppCompatActivity activity,MenuItem item,Control control, NotesAdapter adapter) {
        this.activity = activity;
        this.item = item;
        this.control = control;
        this.adapter = adapter;
    }

    public boolean menuSwitch() {
        switch (item.getItemId()){
            case R.id.action_add_note:
                control.createNote();
                CreateFragment.createNoteFragmentPortrait(activity);
                return true;
            case R.id.action_sorted_notes:
                control.sortedNotes();
                adapter.notifyDataSetChanged();
                return true;
        }
        return activity.onOptionsItemSelected(item);
    }

}
