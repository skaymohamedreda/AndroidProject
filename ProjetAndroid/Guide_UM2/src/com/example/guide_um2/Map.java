package com.example.guide_um2;


import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.R;
import com.example.chemin.MainActivity;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * cette classe permet d'indiquer a l'utilisateur le batiment le plus proche de lui
 * pour ce faire et pour eviter une lourde recherche dans la BD, on va passer par 3 etapes :
 * etape 1 : calculer la distance entre l'utilisateur et le centre des batiments et selectiooner les 4 plus proche
 * etape 2 : calculer la distance entre l'utilisateur et les point lateral des 4 batiments puis selectionner le plus proche
 * etape 3 : extraire les informations correspondante du batiment dont appartient le point le plus proche
 * 
 * N.B:- on a utiliser les point lateral en plus des central afin d'eviter que l'application indique a l'utilisateur
 * 		qu'il est pres du centre d'un batiment au temps ou il plus proche a un point lateral d'un autre batiment.
 * 	   - le tableau dont les distances sont stocké est un tableau de 2 dimension dont chaque distance correspond a 
 *     un id du batiment.
 * 
 *
 * pour afficher le chemin on aura besoin d'un autre type de point : le point de la route le plus proche au batiment
 *
 **/


public class Map extends MapActivity implements LocationListener {

	
	private LocationManager locManager;
	private MapView maMap;
	private MapController monControler;
	MyLocationOverlay myLocationOverlay;
	
	double latitude = 43.63154;
	double longitude = 3.861027;
	double latitude1;
	double longitude1;
	GeoPoint point;
	
	int id; // id batiment
	String nom; // nom batiment
	JSONArray jArray; // table de resultat recuperer par Json
	String result;
	double[][] batiment = new double[35][2];  // tableau pour stocker la distance entre ta position et celle des batiments
	Location Sloc = new Location("");
	Location Dloc = new Location("");
	 
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        
        maMap = (MapView)findViewById(R.id.myGmap);
        maMap.setBuiltInZoomControls(true);
        
        point = new GeoPoint(microdegrees(latitude), microdegrees(longitude));
        
        monControler = maMap.getController(); 
        monControler.setZoom(19);
        monControler.setCenter(point);
        monControler.animateTo(point);
        
        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, this);
        
        myLocationOverlay = new MyLocationOverlay(this, maMap);
        
        maMap.getOverlays().add(myLocationOverlay);
        
        //// situer la position sur la Map
        myLocationOverlay.runOnFirstFix(new Runnable() {
            public void run() {
                monControler.animateTo(myLocationOverlay.getMyLocation());
            }
        });

        //afficher le marqueur sur le point
        ItemizedOverlayPerso objet1 = new ItemizedOverlayPerso(getResources().getDrawable(R.drawable.marker));
		objet1.addPoint(point);
		maMap.getOverlays().add(objet1);
    
    
    }
    
    
/////////////////////////////////////////////////////////////////////////   
    
    private int microdegrees (double value){
    	return (int)(value*1000000);
    }
   
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
/////////////////////////////////////////////////////////////////////////	


