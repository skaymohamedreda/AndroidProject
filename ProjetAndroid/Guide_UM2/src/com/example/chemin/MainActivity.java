package com.example.chemin;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.R;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

public class MainActivity extends MapActivity implements LocationListener {

MapView mapView = null;
MapController mapController;

double long1 = 43.633715*1E6;
double lati1 = 3.864548*1E6;
double long2 = 43.634584*1E6;
double lati2 = 3.86212*1E6;
GeoPoint start, dest;
private MapController monControler;
private LocationManager locManager;
MyLocationOverlay myLocationOverlay;

@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.map);
    mapView = (MapView) findViewById(R.id.myGmap);
    mapView.setBuiltInZoomControls(true);
    
    //////
    
    
    locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, this);
    
    myLocationOverlay = new MyLocationOverlay(this, mapView);
    
    mapView.getOverlays().add(myLocationOverlay);
    
    /////
    lati2 = this.getIntent().getExtras().getDouble("lon")*1E6;
    Log.e("lati1", String.valueOf(lati1));
    long2 = this.getIntent().getExtras().getDouble("lat")*1E6;
    Log.e("long1", String.valueOf(long1));
    
    monControler = mapView.getController(); 
    monControler.setZoom(18);
     DirectionsTask getDirectionsTask = new DirectionsTask();
     getDirectionsTask.execute(new GeoPoint((int)(long1),(int)(lati1)), new GeoPoint((int)(long2),(int)(lati2)));
}

private class DirectionsTask extends AsyncTask<GeoPoint, Void, Route> {

    protected Route doInBackground(GeoPoint...geoPoints) {
        start = geoPoints[0];
        dest =  geoPoints[1];

           Parser parser;
            String jsonURL = "http://maps.google.com/maps/api/directions/json?";
            final StringBuffer sBuf = new StringBuffer(jsonURL);
            sBuf.append("origin=");
            sBuf.append(start.getLatitudeE6()/1E6);
            sBuf.append(',');
            sBuf.append(start.getLongitudeE6()/1E6);
            sBuf.append("&destination=");
            sBuf.append(dest.getLatitudeE6()/1E6);
            sBuf.append(',');
            sBuf.append(dest.getLongitudeE6()/1E6);
            sBuf.append("&sensor=true&mode=driving");
            Log.v("I came in URL", sBuf.toString());
            parser = new GoogleParser(sBuf.toString());
            Route r =  parser.parse();
            return r;
    }

protected void onPostExecute(Route route) {
	
  //  mapView.getController().zoomToSpan((int)lati1,(int)long1);

    RouteOverlay routeOverlay = new RouteOverlay(route, Color.RED);
     mapView.getOverlays().add(routeOverlay);
     mapView.invalidate();
    GeoPoint midllePoint = new GeoPoint((int)((long1+long2)/2),(int)((lati1+lati2)/2));
 
     mapController = mapView.getController();
     mapController.setCenter(midllePoint);
     mapController.animateTo(midllePoint);
     mapController.setZoom(10); // Zoom 1 is world view
     mapView.getMapCenter();
 //    Log.e("route", route.getName());

}

}

@Override
protected boolean isRouteDisplayed() {
	// TODO Auto-generated method stub
	return false;
}

public void onLocationChanged(Location location) {
	
	mapView.getOverlays().clear();

	long1 = location.getLatitude()*1E6;
	lati1  = location.getLongitude()*1E6;
	
	DirectionsTask getDirectionsTask = new DirectionsTask();
    getDirectionsTask.execute(new GeoPoint((int)(long1),(int)(lati1)), new GeoPoint((int)(long2),(int)(lati2)));
	
}

public void onProviderDisabled(String provider) {
	// TODO Auto-generated method stub
	
}

public void onProviderEnabled(String provider) {
	// TODO Auto-generated method stub
	
}

public void onStatusChanged(String provider, int status, Bundle extras) {
	// TODO Auto-generated method stub
	
}
}
