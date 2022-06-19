package java.android.notes.wrapper.fragments.core;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.android.notes.R;
import java.android.notes.core.Note;
import java.android.notes.listeners.INotesClickListener;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder>{
    private INotesClickListener listener;
    private List<Note> list;

    public void setList(List<Note> list) {
        this.list = list;
    }

    public void setListener(INotesClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note,parent,false);

        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.getItemView().<TextView>findViewById(R.id.headLineToList).setText(list.get(position).getHeadline());
        holder.getItemView().<TextView>findViewById(R.id.dateToList).setText(list.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    //////////////////////////////////////////////////////////////////////////////////////////////////////////
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
                    listener.onDeleteClick(getAdapterPosition(),(ImageView)view);
                }
            });

            itemView.findViewById(R.id.noteInformation).setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onLongItemClick(view);
                    return true;
                }
            });

        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////////

        public View getItemView() {
            return itemView;
        }
    }

}
