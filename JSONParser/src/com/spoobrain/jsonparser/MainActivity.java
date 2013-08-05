package com.spoobrain.jsonparser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	static String yahooStockURL = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quote%20where%20symbol%20in%20(%22GOOG%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=cbfunc";
	
	static String daysLow = "";
	static String daysHigh = "";
	static String symbol = "";
	static String change = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		new MyAsyncTask().execute();
		
	}

	private class MyAsyncTask extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... arg0) {

			// To be able to download from YQL
			DefaultHttpClient client = new DefaultHttpClient(new BasicHttpParams());
		
			HttpPost post = new HttpPost(yahooStockURL);
			
			// To specify json
			post.setHeader("Content-type", "application/json");
			
			InputStream input = null;
			
			String result = "";
			
			try {
				
				HttpResponse response = client.execute(post);
				
				// Will hold all the content from URL
				HttpEntity entity = response.getEntity();
				input = entity.getContent();
				
				BufferedReader br = new BufferedReader(new InputStreamReader(input, "UTF-8"), 8);
				StringBuilder sb = new StringBuilder();
				
				String line;
				
				while((line = br.readLine()) != null) {
					
					sb.append(line + "\n");
					
				}
				
				result = sb.toString();
				
			} catch(Exception e) {
				
				e.printStackTrace();
				
			} finally {
				
				try {
					
					if(input != null) input.close();
					
				} catch(Exception e) {
					
					e.printStackTrace();
					
				}
				
			}
			
			// Holds the key value pairs for json
			JSONObject json;
			
			try {
				
				// get rid of cbfunc
				result = result.substring(7);
				result = result.substring(0, result.length()-2);
				
				Log.v("JSONParser RESULT ", result);
				
				json = new JSONObject(result);
				
				JSONObject query = json.getJSONObject("query");
				
				JSONObject results = query.getJSONObject("results");
				
				JSONObject quote = results.getJSONObject("quote");
				
				symbol = quote.getString("symbol");
				daysLow = quote.getString("DaysLow");
				daysHigh = quote.getString("DaysHigh");
				change = quote.getString("Change");
				
				JSONArray queryArr = quote.names();
				
				List<String> list = new ArrayList<String>();
				for(int i = 0; i < queryArr.length(); i++) {
					
					list.add(queryArr.getString(i));
					
				}
				
				for(String item : list) {
					
					Log.v("JSON ITEMS ", item);
					
				}
				
			} catch(JSONException e) {
				
				e.printStackTrace();
				
			}
			
			return result;
		}
		
		@Override
		protected void onPostExecute(String result) {
			
			TextView line1 = (TextView) findViewById(R.id.line1);
			TextView line2 = (TextView) findViewById(R.id.line2);
			TextView line3 = (TextView) findViewById(R.id.line3);

			line1.setText("Stock: " + symbol + " : " + change);
			line2.setText("DaysLow: " + daysLow);
			line3.setText("DaysHigh: " + daysHigh);
			
		}
		
		
		
	}

}
