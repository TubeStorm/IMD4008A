package com.example.favourdiokpo.tutorial4;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int currentFragment = 0;
    public int currentCount = 0;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {



        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {




            Fragment fragment = null;

            switch(item.getItemId()){
                case R.id.home_counter:
                    fragment = new HomeFragment();
                    currentFragment = 0;
                    break;
                case R.id.dashboard_counter:
                    fragment = new DashboardFragment();
                    currentFragment = 1;
                    break;
                case R.id.notifications_counter:
                    fragment = new NotificationsFragment();
                    currentFragment = 2;
                    break;
                case R.id.navigation_settings:
                    fragment = new SettingsFragment();
                    currentFragment = 3;
                    break;

            }
            return loadFragment(fragment);
        }


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        final DataContainer dc = ViewModelProviders.of(this).get(DataContainer.class);
        loadFragment(new HomeFragment());
    }



    private boolean loadFragment(Fragment frag){
        if(frag != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,frag).commit();

            return true;
        }
        else return false;
    }



}
