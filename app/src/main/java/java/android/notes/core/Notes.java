package java.android.notes.core;

import java.util.ArrayList;

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
