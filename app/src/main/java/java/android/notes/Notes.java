package java.android.notes;

import java.util.ArrayList;
import java.util.Date;

public class Notes {

    private ArrayList<Note> notes = new ArrayList<>();

    public int numberOfNotes(){ return notes.size(); }

    public Note getNote(int index){
        return notes.get(index) ;
    }

    public void addNote(Note note){ notes.add(note); }

    public void removeNote(Note note){
        notes.remove(note);
    }

}
