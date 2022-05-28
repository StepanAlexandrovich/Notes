package java.android.notes.wrapper;

import android.content.res.Configuration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.android.notes.R;

public class CreateFragment {

    public static void createFragment(AppCompatActivity appCompatActivity, int fragment_container, Fragment fragment){
        appCompatActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(fragment_container,fragment)
                .commit();
    }

    public static void createFragmentWithBackStack(AppCompatActivity appCompatActivity,int fragment_container,Fragment fragment){
        appCompatActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(fragment_container,fragment)
                .addToBackStack(null)
                .commit();
    }

    public static void createCalendarFragment(AppCompatActivity activity){
        CreateFragment.createFragmentWithBackStack(activity, R.id.fragment_container_list,new CalendarFragment());
    }

    public static void createNotesFragment(AppCompatActivity activity){
        CreateFragment.createFragment(activity, R.id.fragment_container_list,new NotesFragment());
    }

    public static void createNoteFragment (AppCompatActivity activity){
        if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            CreateFragment.createFragmentWithBackStack(activity, R.id.fragment_container_list,new NoteFragment());
        }else{
            CreateFragment.createFragmentWithBackStack(activity, R.id.fragment_container_note,new NoteFragment());
        }
    }

}
