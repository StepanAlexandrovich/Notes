package java.android.notes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NoteFragment extends Fragment implements View.OnClickListener,Constants{
    MainActivity mainActivity = MainActivity.mainActivity;
    Notes notes = mainActivity.notes;
    Note note = mainActivity.note;

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

        editTextHeadLine.setText(note.getHeadline());
        editTextDescription.setText(note.getDescription());
        editTextBody.setText(note.getBody());

        Button buttonAdd = view.findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(this);

        Button buttonCancel = view.findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonAdd:
                writeNote(note);

                if(note.ready()){
                    if(mainActivity.mode == Mode.addNote){
                        notes.addNote(note);
                    }
                    mainActivity.createNotesFragment();
                }else{
                    Toast.makeText(getContext(), "FILL NOTES'NAME", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.buttonCancel:
                mainActivity.createNotesFragment();
                break;
        }

    }

    void writeNote(Note note){
        note.setHeadline(editTextHeadLine.getText().toString());
        note.setDescription(editTextDescription.getText().toString());
        note.setBody(editTextBody.getText().toString());
        note.setDate(Time.getDate().toString());
    }

}
