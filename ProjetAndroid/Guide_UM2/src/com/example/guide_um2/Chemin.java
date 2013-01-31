package com.example.guide_um2;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.R;
import com.example.chemin.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Chemin extends Activity{

	private EditText txt1;
	private EditText txt2;
	String result;
	double lat;
	double lon;
	String nom;
	String salle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chemin); 
        
        txt1 = (EditText) findViewById(R.id.cheminB);
        txt2 = (EditText) findViewById(R.id.cheminS);
        Button cheminB = (Button) findViewById(R.id.searchB);
        
      //  nom= "batiment 1";
//	double lats = this.getIntent().getExtras().getDouble("latitude");
//	double lons = this.getIntent().getExtras().getDouble("longitude");
	
//	double latd = this.getIntent().getExtras().getDouble("latitude1");
//	double lond = this.getIntent().getExtras().getDouble("longitude1");
//	String nom = this.getIntent().getExtras().getString("nom");
	
/*	if(nom != null){
		txt1.setText(nom);
	}*/
	
    
    cheminB.setOnClickListener(new View.OnClickListener() {
   	 public void onClick(View view) {
   		 
   	 
   		nom = txt1.getText().toString();
		salle = txt2.getText().toString();
		
   		 ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
   		 nameValuePairs.add(new BasicNameValuePair ("nom" ,nom));
   		 nameValuePairs.add(new BasicNameValuePair ("salle" ,salle));
   		 String URL = "http://10.0.2.2/Android/bat.php";

    
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
				lat = json_data.getDouble("Latitude");
				Log.e("lat", String.valueOf(lat));
				lon = json_data.getDouble("Longitude");
				Log.e("lon", String.valueOf(lon));
				nom = json_data.getString("nom");
			}
			
			}catch (JSONException e1) {
			e1.printStackTrace();
			}
		
		/////////////////////Affichage du chemin//////////////////////
		Intent in = new Intent(Chemin.this, MainActivity.class);
		in.putExtra("lon", lon);
		in.putExtra("lat", lat);
		startActivity(in);
		
		
   	 	}
    	});
    
    }
}