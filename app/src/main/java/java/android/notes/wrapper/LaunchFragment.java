package java.android.notes.wrapper;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
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
        return inflater.inflate(R.layout.fragment_launch,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        visible();  // временно

        view.findViewById(R.id.buttonOpenNotes).setOnClickListener(this);
        view.findViewById(R.id.buttonSettings).setOnClickListener(this);
        view.findViewById(R.id.buttonAbout).setOnClickListener(this);
        view.findViewById(R.id.buttonAddFragment).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonOpenNotes:

                notVisible(); // временно

                CreateFragment.createNotesFragment( (AppCompatActivity)requireActivity() );
                break;
            case R.id.buttonSettings:
                CreateFragment.createSettingsFragment((AppCompatActivity)requireActivity());
                break;
            case R.id.buttonAbout:
                AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
                builder.setTitle("BOMBACOD").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {}
                });
                builder.create().show();
                break;
            case R.id.buttonAddFragment:
                CreateFragment.createChildFragment(LaunchFragment.this);
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
