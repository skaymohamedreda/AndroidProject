package com.example.guide_um2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.R;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class MapBatiment extends MapActivity {
	
	private MapView maMap;
	private MapController monControler;
	double lat;
	double lon;
	GeoPoint point;
	
	   @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.map);
	        
	        //recuperation des coordonnées du batiment selectionné
	        lat = this.getIntent().getExtras().getDouble("lat");
	        lon = this.getIntent().getExtras().getDouble("lon");

	        maMap = (MapView)findViewById(R.id.myGmap);
	        maMap.setBuiltInZoomControls(true);
	        
	        point = new GeoPoint(microdegrees(lat), microdegrees(lon));
	        
	        monControler = maMap.getController();
	        monControler.setZoom(19);
	        monControler.setCenter(point);
	        monControler.animateTo(point);
	        
	        //affichage du batiment selectionné sur  map
	        ItemizedOverlayPerso objet2 = new ItemizedOverlayPerso(getResources().getDrawable(R.drawable.marker));
	        objet2.addPoint(point);
	        maMap.getOverlays().add(objet2);
	   }
	
	
	
	    private int microdegrees (double value){
	    	return (int)(value*1000000);
	    }
	   
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
//////////////////////////// Menu ///////////////////////////////
	
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
//  		Intent in1 = new Intent(Map.this, FicheBatiment.class);
//  		startActivity(in1);
		        return true;

		    case R.id.mliste:
  		Intent in2 = new Intent(MapBatiment.this, ListeBatiment.class);
  		startActivity(in2);
		        return true;

		    case R.id.mrecherche:
  		Intent in3 = new Intent(MapBatiment.this, Recherche.class);
  		startActivity(in3);
		        return true;

		    case R.id.macceuil:
  		Intent in4 = new Intent(MapBatiment.this, Acceuil.class);
  		startActivity(in4);
		        return true;

		    case R.id.mbatiment:
	     	Intent in = new Intent(MapBatiment.this, ListeBatiment.class);
	     	startActivity(in);
			    return true;   
		        
		    case R.id.mquitter:
		    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	            builder.setMessage("Etes vous sûr de vouloir quitter l'application ?")
	                   .setCancelable(false)
	                   .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	                       public void onClick(DialogInterface dialog, int id) {
	                               MapBatiment.this.finish();
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


}
