package java.android.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    public Notes notes = new Notes();
    public Note note;

    public static MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // временно //
        Note note1 = new Note();
        note1.setHeadline("note1");
        notes.addNote(note1);

        Note note2= new Note();
        note2.setHeadline("note2");
        notes.addNote(note2);

        Note note3 = new Note();
        note3.setHeadline("note3");
        notes.addNote(note3);
        //////



        mainActivity = this;
        if(savedInstanceState == null){
            createNotesFragment();
        }
    }

    // FRAGMENTS //
    void createNotesFragment(){
        createFragment(R.id.fragment_container_list,new NotesFragment());
    }

    void createFragment(int fragment_container, Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(fragment_container,fragment)
                .commit();
    }

}