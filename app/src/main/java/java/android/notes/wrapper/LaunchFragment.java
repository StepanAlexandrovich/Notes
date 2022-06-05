package java.android.notes.wrapper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.android.notes.R;
import java.android.notes.wrapper.alerts.CustomDialogFragmentWithView;
import java.android.notes.wrapper.alerts.CustomDialogListener;
import java.android.notes.wrapper.helpers.CreateFragment;

public class LaunchFragment extends Fragment implements View.OnClickListener, CustomDialogListener {
    private CustomDialogFragmentWithView dialog;

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
        //visible();  // временно

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

                //notVisible(); // временно

                dialog = new CustomDialogFragmentWithView();
                dialog.setListener(this);
                dialog.show(requireActivity().getSupportFragmentManager(),CustomDialogFragmentWithView.TAG);

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

    @Override
    public void onOk() {
        if(dialog.editText.getText().toString().equals("4")){
            CreateFragment.createNotesFragment( (AppCompatActivity)requireActivity() );
        }else{
            Toast.makeText((AppCompatActivity)requireActivity(),"Результат проведённого тэста указывает на то, что уровня знаний недостаточно. Совершенствуйте свои навыки и обязательно возвращайтесь снова", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onNo() {
        Toast.makeText((AppCompatActivity)requireActivity(), "Действие отменено пользователем", Toast.LENGTH_SHORT).show();
    }
}
