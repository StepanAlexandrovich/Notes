package java.android.notes.wrapper.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.android.notes.R;
import java.android.notes.core.Notes;
import java.android.notes.wrapper.MainActivity;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder>{
    Notes notes = MainActivity.control.notes;

    private NotesClickListener listener;

    public void setListener(NotesClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_to_list,parent,false);

        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.getItemView().<TextView>findViewById(R.id.headLineToList).setText(notes.getNote(position).getHeadline());
        holder.getItemView().<TextView>findViewById(R.id.dateToList).setText(notes.getNote(position).getDate());
    }

    @Override
    public int getItemCount() {
        return notes.numberOfNotes();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        private View itemView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;

            itemView.findViewById(R.id.noteInformation).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onOpenClick(getAdapterPosition());
                }
            });

            itemView.findViewById(R.id.imageViewDelete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onDeleteClick(getAdapterPosition());
                }
            });

        }

        public View getItemView() {
            return itemView;
        }
    }

}