////////////////////////Quand la position est changé ////////////////////////////
	
	public void onLocationChanged(Location location) {
		maMap.getOverlays().clear();
		GeoPoint point = new GeoPoint(microdegrees(location.getLatitude()), microdegrees(location.getLongitude()));
		latitude = location.getLatitude();
		longitude  = location.getLongitude();
		monControler.animateTo(point);
		monControler.setCenter(point);

		ItemizedOverlayPerso objet1 = new ItemizedOverlayPerso(getResources().getDrawable(R.drawable.marker));
		objet1.addPoint(point);
		maMap.getOverlays().add(objet1);

		try {
			PositionBatiment ();
		} catch (JSONException e) {
			Toast.makeText(getApplicationContext(), "Erreur lors de la recherche du batiment le plus proche", Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
			
	}

////////////////////Calculer la distance entre 2 points////////////////////////
	
	public static double getDistance(double lat_s,double lng_s, double lat_d, double lon_d){
		  int Radius = 6371000; //Rayon de la tierra
		  double lat1 = lat_s / 1E6;
		  double lat2 = lat_d / 1E6;
		  double lon1 = lng_s / 1E6;
		  double lon2 = lon_d / 1E6;
		  double dLat = Math.toRadians(lat2-lat1);
		  double dLon = Math.toRadians(lon2-lon1);
		  double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon /2) * Math.sin(dLon/2);
		  double c = 2 * Math.asin(Math.sqrt(a));
		  return (double) (Radius * c);  

		 }
	
	/////////////////////////////////////////////////////////////////////////
	
////////////////////////Trouver le batiment le plus proche/////////////////////	
	
	
	
	public void PositionBatiment () throws JSONException{
		
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		String URL = "http://10.0.2.2/Android/batiment.php";
		//URL: cherche le point centre de tous les batiments
		//calculer la distance pour localiser les batiments proche
		try{
			DBConnection DBC = new DBConnection();
			DBC.DBConnection2(nameValuePairs, URL);

			while (!DBC.dd.isfinish()){
				Thread.sleep (100);
			}
		 
			result = DBC.dd.getResult();
			

		}catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Erreur d'acces BD", Toast.LENGTH_LONG).show();
		}

		try{
			// extraction 
		      jArray = new JSONArray(result);
		      JSONObject json_data=null;
		      for(int i=0;i<jArray.length();i++){
		             json_data = jArray.getJSONObject(i);
		             longitude1 = json_data.getDouble("Longitude");
		             latitude1 = json_data.getDouble("Latitude");
		             id = json_data.getInt("Id_Batiment");
		             nom = json_data.getString("nom");

		             GeoPoint PointBatiment = new GeoPoint(microdegrees(latitude1), microdegrees(longitude1));
		     		double dist = getDistance(latitude,longitude, latitude1, longitude1);
		     		   batiment[i][0]= id;
		               batiment[i][1]=dist; 
		      	}
		      
		      // trier le tableau selon la colonne des distances
		      batiment = trier(batiment);
		  	for (int k=0;k<10;k++){
				int d = (int) batiment [k][0];
				String n = String.valueOf(d);
				Log.i("tab 1", n);
		  	}
	 
		}catch (ParseException e1) {
			e1.printStackTrace();
			}
		BatimentProche(batiment);
	}


	
//////////////////////////trouver le batiment le plus proche//////////////////////////
	
public void BatimentProche(double[][] batiment) throws JSONException{
	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

	
	//on parcours les 4 premier elements du tableau
	for (int i=0;i<4;i++){
		int idb = (int) batiment [i][0];
		nameValuePairs.add(new BasicNameValuePair ("Id_batiment" ,String.valueOf(idb)));
		String URL = "http://10.0.2.2/Android/batiment_entourage.php";
//		String URL = "http://10.0.2.2/Android/batiment_entourage.php?Id_batiment="+Integer.toString(idb); // recherche des 4 batiments
		//URL: chercher les points lateral des 4 batiments les plus proche
		//calculer la distence avec ces point les stocker dans un tableau
		//trier le tableau par ordre croissant 
		Log.e("erreur result1", "1");
		try{
			DBConnection DBC = new DBConnection();
			DBC.DBConnection2(nameValuePairs, URL);

			while (!DBC.dd.isfinish()){
				Thread.sleep (100);
			}
		 
			result = DBC.dd.getResult();
			Log.e("erreur result", result);
		}catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Erreur d'acces BD", Toast.LENGTH_LONG).show();
		}
		//////
		
		try{
			jArray = new JSONArray(result);
			Log.e("erreur result2", "2");
			JSONObject json_data=null;
			for(int j=0;j<jArray.length();j++){
				json_data = jArray.getJSONObject(j);
				longitude1 = json_data.getDouble("Longitude");
				latitude1 = json_data.getDouble("Latitude");
				id = json_data.getInt("Id_Batiment");                                 
				Log.e("erreur result2", "3");
				GeoPoint PointBatiment1 = new GeoPoint(microdegrees(latitude1), microdegrees(longitude1));

				double dist = getDistance(latitude,longitude, latitude1, longitude1);
   
				batiment[j][0]=id;
				batiment[j][1]=dist;
			}

		}catch (ParseException e1) {
			e1.printStackTrace();
			Toast.makeText(getApplicationContext(), "2eme erreur", Toast.LENGTH_LONG).show();
		}                                                
	}
    
	batiment = trier(batiment);
	// le premier element du tableau contient la distance et l'id du batiment le plus proche 
    // recuperation du 1er enregistrement du tableau-> le plus proche
    int db = (int) batiment [0][0];
    
    String URL1 = "http://10.0.2.2/Android/batiment_proche.php?Id_batiment="+Integer.toString(db); // chrecher le batiment
	//URL : chercher les informations du batiment le plus proche et l'afficher sur l'ecran

    try{
		DBConnection DBC = new DBConnection();
		DBC.DBConnection2(nameValuePairs, URL1);
		while (!DBC.dd.isfinish()){
			Thread.sleep (100);
		}
		result = DBC.dd.getResult();
	}catch (Exception e) {
		Toast.makeText(getApplicationContext(), "Erreur d'acces BD", Toast.LENGTH_LONG).show();
	}
    jArray = new JSONArray(result);
    JSONObject json_data=null;
    for(int j=0;j<jArray.length();j++){
    	json_data = jArray.getJSONObject(j);
    	longitude1 = json_data.getDouble("Longitude");
    	latitude1 = json_data.getDouble("Latitude");
    	id = json_data.getInt("Id");
    	nom = json_data.getString("nom");
    	Log.e("nom_bat", nom);

}  
	      	// affichage sur map du batiment le plus proche
     Toast.makeText(getApplicationContext(), "le batiment le plus proche a vous est : " + nom, Toast.LENGTH_LONG).show();
     GeoPoint PointBatiment = new GeoPoint(microdegrees(latitude1), microdegrees(longitude1));
     ItemizedOverlayPerso objet2 = new ItemizedOverlayPerso(getResources().getDrawable(R.drawable.marker1));
     objet2.addPoint(PointBatiment);
     maMap.getOverlays().add(objet2);

	}

	
	
