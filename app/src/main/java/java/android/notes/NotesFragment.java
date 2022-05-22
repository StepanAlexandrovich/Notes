package java.android.notes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NotesFragment extends Fragment {
    MainActivity mainActivity = MainActivity.mainActivity;

    Notes notes = mainActivity.notes;
    Note note = mainActivity.note;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        for(int i = 0;i<notes.numberOfNotes();i++){

            NoteVisible noteVisible = new NoteVisible(getContext());

            noteVisible.textViewNote.setText(String.valueOf(notes.getNote(i).getHeadline()));

            int index = i;
            noteVisible.information.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // ......................
                }
            });
            noteVisible.buttonDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // ...........................
                }
            });

            noteVisible.textViewDate.setText(Time.getDate().toString());

            ((LinearLayout)view).addView(noteVisible);

        }

        view.findViewById(R.id.buttonAddNote).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // ................
            }
        });
    }


}
