package java.android.notes.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Notes {

    private ArrayList<Note> notes = new ArrayList<>();

    public ArrayList<Note> getNotes() { return notes; }

    public int numberOfNotes(){ return notes.size(); }

    public Note getNote(int index){
        return notes.get(index) ;
    }

    public void addNote(Note note){ notes.add(note); }

    public void removeNote(Note note){
        notes.remove(note);
    }

    public void removeAll(){ notes.clear(); }

    public void sorted(){
        Collections.sort(notes, new Comparator<Note>(){
            @Override
            public int compare(Note note1, Note note2) {
                return note1.getHeadline().compareTo(note2.getHeadline());
            }
        });
    }

}
