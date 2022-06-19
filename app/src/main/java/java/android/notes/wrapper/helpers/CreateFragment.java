package java.android.notes.wrapper.helpers;

import android.content.res.Configuration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.android.notes.R;
import java.android.notes.wrapper.fragments.extra.AboutFragment;
import java.android.notes.wrapper.fragments.extra.CalendarFragment;
import java.android.notes.wrapper.fragments.extra.LaunchFragment;
import java.android.notes.wrapper.fragments.extra.SettingsFragment;
import java.android.notes.wrapper.fragments.core.NotesFragment;
import java.android.notes.wrapper.fragments.core.NoteFragment;

public class CreateFragment {

    /////////////////////////////////////////////////
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

    public static void createChildFragment(androidx.fragment.app.Fragment fragmentParent, int fragment_container, Fragment fragmentChild){
        fragmentParent.getChildFragmentManager()
                .beginTransaction()
                .replace(fragment_container,fragmentChild)
                .commit();
    }
    ////////////////////////////////////////////////////

    public static void createCalendarFragment(AppCompatActivity activity){
        CreateFragment.createFragmentWithBackStack(activity, R.id.fragmentContainer,new CalendarFragment());
    }

    public static void createLaunchFragment (AppCompatActivity activity){
        CreateFragment.createFragment(activity, R.id.fragmentContainer,new LaunchFragment());
    }

    public static void createNotesFragment(AppCompatActivity activity){
        CreateFragment.createFragmentWithBackStack(activity, R.id.fragmentContainer,new NotesFragment());
    }

    public static void createNoteFragment (AppCompatActivity activity){
        CreateFragment.createFragmentWithBackStack(activity, R.id.fragmentContainer,new NoteFragment());
    }

    public static void createNoteFragmentLand (AppCompatActivity activity){
        if(Extra.beingNoteFragment(activity)){ activity.getSupportFragmentManager().popBackStack(); }
        CreateFragment.createFragmentWithBackStack(activity, R.id.fragmentContainerNote,new NoteFragment());
    }

    // childFragment
    //public static void createNoteFragment (androidx.fragment.app.Fragment fragmentParent){
        //CreateFragment.createChildFragmentWithBackStack(fragmentParent, R.id.fragmentContainerNote,new NoteFragment());
    //}

    public static void createSettingsFragment (AppCompatActivity activity){
        CreateFragment.createFragmentWithBackStack(activity, R.id.fragmentContainer,new SettingsFragment());
    }

    public static void createAboutFragment (AppCompatActivity activity){
        CreateFragment.createFragmentWithBackStack(activity, R.id.fragmentContainer,new AboutFragment());
    }

    public static void createNoteFragent1(AppCompatActivity activity){
        if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            CreateFragment.createNoteFragment(activity);
        }else{
            CreateFragment.createNoteFragmentLand(activity);
        }
    }

}
