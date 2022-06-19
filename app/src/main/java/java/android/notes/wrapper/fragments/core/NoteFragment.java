package java.android.notes.wrapper.fragments.core;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.android.notes.R;
import java.android.notes.activity.IDatatSourseHandler;
import java.android.notes.saveout.IPreferences;
import java.android.notes.core.Control;
import java.android.notes.core.Note;
import java.android.notes.saveout.IWebStore;
import java.android.notes.wrapper.helpers.CreateFragment;
import java.android.notes.wrapper.helpers.Extra;

public class NoteFragment extends Fragment implements View.OnClickListener{
    private Control control;

    EditText editTextHeadLine,editTextDescription,editTextBody;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        return inflater.inflate(R.layout.fragment_note,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        control = ((IDatatSourseHandler)getActivity()).getControl();

        Extra.initToolbar((AppCompatActivity) getActivity(),R.id.toolbarNote);

        editTextHeadLine = view.findViewById(R.id.editTextHeadLine);
        editTextDescription = view.findViewById(R.id.editTextDescription);
        editTextBody = view.findViewById(R.id.editTextBody);

        Note note = control.note;
        editTextHeadLine.setText(note.getHeadline());
        editTextDescription.setText(note.getDescription());
        editTextBody.setText(note.getBody());

        view.findViewById(R.id.buttonSave).setOnClickListener(this);
        view.findViewById(R.id.buttonCancel).setOnClickListener(this);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_fragment_note,menu);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonSave:
                boolean save = control.save(
                    editTextHeadLine.getText().toString(),
                    editTextDescription.getText().toString(),
                    editTextBody.getText().toString()
                );

                if(save){
                    //((IPreferences)requireActivity()).putStringControl();  // save out
                    ((IWebStore)getActivity()).getWebStore().add(control.note); // save out

                    requireActivity().getSupportFragmentManager().popBackStack();
                    requireActivity().getSupportFragmentManager().popBackStack();
                    CreateFragment.createNotesFragment( (AppCompatActivity)requireActivity() );

                }else{
                    Toast.makeText(getContext(), "FILL NOTES'NAME", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.buttonCancel:
                requireActivity().getSupportFragmentManager().popBackStack();
                break;
        }

    }


}
