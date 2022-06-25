package java.android.notes.core

import java.android.notes.Time

class Control {
    private enum class Mode {
        addNote, openNote
    }

    private var mode = Mode.addNote

    var notes = Notes()

    var note: Note = Note(Time.dateYMD)
    fun createNote() {
        note = Note(Time.dateYMD)
        mode = Mode.addNote
    }

    fun openNoteOutList(index: Int) {
        note = notes.getNote(index)
        mode = Mode.openNote
    }

    fun sortedNotes() {
        notes.sorted()
    }

    fun removeNoteOutList(index: Int) {
        notes.removeNote(notes.getNote(index))
    }

    fun save(headline: String, description: String, body: String): Boolean {
        if (headline == "") {
            return false
        }

        note.setterHeadLine(headline)
        note.setterDescription(description)
        note.setterBody(body)
        if (mode == Mode.addNote) {
            notes.addNote(note)
        }
        return true
    }
}