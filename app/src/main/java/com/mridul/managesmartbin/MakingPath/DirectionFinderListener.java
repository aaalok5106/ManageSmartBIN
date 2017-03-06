package com.mridul.managesmartbin.MakingPath;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import com.mridul.managesmartbin.MakingPath.Route;

/**
 * Created by Mridul on 05-03-2017.
 */

public interface DirectionFinderListener {

    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Route> route);

}
