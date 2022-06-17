package java.android.notes.wrapper.fragments.core;

import android.view.View;
import android.widget.ImageView;

public interface NotesClickListener {

    void onOpenClick(int index);
    void onDeleteClick(int index, ImageView view);
    void onLongItemClick(View view);

    void removeNote(int index);
}
