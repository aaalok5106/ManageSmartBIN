package com.mridul.managesmartbin;

/*import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BinMarkers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bin_markers);
    }
}*/
import android.Manifest;
import android.app.Dialog;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BinMarkers extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap mGoogleMap;
    String url = "http://172.16.187.234/123.php";

    ArrayList<String> lat=new ArrayList<>();    //used in downloader() fn.
    ArrayList<String> lng=new ArrayList<>();    //used in downloader() fn.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //checking if google play services are working...
        if (googleServicesAvailable()) {
            Toast.makeText(this, "Nice Play Services Working...", Toast.LENGTH_LONG).show();
            setContentView(R.layout.activity_bin_markers);

            //initializing maps...
            initMap();
        } else {
            //No maps available here.
        }


    }

    private void initMap() {
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapfragment);
        mapFragment.getMapAsync(this);
    }

    public boolean googleServicesAvailable() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;

        } else if (api.isUserResolvableError(isAvailable)) {
            Dialog dialog = api.getErrorDialog(this, isAvailable, 0);
            dialog.show();
        } else {
            Toast.makeText(this, "Can't connect to Google Play Services", Toast.LENGTH_LONG).show();

        }
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        gotoLocationZoom(25.536014,84.8488763, 3);

    /*    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mGoogleMap.setMyLocationEnabled(true);*/

    }

    private void gotoLocation(double lat, double lng) {
        LatLng latlng = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLng(latlng);
        mGoogleMap.moveCamera(update);
    }

    private void gotoLocationZoom(double lat, double lng, float zoom) {
        LatLng latlng = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latlng, zoom);
        mGoogleMap.moveCamera(update);
    }


    //function used to search for an entered place on the map...

    public void geoLocate(View view) throws IOException {

        EditText et = (EditText)findViewById(R.id.editText);
        String location = et.getText().toString();

        Geocoder gc = new Geocoder(this);
        List<Address> list = gc.getFromLocationName(location, 1);
        Address address = list.get(0);

        String locality = address.getLocality();

        Toast.makeText(this, locality, Toast.LENGTH_LONG).show();

        double lat = address.getLatitude();
        double lng = address.getLongitude();

        gotoLocationZoom(lat, lng, 12);

        MarkerOptions options = new MarkerOptions()
                .title(locality)
                .position(new LatLng(lat, lng))
                .snippet("This is myMarker");
        mGoogleMap.addMarker(options);
    }


    // function used to show the positions of installed bins using markers ...

    public void locateBin(View view) throws IOException{

        //permitting network on existing thread...
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());

        // downloader() is used to download and data from server and store it in Array of String...
        downloader(url);

        int size = lat.size();
        for(int i=0; i<size; i++)
        {

            double latitude = Double.parseDouble(lat.get(i).toString());
            double longitude = Double.parseDouble(lng.get(i).toString());

            Log.v("Latitude is", "" + latitude);
            Log.v("Longitude is", "" + longitude);

            MarkerOptions options = new MarkerOptions()
                    .position(new LatLng(latitude, longitude))
                    .snippet("A Bin is HERE")
                    .title("BIN ID...");
            mGoogleMap.addMarker(options);
        }

        //below commented part was also for placing bin markers...may not work properly

     /*   GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(this).build();

            String placeId = "ChIJ7dgFMOwtbzwRHACl6jeYr2Q" ; //placeid_array.get(i).toString();
            Places.GeoDataApi.getPlaceById(mGoogleApiClient, "ChIJ7dgFMOwtbzwRHACl6jeYr2Q")
                    .setResultCallback(new ResultCallback<PlaceBuffer>() {
                        @Override
                        public void onResult(PlaceBuffer places) {
                            if (places.getStatus().isSuccess()) {
                                final Place myPlace = places.get(0);

                                LatLng queriedLocation = myPlace.getLatLng();
                                Log.v("Latitude is", "" + queriedLocation.latitude);
                                Log.v("Longitude is", "" + queriedLocation.longitude);
                                double lat = queriedLocation.latitude;
                                double lng = queriedLocation.longitude;

                                MarkerOptions options = new MarkerOptions()
                                        .position(new LatLng(lat, lng))
                                        .snippet("A Bin is HERE");
                                mGoogleMap.addMarker(options);
                            }
                            places.release();
                        }
                    });*/
    }




    public void downloader(String address){
        //connect & get string of data.
        InputStream is = null;
        String line = null;

        try {
            URL url = new URL(address);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            is = new BufferedInputStream(con.getInputStream());
            //Log.d("Tag1 : ", "We are in downloader : ");

            BufferedReader br=new BufferedReader(new InputStreamReader(is));

            StringBuilder sb= new StringBuilder();


            if(br != null){
                while((line=br.readLine()) != null){
                    sb.append(line).append('\n');
                }
            }

            String data = sb.toString();
            Log.d("String from server : ", "" + data);

            try {
                JSONArray ja =new JSONArray(data);
                JSONObject jo =null;

                lat.clear();
                lng.clear();

                for(int i=0; i<ja.length(); i++ ){
                    jo = ja.getJSONObject(i);

                    String latitude = jo.getString("lat");
                    String longitude = jo.getString("lng");
                    lat.add(latitude);
                    lng.add(longitude);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}

