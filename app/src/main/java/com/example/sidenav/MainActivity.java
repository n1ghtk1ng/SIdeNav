package com.example.sidenav;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        NavigationView navigationView = findViewById(R.id.nv);
        setupDrawerContent(navigationView);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void selectItemDrawer(MenuItem item){
        Fragment fragment = null;
        Class fragmentClass;

        switch (item.getItemId()){
            case R.id.events:
                    fragmentClass = MyEvents.class;
                    break;
            case R.id.invites:
                    fragmentClass = MyInvites.class;
                    break;
            case R.id.create_event:
                    fragmentClass = CreateEvent.class;
                    break;
            case R.id.profile:
                    fragmentClass = Profile.class;
                    break;
            default:fragmentClass = MyEvents.class;
        }
        try{
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e){
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.framecontent, fragment).commit();

        item.setChecked(true);
        setTitle(item.getTitle());
        mDrawerLayout.closeDrawers();
    }

    private void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                selectItemDrawer(menuItem);
                return true;
            }
        });
    }


    public void fun(String city) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction t1 = manager.beginTransaction();

        MyInvites myInvites = new MyInvites();
        Bundle b1 = new Bundle();
        b1.putString("city", city);
        myInvites.setArguments(b1);
        t1.replace(R.id.framecontent, myInvites);
        t1.commit();

    }
}
