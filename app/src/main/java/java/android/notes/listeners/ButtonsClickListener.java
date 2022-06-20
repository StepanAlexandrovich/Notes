package java.android.notes.listeners;

import android.content.res.Configuration;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.android.notes.R;
import java.android.notes.core.Control;

public class ButtonsClickListener implements View.OnClickListener{
    private int imageDirectionId = R.drawable.list_down;
    private AppCompatActivity activity;
    private View view;
    private Control control;
    private RecyclerView rv;

    public ButtonsClickListener(AppCompatActivity activity,View view, Control control, RecyclerView rv) {
        this.activity = activity;
        this.view = view;
        this.control = control;
        this.rv = rv;

        init(view);
    }

    private void init(View view) {
        if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            view.findViewById(R.id.imageViewDownUpList).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        // пока одна кнопка поэтому swith отсутствует
        if(imageDirectionId == R.drawable.list_up){
            ((ImageView)view).setImageResource((imageDirectionId = R.drawable.list_down));
            rv.smoothScrollToPosition(control.notes.numberOfNotes() - 1);
        }else{
            ((ImageView)view).setImageResource((imageDirectionId = R.drawable.list_up));
            rv.smoothScrollToPosition(0);
        }
    }
}
