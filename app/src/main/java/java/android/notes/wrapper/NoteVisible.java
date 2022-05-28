package java.android.notes.wrapper;

import android.content.Context;
import android.graphics.Color;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NoteVisible extends LinearLayout{
    LinearLayout information;
    TextView textViewNote,textViewDate;
    Button buttonDel;

    public NoteVisible(Context context) {
        super(context);

        setOrientation(LinearLayout.HORIZONTAL);

        information = new LinearLayout(context);
        information.setOrientation(LinearLayout.VERTICAL);
            textViewNote = new TextView(context);
            textViewNote.setTextColor(Color.rgb(255,0,0));
            textViewDate = new TextView(context);
        information.addView(textViewNote);
        information.addView(textViewDate);

        buttonDel = new Button(context);
        buttonDel.setText("del");

        addView(information);
        addView(buttonDel);
    }



}
