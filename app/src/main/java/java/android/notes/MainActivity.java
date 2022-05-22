package java.android.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Constants{
    public Notes notes = new Notes();
    public Note note = new Note();

    public Mode mode = Mode.addNote;
    public static MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivity = this;

        createNotesFragment();
    }

    // CALL OUT //

    void addNote() {
        note = new Note();
        mode = Mode.addNote;
        createNoteFragment();
    }

    void openNote(int index) {
        note = notes.getNote(index);
        mode = Mode.openNote;
        createNoteFragment();
    }

    void removeNote(int index) {
        notes.removeNote(notes.getNote(index));
        createNotesFragment();
    }

    // FRAGMENTS //

    void createNotesFragment(){
        createFragment(R.id.fragment_container_list,new NotesFragment());
    }

    void createNoteFragment (){
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            createFragmentWithBackStack(R.id.fragment_container_list,new NoteFragment());
        }else{
            createFragmentWithBackStack(R.id.fragment_container_note,new NoteFragment());
        }
    }

    void createFragment(int fragment_container, Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(fragment_container,fragment)
                .commit();
    }

    void createFragmentWithBackStack(int fragment_container,Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(fragment_container,fragment)
                //.addToBackStack(null)
                .commit();
    }

}