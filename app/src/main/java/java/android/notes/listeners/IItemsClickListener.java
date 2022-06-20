package java.android.notes.listeners;

import android.view.View;
import android.widget.ImageView;

public interface IItemsClickListener {


    void onOpenClick(int index);
    void onDeleteClick(int index, ImageView view);
    void onLongItemClick(View view);

    void removeNote(int index);
}
