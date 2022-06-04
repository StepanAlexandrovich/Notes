package java.android.notes.wrapper;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.android.notes.R;

public class LaunchFragment extends Fragment implements View.OnClickListener{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        return inflater.inflate(R.layout.fragment_launch,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) requireActivity()).getSupportActionBar().hide();
        visible();  // временно

        view.findViewById(R.id.buttonOpenNotes).setOnClickListener(this);
        view.findViewById(R.id.buttonClose).setOnClickListener(this);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        MenuItem item = menu.findItem(R.id.action_exit);
        if(item!=null){
            item.setVisible(false);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonOpenNotes:

                notVisible(); // временно

                CreateFragment.createNotesFragment( (AppCompatActivity)requireActivity() );

                new CustomDialogFragmentWithView().show(requireActivity().getSupportFragmentManager(),CustomDialogFragmentWithView.TAG);

                break;
            case R.id.buttonClose:
                requireActivity().finish();
                break;
        }
    }

    ////////////////////////
    public void visible(){
        requireActivity().getSupportFragmentManager().beginTransaction().show(this).commit();
    }

    public void notVisible(){
        requireActivity().getSupportFragmentManager().beginTransaction().hide(this).commit();
    }
}
