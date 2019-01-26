package com.example.system.hackathon.Views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.system.hackathon.CustomViewPager;
import com.example.system.hackathon.R;
import com.example.system.hackathon.presenters.FunctionsAdapter;


public class UserHomeActivity extends AppCompatActivity {

    CustomViewPager customViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Feeds");
        setContentView(R.layout.activity_user_home);

        customViewPager = findViewById(R.id.functions_viewpager);

        final BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.feeds:
                        customViewPager.setCurrentItem(0);
                        actionBar.setTitle("Feeds");
                        break;
                    case R.id.settings:
                        customViewPager.setCurrentItem(4);
                        actionBar.setTitle("Settings");
                        break;
                    case R.id.health_consultation:
                        customViewPager.setCurrentItem(3);
                        actionBar.setTitle("Health Consultation");
                        break;
                    case R.id.translation:
                        customViewPager.setCurrentItem(2);
                        actionBar.setTitle("Translation");
                        break;
                    case R.id.fatawi:
                        customViewPager.setCurrentItem(1);
                        actionBar.setTitle("Fatawi");
                        break;
                }

                return true;
            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        FunctionsAdapter functionsAdapter = new FunctionsAdapter(fragmentManager,this);
        customViewPager.setAdapter(functionsAdapter);
        customViewPager.setPagingEnabled(false);


    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
