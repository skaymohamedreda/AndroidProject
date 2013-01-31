package com.example.guide_um2;


import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.R;
import com.example.chemin.MainActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class FicheBatiment extends Activity {
	
	private TextView txt1;
	private TextView txt3;
	ImageView image;
	double lat;
	double lon;
	double lat1;
	double lon1;
	String result;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fiche_batiment);

		/* formulaire */
		txt1 = (TextView) findViewById(R.id.txt2 );
		txt3 = (TextView) findViewById(R.id.txt3 );
		image = (ImageView) findViewById(R.id.img);
		txt3.setText("Dans ce batiment vous trouverez les compartiments suivants : \n\n");
		
		//recuperation des information de l'activity ListeBatiment
		txt1.setText(this.getIntent().getExtras().getString("nom"));
//		txt3.setText(this.getIntent().getExtras().getString("des"));
		image.setImageResource(this.getIntent().getExtras().getInt("img"));
		
		lat = this.getIntent().getExtras().getDouble("lat");
		lon = this.getIntent().getExtras().getDouble("lon");
		
		//////////
		
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
 		nameValuePairs.add(new BasicNameValuePair ("lon" ,String.valueOf(lon)));
 		nameValuePairs.add(new BasicNameValuePair ("lat" ,String.valueOf(lat)));
 		
        String URL = "http://10.0.2.2/Android/description.php";

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

				JSONArray jArray = new JSONArray(result);
				JSONObject json_data=null;
				for(int j=0;j<jArray.length();j++){
					json_data = jArray.getJSONObject(j);
				    String nom = json_data.getString("nom");
				    Log.e("erreur_fiche", nom);
				    //ajouter plusieurs texts au TextView 
					txt3.append("> " + nom + ".\n");
				}
			}catch (JSONException e1) {
				e1.printStackTrace();
			} 
		/////////
		
		/////////////////////////Boutton Voir sur Map///////////////////////////
		Button voirMap = (Button) findViewById(R.id.voirMap);
		
		voirMap.setOnClickListener(new View.OnClickListener() {
	      	 public void onClick(View view) {
	     		Intent in = new Intent(FicheBatiment.this, MapBatiment.class);
	    		// passage des valeur
	    		in.putExtra("lat", lat);
	    		in.putExtra("lon", lon);
	    		// demarage de l'intente
	    		startActivity(in);
	      	    	}
	      });
		
		
		/////////////////////////Boutton Voir sur Map///////////////////////////
		Button voirChemin = (Button) findViewById(R.id.voirChemin);
		
		voirChemin.setOnClickListener(new View.OnClickListener() {
	      	 public void onClick(View view) {
	     		Intent in = new Intent(FicheBatiment.this, MainActivity.class);
	    		in.putExtra("lat", lat);
	    		in.putExtra("lon", lon);
	    		startActivity(in);
	      	    	}
	      });
		
		///////////////////////////////////////////////////////////////////////////
	}

    
    
	
	/////////////////////// Menu /////////////////////////////////
	
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
     		Intent in1 = new Intent(FicheBatiment.this, MainActivity.class);
     		in1.putExtra("lat", lat);
    		in1.putExtra("lon", lon);
		        return true;

		    case R.id.mliste:
     		Intent in2 = new Intent(FicheBatiment.this, ListeBatiment.class);
     		startActivity(in2);
		        return true;

		    case R.id.mrecherche:
     		Intent in3 = new Intent(FicheBatiment.this, Recherche.class);
     		startActivity(in3);
		        return true;

		    case R.id.macceuil:
     		Intent in4 = new Intent(FicheBatiment.this, Acceuil.class);
     		startActivity(in4);
		        return true;

		    case R.id.mbatiment:
	     	Intent in = new Intent(FicheBatiment.this, ListeBatiment.class);
	     	startActivity(in);
			    return true;   
		        
		    case R.id.mquitter:
		    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	            builder.setMessage("Etes vous sûr de vouloir quitter l'application ?")
	                   .setCancelable(false)
	                   .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	                       public void onClick(DialogInterface dialog, int id) {
	                               FicheBatiment.this.finish();
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
