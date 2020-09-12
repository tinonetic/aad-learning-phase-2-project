package com.tinonetic.gadsleaderboard;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tinonetic.gadsleaderboard.ui.main.SectionsPagerAdapter;
import com.tinonetic.gadsleaderboard.ui.main.submission.SubmitProjectActivity;

// TODO: Splash screen
// TODO: enable StrictMode penalty (Asych course)
// TODO: add translation
// TODO: add unit tests
// TODO: Add splash screen
// TODO: Implement ViewModel
// TODO: Implement Room DB
// TODO: Add search
// TODO: In Room DB create lookups for different badge types - Have a badge type identifier????
public class MainActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    private Button mOpenSubmitFormButton;

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

        mOpenSubmitFormButton = (Button) findViewById(R.id.button_open_form);
        mOpenSubmitFormButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                openSubmitProjectActivity();
            }
        });

        Log.d(TAG, "FINISH onCreate()");
    }

    private void openSubmitProjectActivity() {
        Intent intent = new Intent(this, SubmitProjectActivity.class);
        startActivity(intent);
    }
}