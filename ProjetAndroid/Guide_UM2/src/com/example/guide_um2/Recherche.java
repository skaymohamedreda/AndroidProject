package com.example.guide_um2;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.R;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Recherche extends Activity implements OnClickListener {
	
	String result;
	private ListView maListViewPerso;
	private EditText recherche;
	private EditText salle;
	String nom;
	String a;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recherche);
 
        recherche  = (EditText) findViewById(R.id.search);
        salle = (EditText) findViewById(R.id.src);
        maListViewPerso = (ListView) findViewById(R.id.recherchelist);
        ((Button) findViewById(R.id.rechercheBtn)).setOnClickListener(this);
        
        
    }

    
	public void onClick(View arg0) {

		String search = recherche.getText().toString();
		String search2 = salle.getText().toString();
        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
 
        HashMap<String, String> map;
        
 		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
 		nameValuePairs.add(new BasicNameValuePair ("nom" ,search));
 		nameValuePairs.add(new BasicNameValuePair ("salle" ,search2));
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
				    nom = json_data.getString("nom");
					String img = json_data.getString("id_image");
					String des = json_data.getString("description");
					double lat = json_data.getDouble("Latitude");
					double lon = json_data.getDouble("Longitude");
					
					//declaration de HashMap qui contiendra les informations pour chaque batiment
					map = new HashMap<String, String>();
					//insertion des éléments dans la HashMap
			        map.put("nom", nom);
			        map.put("des", des);
			        map.put("lat", String.valueOf(lat));
			        map.put("lon", String.valueOf(lon));
			        int resID = getResources().getIdentifier(img, "drawable", "com.example");
			        // on convertit la reference a String car par defaut c Int
			        map.put("img", String.valueOf(resID));
			        
			        // enfin on ajoute cette hashMap dans la arrayList
			        listItem.add(map);
			        
				}

			}catch (JSONException e1) {
				e1.printStackTrace();
			} 
			
			///////////enregistrer historique/////////////////////////
			if (search != null){

				 Log.e("a", search);
				 nameValuePairs.add(new BasicNameValuePair ("a" ,search));
			}
			if (search2 != null){
				 
				 Log.e("a", search2);
				 nameValuePairs.add(new BasicNameValuePair ("a" ,search2));
				 
			}
			//////////////////////////////////////////////////////////////
			
			
			String URL1 = "http://10.0.2.2/Android/recherche.php";
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
			////////////////////
 
        SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.affichageitem,
               new String[] {"img", "nom"}, new int[] {R.id.img, R.id.nom});
 
        maListViewPerso.setAdapter(mSchedule);
 
        maListViewPerso.setOnItemClickListener(new OnItemClickListener() {

        	@SuppressWarnings("unchecked")
         	public void onItemClick(AdapterView<?> a, View v, int position, long id) {
        		

        		HashMap<String, String> map = (HashMap<String, String>) maListViewPerso.getItemAtPosition(position);

        		int img = Integer.parseInt(map.get("img"));
        		String des = map.get("des");
        		double lat = Double.parseDouble(map.get("lat"));
        		double lon = Double.parseDouble(map.get("lon"));

        		Intent in = new Intent(Recherche.this, FicheBatiment.class);
        		in.putExtra("nom", nom);
        		in.putExtra("img", img);
        		in.putExtra("lon", lon);
        		in.putExtra("lat", lat);
        		startActivity(in);
        		
        	}
        	
         });
		
	}
	
	
	//////////////////////// Menu ///////////////////////////////////
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
		    	Intent intent = new Intent();
	            intent.setComponent(new ComponentName("com.example.itineraire.Itineraire", "com.example.itineraire.Itineraire.MainActivity"));
	                        
	            startActivity(intent);
//     		Intent in1 = new Intent(Map.this, FicheBatiment.class);
//     		startActivity(in1);
		        return true;

		    case R.id.mliste:
     		Intent in2 = new Intent(Recherche.this, ListeBatiment.class);
     		startActivity(in2);
		        return true;

		    case R.id.mbatiment:
     		Intent in3 = new Intent(Recherche.this, Map.class);
     		startActivity(in3);
		        return true;

		    case R.id.macceuil:
     		Intent in4 = new Intent(Recherche.this, Acceuil.class);
     		startActivity(in4);
		        return true;

		    case R.id.mquitter:
		    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	            builder.setMessage("Etes vous sûr de vouloir quitter l'application ?")
	                   .setCancelable(false)
	                   .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	                       public void onClick(DialogInterface dialog, int id) {
	                               Recherche.this.finish();
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
