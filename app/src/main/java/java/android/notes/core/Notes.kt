package java.android.notes.core

import java.util.*

class Notes {
    val notes = ArrayList<Note>()
    fun numberOfNotes(): Int {
        return notes.size
    }

    fun getNote(index: Int): Note {
        return notes[index]
    }

    fun addNote(note: Note) {
        notes.add(note)
    }

    fun removeNote(note: Note) {
        notes.remove(note)
    }

    //public void removeAll(){ notes.clear(); }
    fun sorted() {
        //Collections.sort(notes) { note1, note2 -> note2.headline.compareTo(note1.headline) }
    }
}