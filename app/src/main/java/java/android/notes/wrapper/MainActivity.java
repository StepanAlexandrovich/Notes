package java.android.notes.wrapper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.android.notes.R;
import java.android.notes.core.Control;

public class MainActivity extends AppCompatActivity{
    public static Control control = new Control();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CreateFragment.createNotesFragment(this);
    }

}