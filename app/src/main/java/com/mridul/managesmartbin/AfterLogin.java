package com.mridul.managesmartbin;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mridul.managesmartbin.placepicker.PickerHome;


/**
 * This class will be used for works related to activity that opens just after LOGIN.
 */

public class AfterLogin extends AppCompatActivity {


    // proceed to activity where you can see location of all installed bins.
    // it is called from  onCreate .
    public Button but1;
    public void markInstalledBins(){
        but1 = (Button)findViewById(R.id.mark_bins);

        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AfterLogin.this,BinMarkers.class);
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
                Intent intent = new Intent(AfterLogin.this,PickerHome.class);
                startActivity(intent);
            }
        });
    }


    // toolbar management starts here
    Toolbar toolbar;

    // navigation drawer management starts here
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);

        markInstalledBins();
        placepickerlayout();

        // for toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar_afterlogin);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("SmartBin Home");

        // for navigation Drawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout_afterlogin);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open_navdrawer_afterlogin,R.string.close_navdrawer_afterlogin);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);






/*
        navigationView = (NavigationView)findViewById(R.id.nav_view_afterlogin);
        //navigationView.inflateMenu(R.menu.afterlogin_navigation_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                //Log.d("setting listener","fdsf");
                switch(item.getItemId()){
                    case R.id.navigation_item1:
                        // handle clicks here
                        Toast.makeText(AfterLogin.this,"navigation_item2 Clicked",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.navigation_item2:
                        // handle clicks here
                        Toast.makeText(AfterLogin.this,"navigation_item2 Clicked",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.navigation_item3:
                        // handle clicks here
                        Toast.makeText(AfterLogin.this,"navigation_item3 Clicked",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.navigation_logout:
                        // handle clicks here
                        startActivity(new Intent(AfterLogin.this,LoginActivity.class));
                        Toast.makeText(AfterLogin.this,"You have successfully Logged Out",Toast.LENGTH_LONG).show();
                        break;
                }
                return true;
            }
        });*/



    }



    // for toolbar menu items

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_afterlogin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // for navigation Drawer
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){

            return true;
        }


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


            // for navigation Drawer
            // not working presently......................................................................................
         /*   case R.id.navigation_item1:
                // handle clicks here
                Toast.makeText(this,"navigation_item1 Clicked",Toast.LENGTH_LONG).show();
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
                break;*/
        }
        return super.onOptionsItemSelected(item);
    }




    // proceed to activity where you can create path connecting bins.
    // it is called directly on clicking button .
    public void gotoPathMakerLayout(View view){
        startActivity(new Intent(this, PathMaker.class));
    }


}

