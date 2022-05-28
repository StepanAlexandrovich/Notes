package java.android.notes.wrapper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.android.notes.R;
import java.android.notes.core.Control;
import java.android.notes.core.Note;

public class NoteFragment extends Fragment implements View.OnClickListener{
    Control control = MainActivity.control;

    EditText editTextHeadLine,editTextDescription,editTextBody;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonSave:
                boolean save = control.save(
                        editTextHeadLine.getText().toString(),
                        editTextDescription.getText().toString(),
                        editTextBody.getText().toString()
                );

                if(save){
                    requireActivity().getSupportFragmentManager().popBackStack();
                    CreateFragment.createNotesFragment((AppCompatActivity) requireActivity());
                }else{
                    Toast.makeText(getContext(), "FILL NOTES'NAME", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.buttonCancel:
                requireActivity().getSupportFragmentManager().popBackStack();
                CreateFragment.createNotesFragment((AppCompatActivity) requireActivity());
                break;
        }

    }

}
