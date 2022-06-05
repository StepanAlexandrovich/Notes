package java.android.notes.wrapper.helpers;

import androidx.fragment.app.Fragment;

import java.android.notes.wrapper.AboutFragment;
import java.android.notes.wrapper.MainActivity;
import java.android.notes.wrapper.notes.NotesFragment;
import java.android.notes.wrapper.notes.NoteFragment;
import java.util.List;

public class Helper {
    public static MainActivity mainActivity;

    public static boolean beingNoteFragment(){
        List<Fragment> fragments = mainActivity.getSupportFragmentManager().getFragments();
        for(Fragment fragment:fragments){
            if(fragment instanceof NoteFragment && fragment.isVisible()){
                return true;
            }
        }

        return false;
    }

    public static boolean beingNotesFragment(){
        List<Fragment> fragments = mainActivity.getSupportFragmentManager().getFragments();
        for(Fragment fragment:fragments){
            if(fragment instanceof NotesFragment && fragment.isVisible()){
                return true;
            }
        }

        return false;
    }

    public static boolean beingAboutFragment(){
        List<Fragment> fragments = mainActivity.getSupportFragmentManager().getFragments();
        for(Fragment fragment:fragments){
            if(fragment instanceof AboutFragment && fragment.isVisible()){
                return true;
            }
        }

        return false;
    }
}
