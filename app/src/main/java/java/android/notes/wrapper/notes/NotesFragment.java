package java.android.notes.wrapper.notes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.android.notes.R;
import java.android.notes.core.Control;
import java.android.notes.wrapper.alerts.CustomDialogListener;
import java.android.notes.wrapper.alerts.SympleNotes;
import java.android.notes.wrapper.helpers.CreateFragment;
import java.android.notes.wrapper.MainActivity;

public class NotesFragment extends Fragment{
    Control control = MainActivity.control;

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

        initList(view);
    }

    private void initList(View view) {
        RecyclerView rv = view.findViewById(R.id.rvNotes);
        NotesAdapter adapter = new NotesAdapter();
        //adapter.setList(Arrays.asList(notes));
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);
        rv.setAdapter(adapter);

        adapter.setListener(new NotesClickListener() {
            @Override
            public void onOpenClick(int index) {
                control.openNoteOutList(index);
                CreateFragment.createNoteFragment((AppCompatActivity) requireActivity());
            }

            @Override
            public void onDeleteClick(int index){
                CustomDialogListener listener = new CustomDialogListener() {
                    @Override
                    public void onOk() {
                        control.removeNoteOutList(index);
                        CreateFragment.createNotesFragment((AppCompatActivity) requireActivity());
                    }

                    @Override
                    public void onNo() {
                        Toast.makeText(requireActivity(), "Действие отменено пользователем", Toast.LENGTH_SHORT).show();
                    }
                };
                SympleNotes.showAlertDialog((AppCompatActivity)requireActivity(),listener,"УВЕРЕН?","100%","НЕЕЕЕТ!");
            }
        });
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
