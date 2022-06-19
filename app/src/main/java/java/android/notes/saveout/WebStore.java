package java.android.notes.saveout;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.android.notes.core.Control;
import java.android.notes.core.Note;
import java.android.notes.wrapper.fragments.core.NotesAdapter;
import java.lang.ref.Reference;
import java.util.List;

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
