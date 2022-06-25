package java.android.notes.wrapper.helpers

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.android.notes.wrapper.helpers.CreateFragment
import java.android.notes.R
import java.android.notes.wrapper.fragments.extra.CalendarFragment
import java.android.notes.wrapper.fragments.extra.LaunchFragment
import java.android.notes.wrapper.fragments.core.NotesFragment
import java.android.notes.wrapper.fragments.core.NoteFragment
import java.android.notes.wrapper.helpers.Extra
import java.android.notes.wrapper.fragments.extra.SettingsFragment
import java.android.notes.wrapper.fragments.extra.AboutFragment

object CreateFragment {
    /////////////////////////////////////////////////
    fun createFragment(
        appCompatActivity: AppCompatActivity,
        fragment_container: Int,
        fragment: Fragment?
    ) {
        appCompatActivity.supportFragmentManager
            .beginTransaction()
            .replace(fragment_container, fragment!!)
            .commit()
    }

    fun createFragmentWithBackStack(
        appCompatActivity: AppCompatActivity,
        fragment_container: Int,
        fragment: Fragment?
    ) {
        appCompatActivity.supportFragmentManager
            .beginTransaction()
            .replace(fragment_container, fragment!!)
            .addToBackStack(null)
            .commit()
    }

    fun createChildFragment(
        fragmentParent: Fragment,
        fragment_container: Int,
        fragmentChild: Fragment?
    ) {
        fragmentParent.childFragmentManager
            .beginTransaction()
            .replace(fragment_container, fragmentChild!!)
            .commit()
    }

    ////////////////////////////////////////////////////
    fun createCalendarFragment(activity: AppCompatActivity) {
        createFragmentWithBackStack(activity, R.id.fragmentContainer, CalendarFragment())
    }

    fun createLaunchFragment(activity: AppCompatActivity) {
        createFragment(activity, R.id.fragmentContainer, LaunchFragment())
    }

    fun createNotesFragment(activity: AppCompatActivity) {
        createFragmentWithBackStack(activity, R.id.fragmentContainer, NotesFragment())
    }

    fun createNoteFragmentPortrait(activity: AppCompatActivity) {
        createFragmentWithBackStack(activity, R.id.fragmentContainer, NoteFragment())
    }

    fun createNoteFragmentLand(activity: AppCompatActivity) {
        if (Extra.beingNoteFragment(activity)) {
            activity.supportFragmentManager.popBackStack()
        }
        createFragmentWithBackStack(activity, R.id.fragmentContainerNote, NoteFragment())
    }

    // childFragment
    //public static void createNoteFragment (androidx.fragment.app.Fragment fragmentParent){
    //CreateFragment.createChildFragmentWithBackStack(fragmentParent, R.id.fragmentContainerNote,new NoteFragment());
    //}
    fun createSettingsFragment(activity: AppCompatActivity) {
        createFragmentWithBackStack(activity, R.id.fragmentContainer, SettingsFragment())
    }

    fun createAboutFragment(activity: AppCompatActivity) {
        createFragmentWithBackStack(activity, R.id.fragmentContainer, AboutFragment())
    }

    fun createNoteFragent(activity: AppCompatActivity) {
        if (activity.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            createNoteFragmentPortrait(activity)
        } else {
            createNoteFragmentLand(activity)
        }
    }
}