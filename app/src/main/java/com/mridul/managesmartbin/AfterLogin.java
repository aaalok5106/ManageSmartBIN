package com.mridul.managesmartbin;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.location.places.ui.PlacePicker;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);

        markInstalledBins();
        placepickerlayout();
    }


    // proceed to activity where you can create path connecting bins.
    // it is called directly on clicking button .
    public void gotoPathMakerLayout(View view){
        startActivity(new Intent(this, PathMaker.class));
    }
}

