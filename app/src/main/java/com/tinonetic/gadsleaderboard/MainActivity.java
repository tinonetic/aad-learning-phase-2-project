package com.tinonetic.gadsleaderboard;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.tinonetic.gadsleaderboard.api.ApiClient;
import com.tinonetic.gadsleaderboard.ui.main.SectionsPagerAdapter;

import java.net.URL;

// TODO: Splash screen
// TODO: Progress bar/animation
// TODO: enable StrictMode penalty (Asych course)
// TODO: Handle aerplane mode
// TODO: add translation
// TODO: add unit tests
// TODO: Fix layouts and widget designs (rounded buttons etc)
// TODO: Add splash screen
// TODO: Implement ViewModel
// TODO: Implement Room DB
public class MainActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "START onCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        Log.d(TAG, "FINISH onCreate()");
    }
}