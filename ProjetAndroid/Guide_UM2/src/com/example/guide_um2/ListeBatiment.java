package com.example.guide_um2;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ListeBatiment extends Activity {
	
	String result;
	private ListView maListViewPerso;
	String nom;
	double latP;
	double lonP;
	 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste);
 
        
        //Récupération de la listview créée dans le fichier main.xml
        maListViewPerso = (ListView) findViewById(R.id.listviewperso);
 
        //Création de la ArrayList qui nous permettra de remplire la listView
        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
 
        //declaration de HashMap qui contiendra les informations pour chaque batiment
        HashMap<String, String> map;
 
        
        
    	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
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
			        //recuparation de la reference de l'image apartir de son nom 
			        int resID = getResources().getIdentifier(img, "drawable", "com.example");
			        // on convertit la reference a String car par defaut c Int
			        map.put("img", String.valueOf(resID));
			        
			        // enfin on ajoute cette hashMap dans la arrayList
			        listItem.add(map);
			        
				}

			}catch (JSONException e1) {
				e1.printStackTrace();
			} 
			
			//////////////////
 
        //Création d'un SimpleAdapter qui se chargera de mettre les items présent dans notre list (listItem) dans la vue affichageitem
        SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.affichageitem,
               new String[] {"img", "nom"}, new int[] {R.id.img, R.id.nom});
 
        //On attribut à notre listView l'adapter que l'on vient de créer
        maListViewPerso.setAdapter(mSchedule);
 
        //ajout d'un écouteur d'évènement sur notre listView
        maListViewPerso.setOnItemClickListener(new OnItemClickListener() {

        	@SuppressWarnings("unchecked")
         	public void onItemClick(AdapterView<?> a, View v, int position, long id) {
        		
        		//recuperation de HashMap contenant les infos de l'element cliqué (titre, description, img)
        		HashMap<String, String> map = (HashMap<String, String>) maListViewPerso.getItemAtPosition(position);
        		// recuperation des valeurs 
        		String name = map.get("nom");
        		int img = Integer.parseInt(map.get("img"));
        		String des = map.get("des");
        		double lat = Double.parseDouble(map.get("lat"));
        		double lon = Double.parseDouble(map.get("lon"));
        		
        		
        		//creation d'une intente pour passé a la classe permettant d'afficher la fiche du batiment
        		Intent in = new Intent(ListeBatiment.this, FicheBatiment.class);
        		// passage des valeur
        		in.putExtra("nom", name);
        		in.putExtra("img", img);
        		
        		in.putExtra("lat", lat);
        		in.putExtra("lon", lon);
        		// demarage de l'intente
        		startActivity(in);
        		
        	}
        	
         });
 
    }
    
    
	 
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
		    	Intent in2 = new Intent(ListeBatiment.this, ListeBatiment.class);
		    	startActivity(in2);
		        return true;

		    case R.id.mrecherche:
		    	Intent in3 = new Intent(ListeBatiment.this, Recherche.class);
		    	startActivity(in3);
		        return true;

		    case R.id.macceuil:
		    	Intent in4 = new Intent(ListeBatiment.this, Acceuil.class);
		    	startActivity(in4);
		        return true;

		    case R.id.mbatiment:
		    	Intent in = new Intent(ListeBatiment.this, ListeBatiment.class);
		    	startActivity(in);
			    return true;   

		    case R.id.mquitter:
		    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	            builder.setMessage("Etes vous sûr de vouloir quitter l'application ?")
	                   .setCancelable(false)
	                   .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	                       public void onClick(DialogInterface dialog, int id) {
	                               ListeBatiment.this.finish();
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
