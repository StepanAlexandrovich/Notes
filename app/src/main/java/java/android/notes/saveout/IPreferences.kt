package java.android.notes.saveout

interface IPreferences {
    fun putStringControl()
    fun getterStringControl():String

    companion object {
        const val PREFERENCES_NAME = "PREF_NOTES"
        const val PREF_NOTES_KEY = "PREF_NOTES_KEY"
    }
}