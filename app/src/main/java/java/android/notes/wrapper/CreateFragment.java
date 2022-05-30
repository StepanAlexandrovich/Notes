package java.android.notes.wrapper;

import android.content.res.Configuration;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.android.notes.R;

public class CreateFragment {

    /////////////////////////////////////////////////
    public static void createFragment(AppCompatActivity appCompatActivity, int fragment_container, Fragment fragment){
        //fragment.requireView()
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
        if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            CreateFragment.createFragmentWithBackStack(activity, R.id.fragment_container,new CalendarFragment());
        }else{
            CreateFragment.createFragmentWithBackStack(activity, R.id.fragment_container_list,new CalendarFragment());
        }
    }

    public static void createLaunchFragment (AppCompatActivity activity){
        CreateFragment.createFragment(activity, R.id.fragment_container,new LaunchFragment());
    }

    public static void createNotesFragment(AppCompatActivity activity){
        if(Helper.beingNotesFragment()){ activity.getSupportFragmentManager().popBackStack(); }

        if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            CreateFragment.createFragmentWithBackStack(activity, R.id.fragment_container,new NotesFragment());
        }else{
            CreateFragment.createFragmentWithBackStack(activity, R.id.fragment_container_list,new NotesFragment());
        }
    }

    public static void createNoteFragment (AppCompatActivity activity){
        if(Helper.beingNoteFragment()){ activity.getSupportFragmentManager().popBackStack(); }

        if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            CreateFragment.createFragmentWithBackStack(activity, R.id.fragment_container,new NoteFragment());
        }else{
            CreateFragment.createFragmentWithBackStack(activity, R.id.fragment_container_note,new NoteFragment());
        }

    }

    public static void createSettingsFragment (AppCompatActivity activity){
        CreateFragment.createFragmentWithBackStack(activity, R.id.fragment_container,new SettingsFragment());
    }

    public static void createAboutFragment (AppCompatActivity activity){
        if(Helper.beingAboutFragment()){ activity.getSupportFragmentManager().popBackStack(); }
        CreateFragment.createFragmentWithBackStack(activity, R.id.fragment_container,new AboutFragment());
    }

    public static void createChildFragment (androidx.fragment.app.Fragment fragmentParent){
        //CreateFragment.createChildFragment(fragmentParent, R.id.fragment_child_container,new ChildFragment());
    }

}
