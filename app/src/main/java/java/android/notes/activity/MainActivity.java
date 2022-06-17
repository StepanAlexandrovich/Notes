package java.android.notes.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.GsonBuilder;

import java.android.notes.R;
import java.android.notes.core.Control;
import java.android.notes.wrapper.fragments.core.IPreferences;
import java.android.notes.wrapper.helpers.CreateFragment;
import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity implements IDatatSourseHandler, IPreferences {
    private Control control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String stringControl = getStringControl();
        if(stringControl.equals("default")){
            control = new Control();
        }else{
            control = new GsonBuilder().create().fromJson(stringControl,Control.class);
        }

        CreateFragment.createLaunchFragment(this);
    }

    public void initDrawer(){
        DrawerLayout drawer = findViewById(R.id.drawer);
        Toolbar toolbar = findViewById(R.id.toolbarNotes);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_settings:
                        CreateFragment.createSettingsFragment(MainActivity.this);
                        drawer.close();
                        return true;
                    case R.id.action_about:
                        CreateFragment.createAboutFragment(MainActivity.this);
                        drawer.close();
                        return true;
                }

                return false;
            }
        });
    }

    @Override
    public Control getControl() {
        return control;
    }


    @Override
    public String getStringControl() {
        SharedPreferences preferences = getSharedPreferences(PREFERENCES_NAME,MODE_PRIVATE);
        return preferences.getString(PREF_NOTES_KEY,"default");
    }

    @Override
    public void putStringControl() {
        String stringControl = new GsonBuilder().create().toJson(control);
        SharedPreferences preferences = getSharedPreferences(PREFERENCES_NAME,MODE_PRIVATE);
        preferences.edit().putString(PREF_NOTES_KEY,stringControl).apply();
    }

}