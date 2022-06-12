package java.android.notes.wrapper.fragments.core;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.android.notes.R;
import java.android.notes.activity.IDatatSourseHandler;
import java.android.notes.core.Control;
import java.android.notes.wrapper.animation.TimerControl;
import java.android.notes.wrapper.helpers.CreateFragment;
import java.android.notes.activity.MainActivity;
import java.android.notes.wrapper.helpers.Extra;


public class NotesFragment extends Fragment{
    private Control control;
    private NotesAdapter adapter;
    private RecyclerView rv;

    private int animDelay = 500;

    private int imageDirectionId = R.drawable.list_down;

    static int test = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_notes,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        control = ((IDatatSourseHandler)getActivity()).getControl();
        adapter = new NotesAdapter();

        // пока нет базы данных
        if(test == 0){
            for(int i = 0;i<20;i++){
                control.createNote();
                control.save("заметка "+i,"","");
            }
            test = 1;
        }

        Extra.initToolbar((AppCompatActivity)getActivity(),R.id.toolbarNotes);

        // переделать и убрать импорт
        ((MainActivity) requireActivity()).initDrawer();
        initList(view);

        if(requireActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            initButton(view);
        }

    }

    private void initList(View view) {
        rv = view.findViewById(R.id.rvNotes);

        // animation
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(animDelay);
        animator.setChangeDuration(animDelay);
        animator.setRemoveDuration(animDelay);
        rv.setItemAnimator(animator);

        adapter.setList(control.notes.getNotes());
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setReverseLayout(true);
        llm.setStackFromEnd(true);
        rv.setLayoutManager(llm);

        DividerItemDecoration decorator = new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL);
        decorator.setDrawable((getResources().getDrawable(R.drawable.separator,null)));
        rv.addItemDecoration(decorator);

        rv.setAdapter(adapter);

        adapter.setListener(new NotesClickListener() {
            TimerControl timerControl = new TimerControl();

            @Override
            public void onOpenClick(int index) {
                control.openNoteOutList(index);
                createNoteFragent();
            }

            @Override
            public void onDeleteClick(int index,ImageView view){
                //removeNote(index); без анимации
                timerControl.start(view,index,this);
            }

            @Override
            public void removeNote(int index) {
                control.removeNoteOutList(index);
                adapter.notifyItemRemoved(index);
            }
        });

    }

    private void initButton(View view) {
        view.findViewById(R.id.imageViewDownUpList).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageDirectionId == R.drawable.list_up){
                    ((ImageView)view).setImageResource((imageDirectionId = R.drawable.list_down));
                    rv.smoothScrollToPosition(control.notes.numberOfNotes() - 1);
                }else{
                    ((ImageView)view).setImageResource((imageDirectionId = R.drawable.list_up));
                    rv.smoothScrollToPosition(0);
                }
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
                createNoteFragent();
                return true;
            case R.id.action_sorted_notes:
                control.sortedNotes();
                CreateFragment.createNotesFragment((AppCompatActivity) requireActivity());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void createNoteFragent(){
        if(requireActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            CreateFragment.createNoteFragment((AppCompatActivity) requireActivity());
        }else{
            CreateFragment.createNoteFragmentLand((AppCompatActivity) getActivity());
        }
    }

}
