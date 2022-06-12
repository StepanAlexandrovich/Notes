package java.android.notes.wrapper.helpers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import java.android.notes.wrapper.fragments.core.NoteFragment;
import java.util.List;

public class Extra {

    public static boolean beingNoteFragment(AppCompatActivity activity){
        List<Fragment> fragments = activity.getSupportFragmentManager().getFragments();
        for(Fragment fragment:fragments){
            if(fragment instanceof NoteFragment && fragment.isVisible()){
                return true;
            }
        }

        return false;
    }

    public static void initToolbar(AppCompatActivity activity,int toolbarId){
        Toolbar toolbar = activity.findViewById(toolbarId);
        activity.setSupportActionBar(toolbar);
    }

}
