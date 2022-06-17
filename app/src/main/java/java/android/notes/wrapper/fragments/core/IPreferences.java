package java.android.notes.wrapper.fragments.core;

public interface IPreferences {
    String PREFERENCES_NAME = "PREF_NOTES";
    String PREF_NOTES_KEY = "PREF_NOTES_KEY";

    String getStringControl();
    void putStringControl();
}
