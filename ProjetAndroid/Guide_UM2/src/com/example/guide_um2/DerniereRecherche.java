package com.example.guide_um2;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class DerniereRecherche extends Activity {
	
	String result;
	private ListView maListViewPerso;
	String nom;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.liste);
	
		maListViewPerso = (ListView) findViewById(R.id.listviewperso);
		 
        //Création de la ArrayList qui nous permettra de remplire la listView
        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
 
        //declaration de HashMap qui contiendra les informations pour chaque batiment
        HashMap<String, String> map;
 
        
        
    	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        String URL = "http://10.0.2.2/Android/search.php";
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
					//declaration de HashMap qui contiendra les informations pour chaque batiment
					map = new HashMap<String, String>();
					//insertion des éléments dans la HashMap
			        map.put("nom", nom);
			        
			        // enfin on ajoute cette hashMap dans la arrayList
			        listItem.add(map);
			        
				}

			}catch (JSONException e1) {
				e1.printStackTrace();
			} 
			
			//////////////////
 
        //Création d'un SimpleAdapter qui se chargera de mettre les items présent dans notre list (listItem) dans la vue affichageitem
        SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.affichageitem,
               new String[] {"nom"}, new int[] {R.id.nom});
 
        //On attribut à notre listView l'adapter que l'on vient de créer
        maListViewPerso.setAdapter(mSchedule);
 

 
    }
		
		

}
