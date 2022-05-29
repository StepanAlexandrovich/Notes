package java.android.notes.core;

import java.android.notes.Time;

public class Control {
    private enum Mode{ addNote,openNote }
    private Mode mode = Mode.addNote;

    public Notes notes = new Notes();
    public Note note = new Note(Time.getDateYMD());

    public void createNote() {
        note = new Note(Time.getDateYMD());
        mode = Mode.addNote;
    }

    public void openNoteOutList(int index) {
        note = notes.getNote(index);
        mode = Mode.openNote;
    }

    public void sortedNotes(){
        notes.sorted();
    }

    public void removeNoteOutList(int index) {
        notes.removeNote(notes.getNote(index));
    }

    public boolean save(String headline,String description,String body){
        if(headline.equals("")){ return false; }

        note.setHeadline(headline);
        note.setDescription(description);
        note.setBody(body);

        if(mode == Mode.addNote){
            notes.addNote(note);
        }

        return true;
    }

}
