package java.android.notes.wrapper;

import android.view.Menu;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.List;

public class Helper {
    static MainActivity mainActivity;

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
