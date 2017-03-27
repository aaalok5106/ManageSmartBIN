package com.mridul.managesmartbin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.mridul.managesmartbin.placepicker.PickerHome;

public class AfterLogin1 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    // proceed to activity where you can see location of all installed bins.
    // it is called from  onCreate .
    public Button but1;
    public void markInstalledBins(){
        but1 = (Button)findViewById(R.id.mark_bins);

        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AfterLogin1.this,BinMarkers.class);
                startActivity(intent);
            }
        });
    }

    // proceed to activity where you can install a bin on required location.
    // it is called from  onCreate .
    public Button but2;
    public void placepickerlayout(){
        but2 = (Button)findViewById(R.id.button_place_picker);

        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AfterLogin1.this,PickerHome.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login1);



        markInstalledBins();
        placepickerlayout();




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("ManageBin");
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.after_login1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button
        int id = item.getItemId();

        switch(id){

            // for toolbar menu items
            case R.id.change_password:
                // handle clicks here
                Toast.makeText(this,"Change Password Clicked",Toast.LENGTH_LONG).show();
                break;
            case R.id.info:
                // handle clicks here
                Toast.makeText(this,"Info Clicked",Toast.LENGTH_LONG).show();
                break;
            case R.id.logout:
                // handle clicks here
                startActivity(new Intent(this,LoginActivity.class));
                Toast.makeText(this,"You have successfully Logged Out",Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch(item.getItemId()){
            case R.id.navigation_item1:
                // handle clicks here
                Toast.makeText(this,"navigation_item2 Clicked",Toast.LENGTH_LONG).show();
                break;
            case R.id.navigation_item2:
                // handle clicks here
                Toast.makeText(this,"navigation_item2 Clicked",Toast.LENGTH_LONG).show();
                break;
            case R.id.navigation_item3:
                // handle clicks here
                Toast.makeText(this,"navigation_item3 Clicked",Toast.LENGTH_LONG).show();
                break;
            case R.id.navigation_logout:
                // handle clicks here
                startActivity(new Intent(this,LoginActivity.class));
                Toast.makeText(this,"You have successfully Logged Out",Toast.LENGTH_LONG).show();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public void gotoPathMakerLayout(View view){
        startActivity(new Intent(this, PathMaker.class));
    }

}
