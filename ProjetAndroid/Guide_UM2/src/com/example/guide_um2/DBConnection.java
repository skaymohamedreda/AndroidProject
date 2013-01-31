package com.example.guide_um2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class DBConnection extends Activity{

	private String URL1;
	
	
	initHttpRequestTask dd;
	Object bat;
	ArrayList<NameValuePair> nameValuePairs1;
	
	public void DBConnection2(ArrayList<NameValuePair> nameValuePairs, String URL){
	     URL1 = URL;
	     nameValuePairs1 = nameValuePairs;
	     dd = new initHttpRequestTask();
	     dd.execute();
		
	}

	class initHttpRequestTask extends AsyncTask<String, Integer, String> {
		
	    InputStream is = null;
	    private String result;	    
	    private Boolean finish = false;
	    @Override
		protected String doInBackground(String... arg0) {
			 try{
	              HttpClient httpclient = new DefaultHttpClient();
	              HttpPost httppost = new HttpPost(URL1);
	              
	              httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs1));
	              HttpResponse response = httpclient.execute(httppost);
	              HttpEntity entity = response.getEntity();
	              is = entity.getContent();
	      }catch(Exception e){
	    	  Toast.makeText(getApplicationContext(), "Error in http connection"+e.toString(), Toast.LENGTH_LONG).show();
	      }
	      //convert response to string
	      try{
	              BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	              StringBuilder sb = new StringBuilder();
	              
	              String line = null;

	              while ((line = reader.readLine()) != null) {
	                      sb.append(line + "\n");
	                      
	              }
	              
	              is.close();
	         //     result = String.valueOf(sb);
	              result=sb.toString();
	              Log.e("result", result);
	      }catch(Exception e){
	    	  Toast.makeText(getApplicationContext(), "Error converting result "+e.toString(), Toast.LENGTH_LONG).show();
	      }
	      
	      finish = true;
			return result;	
		}
	
		public String getResult()
	    {
	    	return result;
	    }
		public Boolean isfinish()
	    {
	    	return finish;
	    }
		protected void onPostExecute(String result) {
			
			
		}
	
	}


}
