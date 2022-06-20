package java.android.notes.saveout;

import android.util.Log;

import androidx.annotation.Nullable;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.android.notes.core.Control;
import java.android.notes.core.Note;
import java.android.notes.wrapper.fragments.core.NotesAdapter;


public class WebStore {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void updateData(Control control, NotesAdapter adapter) {

        db.collection("Notes").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(e!=null){
                    Log.e("fireStoreError",e.getMessage());
                }

                for(DocumentChange dc : queryDocumentSnapshots.getDocumentChanges()){

                    if(dc.getType() == DocumentChange.Type.ADDED){
                        Note note = dc.getDocument().toObject(Note.class);
                        note.setId(dc.getDocument().getId());

                        control.notes.addNote(note);
                        adapter.notifyDataSetChanged();
                    }


                }

            }


        });

    }

    public void add(Note note) {
        db.collection("Notes").add(note);
    }

    public void del(Note note) {
        db.collection("Notes").document(note.getId()).delete();
    }

}
