package com.example.guide_um2;


import com.example.R;
import com.example.chemin.MainActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class Acceuil extends Activity implements OnClickListener {


	ImageView logo;
	String nom;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acceuil);
 
      
        
		logo = (ImageView) findViewById(R.id.mm);
		logo.setImageResource(R.drawable.logo_um2) ;
		
		
		Button liste = (Button) findViewById(R.id.liste);
		Button chemin = (Button) findViewById(R.id.chemin);
		Button recherche = (Button) findViewById(R.id.recherche);
		Button batiment = (Button) findViewById(R.id.batiment);
		Button aide = (Button) findViewById(R.id.aide);
    

    liste.setOnClickListener(new View.OnClickListener() {
    	public void onClick(View view) {
    		startActivity(new Intent(Acceuil.this, ListeBatiment.class));
    		}
    });
    
    chemin.setOnClickListener(new View.OnClickListener() {
    	 public void onClick(View view) {
    	    startActivity(new Intent(Acceuil.this, Chemin.class));
    	    /*Intent in = new Intent(Acceuil.this, MainActivity.class);
    	    double long1 = 43.633715*1E6;
    	    double lati1 = 0; 
    	    // = 3.864548*1E6;
    		in.putExtra("long", long1);
    		in.putExtra("lati", lati1);
    		startActivity(in);*/
    	    	}
    });
    
    recherche.setOnClickListener(new View.OnClickListener() {
   	 public void onClick(View view) {
   	    startActivity(new Intent(Acceuil.this, Recherche.class));
   	    	}
   });
    
    batiment.setOnClickListener(new View.OnClickListener() {
      	 public void onClick(View view) {
      	    startActivity(new Intent(Acceuil.this, Map.class));
      	    	}
      });
    
    aide.setOnClickListener(new View.OnClickListener() {
     	 public void onClick(View view) {
    	     startActivity(new Intent(Acceuil.this, DerniereRecherche.class)); 
     	    	}
     });
    
	
}
 
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	/////////////////////// Menu /////////////////////////
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }


	
	public boolean onOptionsItemSelected(MenuItem item)
	{

	    switch (item.getItemId())
	    {
	   
	    case R.id.mquitter:
	    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Etes vous sûr de vouloir quitter l'application ?")
                   .setCancelable(false)
                   .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                       public void onClick(DialogInterface dialog, int id) {
 //                          mp.stop();
                               Acceuil.this.finish();
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
