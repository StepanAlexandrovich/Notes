package java.android.notes.wrapper;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.android.notes.R;
import java.android.notes.core.Control;
import java.android.notes.core.Notes;
import java.util.List;

public class NotesFragment extends Fragment implements CustomDialogListener{
    Control control = MainActivity.control;
    Notes notes = control.notes;

    TextView textViewNote;

    private int indexChooseNote;

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

            // textViewNote
            textViewNote = noteVisible.textViewNote;
            noteVisible.textViewNote.setText(String.valueOf(notes.getNote(i).getHeadline())+" ");  // " " - > убрать

            int index = i;
            noteVisible.information.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    indexChooseNote = index;
                    control.openNoteOutList(indexChooseNote);

                    CreateFragment.createNoteFragment((AppCompatActivity) requireActivity());
                }
            });

            //registerForContextMenu(noteVisible.textViewNote);
            //
            noteVisible.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    SympleNotes.showAlertDialog((AppCompatActivity) requireActivity(),NotesFragment.this,"УВЕРЕН?","100%","НЕЕЕЕТ!");

                    indexChooseNote = index;
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


    ///// доработать /////////////
    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if(v == textViewNote){
            menu.add(0,1,0,"red");
            menu.add(0,2,0,"green");
            menu.add(0,3,0,"blue");
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 1: textViewNote.setTextColor(Color.rgb(255,0,0)); break;
            case 2: textViewNote.setTextColor(Color.rgb(0,255,0)); break;
            case 3: textViewNote.setTextColor(Color.rgb(0,0,255)); break;
        }

        return super.onContextItemSelected(item);
    }


    // удалять или не удалять
    @Override
    public void onOk() {
        control.removeNoteOutList(indexChooseNote);
        CreateFragment.createNotesFragment((AppCompatActivity) requireActivity());
    }

    @Override
    public void onNo() {
        Toast.makeText(requireActivity(), "Действие отменено пользователем", Toast.LENGTH_SHORT).show();
    }

}
