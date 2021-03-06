package com.mridul.managesmartbin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
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
import android.widget.TextView;
import android.widget.Toast;

import com.mridul.managesmartbin.placepicker.PickerHome;

import static com.mridul.managesmartbin.BackgroundWorker.ACCOUNT_INFO_json_MOB_NO;
import static com.mridul.managesmartbin.BackgroundWorker.ACCOUNT_INFO_json_NAME;
import static com.mridul.managesmartbin.BackgroundWorker.CURRENT_USER_EMAIL;


//import static com.mridul.managesmartbin.BackgroundWorker.OPEN_FRAGMENT_ACCOUNT_INFO;


public class AfterLogin1 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static Toolbar toolbar;

    //private String EMAIL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login1);




        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("ManageBin");
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        displayFragment(R.id.navigation_home);

        /*Intent i = getIntent();
        CURRENT_USER_EMAIL = i.getStringExtra("email");*/
        View header = navigationView.getHeaderView(0);
        TextView t = (TextView)header.findViewById(R.id.textView_nav_header) ;
        t.append("\n"+CURRENT_USER_EMAIL+"");
//        t.setText(""+CURRENT_USER_EMAIL+"");
//        Log.d("email",CURRENT_USER_EMAIL);

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
                //Toast.makeText(this,"Change Password Clicked",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this,ChangePassword.class);
                intent.putExtra("email", CURRENT_USER_EMAIL);
                startActivity(intent);
                break;
            /*case R.id.info:
                // handle clicks here
                Toast.makeText(this,"Info Clicked",Toast.LENGTH_LONG).show();
                break;*/
            case R.id.logout:
                // handle clicks here
                startActivity(new Intent(this,LoginActivity.class));
                Toast.makeText(this,"You have successfully Logged Out "+CURRENT_USER_EMAIL,Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        /*switch(item.getItemId()){
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
                Toast.makeText(this,"You have successfully Logged Out"+EMAIL,Toast.LENGTH_LONG).show();
                break;
            case R.id.account_info:
                // handle clicks here
                String type="accountInfo";

                BackgroundWorker backgroundWorker = new BackgroundWorker(this);
                backgroundWorker.execute(type, EMAIL);
                *//*startActivity(new Intent(this,AccountInfo.class));
                Toast.makeText(this,"Your account info",Toast.LENGTH_LONG).show();*//*
                break;
            case R.id.app_info:
                // handle clicks here
                Toast.makeText(this,"app info Clicked",Toast.LENGTH_LONG).show();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);*/

        displayFragment(item.getItemId());
        return true;
    }


    public void displayFragment(int id){
        android.support.v4.app.Fragment fragment = null;

        switch (id){
            case R.id.navigation_home:
                fragment = new FragmentHome();
                break;
            case R.id.navigation_bin_locations:
                fragment = new FragmentBinMarkers();
                break;
            case R.id.navigation_pickerHome:
                //
                /*Intent intent = new Intent(this, PickerHome.class);
                startActivity(intent);*/
                fragment = new FragmentPlacePickerStart();
                break;
            case R.id.navigation_pathMaker:
                //
                Intent intent1 = new Intent(this, PathMaker.class);
                startActivity(intent1);
                /*fragment = new FragmentPathMaker();*/
                break;
            case R.id.navigation_path_start_pos:
                //
                fragment = new FragmentPlacePickerStart();
                break;
            case R.id.account_info:
                // handle clicks here
               /* String type="accountInfo";

                BackgroundWorker backgroundWorker = new BackgroundWorker(this);
                backgroundWorker.execute(type, EMAIL);

                if (OPEN_FRAGMENT_ACCOUNT_INFO ==1){
                    fragment = new FragmentAccountInfo();
                }else{
                    //
                }*/
                Bundle bundle = new Bundle();
                bundle.putString("email_current_user", CURRENT_USER_EMAIL);
                bundle.putString("mob_no_current_user", ACCOUNT_INFO_json_MOB_NO);
                bundle.putString("name_current_user", ACCOUNT_INFO_json_NAME);

                fragment = new FragmentAccountInfo();
                fragment.setArguments(bundle);

                break;
            case R.id.app_info:
                // handle clicks here
                Toast.makeText(this,"app info Clicked",Toast.LENGTH_LONG).show();
                break;
        }


        if(fragment != null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_after_login1, fragment );
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }



    // proceed to activity where you can see location of all installed bins.


    protected void markInstalledBins(View view){
        Intent intent = new Intent(AfterLogin1.this,BinMarkers.class);
        startActivity(intent);
    }

    // proceed to activity where you can install a bin on required location.


    protected void placepickerlayout(View view){
        /*Intent intent = new Intent(AfterLogin1.this,PickerHome.class);
        startActivity(intent);*/
    }



    protected void gotoPathMakerLayout(View view){
        startActivity(new Intent(this, PathMaker.class));
    }

}
