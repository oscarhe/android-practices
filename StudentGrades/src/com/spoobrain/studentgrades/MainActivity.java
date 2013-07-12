package com.spoobrain.studentgrades;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends ListActivity {

	DBTools dbTools = new DBTools(this);
	
	TextView classId;
	
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ArrayList<HashMap<String, String>> allClasses = dbTools.getAllClasses();
		
		if(allClasses.size() != 0) {
			
			ListView listView = getListView();
			
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View view,
						int arg2, long arg3) {
					
					classId = (TextView) view.findViewById(R.id.classIdTextView);
					String classIdValue = classId.getText().toString();
					
					Intent intent = new Intent(getApplication(), EditClass.class);
					intent.putExtra("classId", classIdValue);
					
					startActivity(intent);
					
				}
				
			});
			
			ListAdapter adapter = new SimpleAdapter(
					MainActivity.this, 
					allClasses, 
					R.layout.class_entry, 
					new String[] {"classId", "classType", "className"}, 
					new int[] {R.id.classIdTextView, R.id.classTypeTextView, R.id.classNameTextView});
			
			setListAdapter(adapter);
			
		}
		
	}
	
	public void showAddClass(View view) {
		
		Intent intent = new Intent(getApplication(), NewClass.class);
		
		startActivity(intent);
		
	}

}
