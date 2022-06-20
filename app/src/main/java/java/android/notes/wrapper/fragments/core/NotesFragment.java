package java.android.notes.wrapper.fragments.core;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

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
import java.android.notes.listeners.ButtonsClickListener;
import java.android.notes.listeners.ItemsClickListener;
import java.android.notes.listeners.MenuListener;
import java.android.notes.activity.MainActivity;
import java.android.notes.wrapper.helpers.Extra;

public class NotesFragment extends Fragment{
    private Control control;
    private NotesAdapter adapter;
    private RecyclerView rv;

    private int animDelay = 500;

    private int imageDirectionId = R.drawable.list_down;

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
        initList(view);
        new ButtonsClickListener((AppCompatActivity)getActivity(),view,control,rv);

        // переделать
        Extra.initToolbar((AppCompatActivity)getActivity(),R.id.toolbarNotes);
        // переделать и убрать импорт MainActivity
        ((MainActivity) requireActivity()).initDrawer();
    }

    private void initList(View view) {
        adapter = new NotesAdapter();
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

        rv.postDelayed(new Runnable() {
            @Override
            public void run() {
                rv.scrollToPosition(control.notes.numberOfNotes() - 1);
            }
        },200);

        rv.setLayoutManager(llm);

        DividerItemDecoration decorator = new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL);
        decorator.setDrawable((getResources().getDrawable(R.drawable.separator,null)));
        rv.addItemDecoration(decorator);

        rv.setAdapter(adapter);

        adapter.setListener(new ItemsClickListener(control,(AppCompatActivity)getActivity(),adapter));
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_fragment_notes,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return (new MenuListener((AppCompatActivity)getActivity(),item,control,adapter)).menuSwitch();
    }

}
