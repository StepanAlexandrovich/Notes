package java.android.notes.activity

import androidx.appcompat.app.AppCompatActivity
import java.android.notes.saveout.IPreferences
import android.os.Bundle
import java.android.notes.R
import com.google.gson.GsonBuilder
import java.android.notes.wrapper.helpers.CreateFragment
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.widget.Toolbar
import java.android.notes.core.Control

class MainActivity : AppCompatActivity(), IDatatSourseHandler, IPreferences {
    private val control:Control = Control()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CreateFragment.createLaunchFragment(this)
    }

    fun initDrawer() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer)
        val toolbar = findViewById<Toolbar>(R.id.toolbarNotes)
        val toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_settings -> {
                    CreateFragment.createSettingsFragment(this@MainActivity)
                    drawer.close()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.action_about -> {
                    CreateFragment.createAboutFragment(this@MainActivity)
                    drawer.close()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
    }

    override fun putStringControl() {
        val stringControl = GsonBuilder().create().toJson(control)
        val preferences = getSharedPreferences(IPreferences.PREFERENCES_NAME, MODE_PRIVATE)
        preferences.edit().putString(IPreferences.PREF_NOTES_KEY, stringControl).apply()
    }

    override fun getterStringControl(): String {
        val preferences = getSharedPreferences(IPreferences.PREFERENCES_NAME, MODE_PRIVATE)
        return preferences.getString(IPreferences.PREF_NOTES_KEY, "default")!!
    }

    override fun getterControl(): Control {
        return control
    }
}