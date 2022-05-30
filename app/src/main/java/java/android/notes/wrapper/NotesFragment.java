package java.android.notes.wrapper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import java.util.List;

public class NotesFragment extends Fragment{
    Control control = MainActivity.control;
    Notes notes = control.notes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_notes,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) requireActivity()).getSupportActionBar().show();

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

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_fragment_notes,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add_note:
                control.createNote();
                CreateFragment.createNoteFragment((AppCompatActivity) requireActivity());
                return true;
            case R.id.action_sorted_notes:
                control.sortedNotes();
                CreateFragment.createNotesFragment((AppCompatActivity) requireActivity());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
