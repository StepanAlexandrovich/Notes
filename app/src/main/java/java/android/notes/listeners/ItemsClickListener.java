package java.android.notes.listeners;

import android.graphics.Color;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.android.notes.R;
import java.android.notes.core.Control;
import java.android.notes.wrapper.animation.TimerControl;
import java.android.notes.saveout.IPreferences;
import java.android.notes.wrapper.fragments.core.NotesAdapter;
import java.android.notes.wrapper.helpers.CreateFragment;

public class ItemsClickListener implements IItemsClickListener {
    TimerControl timerControl = new TimerControl();

    Control control;
    AppCompatActivity activity;
    NotesAdapter adapter;

    public ItemsClickListener(Control control, AppCompatActivity activity, NotesAdapter adapter) {
        this.control = control;
        this.activity = activity;
        this.adapter = adapter;
    }

    @Override
    public void onOpenClick(int index) {
        control.openNoteOutList(index);
        CreateFragment.createNoteFragent(activity);
    }

    @Override
    public void onDeleteClick(int index, ImageView view){
        //removeNote(index); без анимации
        timerControl.start(view,index,this);

        //((IWebStore)activity).getWebStore().del(control.notes.getNote(index));
    }

    @Override
    public void removeNote(int index) {
        control.removeNoteOutList(index);
        adapter.notifyItemRemoved(index);
        ((IPreferences)activity).putStringControl();  // save out
    }

    @Override
    public void onLongItemClick(View view) {
        PopupMenu popupMenu = new PopupMenu(activity,view);
        activity.getMenuInflater().inflate(R.menu.menu_notes_context,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.headLineRed:
                        ((TextView)view.findViewById(R.id.headLineToList)).setTextColor(Color.RED);
                        return true;
                    case R.id.headLineBlue:
                        ((TextView)view.findViewById(R.id.headLineToList)).setTextColor(Color.BLUE);
                        return true;
                    case R.id.headLineDeepBlue:
                        ((TextView)view.findViewById(R.id.headLineToList)).setTextColor(ContextCompat.getColor(view.getContext(),R.color.deep_blue));
                        return true;
                }
                return true;
            }
        });
        popupMenu.show();
    }

}
