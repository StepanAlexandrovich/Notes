package java.android.notes.wrapper;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.android.notes.R;

public class NoteVisible extends LinearLayout{
    LinearLayout information;
    TextView textViewNote,textViewDate;
    ImageView imageView;

    public NoteVisible(Context context) {
        super(context);

        setOrientation(LinearLayout.HORIZONTAL);

        information = new LinearLayout(context);
        information.setOrientation(LinearLayout.VERTICAL);
            textViewNote = new TextView(context);
            textViewNote.setTextColor(Color.rgb(0,0,255));
            textViewNote.setTextSize(30);
            textViewDate = new TextView(context);
        information.addView(textViewNote);
        information.addView(textViewDate);

        imageView = new ImageView(context);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.delete));
        imageView.setLayoutParams(new LinearLayout.LayoutParams(220, 220));

        addView(information);
        addView(imageView);
    }

}
