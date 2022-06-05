package java.android.notes.wrapper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import java.android.notes.R;
import java.android.notes.core.Control;
import java.android.notes.wrapper.helpers.CreateFragment;
import java.android.notes.wrapper.helpers.Helper;

public class MainActivity extends AppCompatActivity{
    public static Control control = new Control();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CreateFragment.createLaunchFragment(this);
        Helper.mainActivity = this;

        initDrawer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_launch,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_exit){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initDrawer(){
        DrawerLayout drawer = findViewById(R.id.drawer);
        Toolbar toolbar = findViewById(androidx.appcompat.R.id.action_bar);

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

}