/////////comparer une colonne pour permet de trier le tableau selon une colonne /////////	
	
	 public double[][] trier(double[][] bat) {
			int N= bat.length-1;
	        for(int k=0;k<N;k++) {
	            for(int f=0;f<N-k;f++) {
	                if (bat[f][1]>bat[f+1][1]) {
	                    double aux0;
	                    double aux1;
	                    aux0=bat[f][0];
	                    aux1=bat[f][1];
	                    bat[f][0]=bat[f+1][0];
	                    bat[f][1]=bat[f+1][1];
	                    bat[f+1][0]=aux0;
	                    bat[f+1][1]=aux1;
	                }
	            }
	        }
	        return bat;
	    }
	
/////////////////////Afficher marqueur //////////////////////////////////
	
	 public void positionb(GeoPoint PointBatiment){
		 
         ItemizedOverlayPerso objt = new ItemizedOverlayPerso(getResources().getDrawable(R.drawable.marker));
         objt.addPoint(PointBatiment);
  		 maMap.getOverlays().add(objt);
		 
	 }
/////////////////////////// Menu ////////////////////////////////////
	 
	 
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        getMenuInflater().inflate(R.menu.option_menu, menu);
	        return true;
	    }
	 
	 public boolean onOptionsItemSelected(MenuItem item)
		{

		    switch (item.getItemId())
		    {
		   

		    case R.id.mchemin:
        		Intent in1 = new Intent(Map.this, Chemin.class);
        		in1.putExtra("latitude", latitude);
        		in1.putExtra("longitude", longitude);
        		startActivity(in1);
		        return true;

		    case R.id.mliste:
        		Intent in2 = new Intent(Map.this, ListeBatiment.class);
        		startActivity(in2);
		        return true;

		    case R.id.mrecherche:
        		Intent in3 = new Intent(Map.this, Recherche.class);
        		startActivity(in3);
		        return true;

		    case R.id.macceuil:
        		Intent in4 = new Intent(Map.this, Acceuil.class);
        		startActivity(in4);
		        return true;

		    case R.id.mquitter:
		    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	            builder.setMessage("Etes vous sûr de vouloir quitter l'application ?")
	                   .setCancelable(false)
	                   .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	                       public void onClick(DialogInterface dialog, int id) {
	                               Map.this.finish();
	                       }
	                   })
	                   .setNegativeButton("No", new DialogInterface.OnClickListener() {
	                       public void onClick(DialogInterface dialog, int id) {
	                            dialog.cancel();
	                       }
	                   });
	            AlertDialog alert = builder.create();
	            alert.show();
	            return true;

		    default:
		        return super.onOptionsItemSelected(item);
		    }  

		}
	 
	////////////////////////////////////////
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
