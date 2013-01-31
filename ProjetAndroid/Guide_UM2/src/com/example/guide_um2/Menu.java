package com.example.guide_um2;



import com.example.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

import android.widget.Toast;
import android.view.MenuInflater;
import android.view.MenuItem;


public class Menu extends Activity {

    /** Called when the activity is first created. */
  /*  @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
  
     //   onCreateOptionsMenu();
    }
    */
    public boolean onCreateOptionsMenu(Menu menu) {
     MenuInflater inflater = getMenuInflater();
     inflater.inflate(R.menu.option_menu, (android.view.Menu) menu);
     return true;
}

@Override
public boolean onOptionsItemSelected(MenuItem item)
{

    switch (item.getItemId())
    {
    case R.id.mbatiment:
        // Single menu item is selected do something
        // Ex: launching new activity/screen or show alert message
        Toast.makeText(Menu.this, "batiment", Toast.LENGTH_SHORT).show();
        return true;

    case R.id.mchemin:
        Toast.makeText(Menu.this, "chemin", Toast.LENGTH_SHORT).show();
        return true;

    case R.id.mliste:
        Toast.makeText(Menu.this, "liste", Toast.LENGTH_SHORT).show();
        return true;

    case R.id.mrecherche:
        Toast.makeText(Menu.this, "recherche", Toast.LENGTH_SHORT).show();
        return true;

    case R.id.macceuil:
        Toast.makeText(Menu.this, "Acceuil", Toast.LENGTH_SHORT).show();
        return true;

    case R.id.mquitter:
        Toast.makeText(Menu.this, "Quitter", Toast.LENGTH_SHORT).show();
        return true;

    default:
        return super.onOptionsItemSelected(item);
    }  

}

    
}
