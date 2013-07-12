package com.spoobrain.studentgrades;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewClass extends Activity {
	
	DBTools dbTools = new DBTools(this);
	EditText name, type, number, section, year;

	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_new_class);
		
		name = (EditText) findViewById(R.id.nameEditText);
		type = (EditText) findViewById(R.id.typeEditText);
		number = (EditText) findViewById(R.id.numberEditText);
		section = (EditText) findViewById(R.id.sectionEditText);
		year = (EditText) findViewById(R.id.yearEditText);
		
	}
	
	public void addNewClass(View view) {
		
		HashMap<String, String> queryValues = new HashMap<String, String>();
		
		queryValues.put("className", name.getText().toString());
		queryValues.put("classType", type.getText().toString());
		queryValues.put("classNumber", number.getText().toString());
		queryValues.put("classSection", section.getText().toString());
		queryValues.put("classYear", year.getText().toString());
		
		dbTools.insertClass(queryValues);
		
		this.callMainActivity(view);
	
	}
	
	public void callMainActivity(View view) {
		
		Intent intent = new Intent(getApplication(), MainActivity.class);
		
		startActivity(intent);
	}
	
}
