package java.android.notes.wrapper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.android.notes.R;
import java.android.notes.core.Control;
import java.android.notes.core.Notes;

public class NotesFragment extends Fragment implements View.OnClickListener{
    Control control = MainActivity.control;
    Notes notes = control.notes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        for(int i = 0;i<notes.numberOfNotes();i++){

            view.findViewById(R.id.note);
            NoteVisible noteVisible = new NoteVisible(getContext());

            noteVisible.textViewNote.setText(String.valueOf(notes.getNote(i).getHeadline()));

            int index = i;
            noteVisible.information.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    control.openNoteOutList(index);

                    CreateFragment.createNoteFragment((AppCompatActivity) requireActivity());
                }
            });
            noteVisible.buttonDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    control.removeNoteOutList(index);

                    CreateFragment.createNotesFragment((AppCompatActivity) requireActivity());
                }
            });

            noteVisible.textViewDate.setText(notes.getNote(index).getDate());

            ((LinearLayout)view).addView(noteVisible);

        }

        view.findViewById(R.id.buttonAddNote).setOnClickListener(this);
        view.findViewById(R.id.buttonEditText).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonAddNote:
                control.createNote();
                CreateFragment.createNoteFragment((AppCompatActivity) requireActivity());
                break;
            case R.id.buttonEditText:
                CreateFragment.createCalendarFragment((AppCompatActivity) requireActivity());
                break;
        }
    }

}
