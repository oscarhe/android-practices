package com.spoobrain.addressbookapp;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditContact extends Activity {

	EditText firstNameEditText, lastNameEditText, phoneNumberEditText, emailAddressEditText, homeAddressEditText;
	
	DBTools dbTools = new DBTools(this);
	
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_contact);
		
		firstNameEditText = (EditText) findViewById(R.id.firstNameEditText);
		lastNameEditText = (EditText) findViewById(R.id.lastNameEditText);
		phoneNumberEditText = (EditText) findViewById(R.id.phoneNumberEditText);
		emailAddressEditText = (EditText) findViewById(R.id.emailAddressEditText);
		homeAddressEditText = (EditText) findViewById(R.id.homeAddressEditText);
		
		// to get the intent from MainActivity.java so that the value stored from putExtra() can be retrieved
		Intent intent = getIntent();
		String contactId = intent.getStringExtra("contactId");
		
		HashMap<String, String> contactList = dbTools.getContactInfo(contactId);
		
		if(contactList.size() != 0) {
			
			firstNameEditText.setText(contactList.get("firstName"));
			lastNameEditText.setText(contactList.get("lastName"));
			phoneNumberEditText.setText(contactList.get("phoneNumber"));
			emailAddressEditText.setText(contactList.get("emailAddress"));
			homeAddressEditText.setText(contactList.get("homeAddress"));
			
		}
	
	}
	
	public void editContact(View view) {
		
		HashMap<String, String> queryValuesMap = new HashMap<String, String>();
		
		Intent intent = getIntent();
		String contactId = intent.getStringExtra("contactId");
		
		queryValuesMap.put("contactId", contactId);
		queryValuesMap.put("firstName", firstNameEditText.getText().toString());
		queryValuesMap.put("lastName", lastNameEditText.getText().toString());
		queryValuesMap.put("phoneNumber", phoneNumberEditText.getText().toString());
		queryValuesMap.put("emailAddressEditText", emailAddressEditText.getText().toString());
		queryValuesMap.put("homeAddressEditText", homeAddressEditText.getText().toString());
		
		dbTools.updateContact(queryValuesMap);
		
		this.callMainActivity(view);
		
	}
	
	public void removeContact(View view) {
		
		Intent intent = getIntent();
		String contactId = intent.getStringExtra("contactId");
		
		dbTools.deleteContact(contactId);
		
		this.callMainActivity(view);
	
	}
	
	public void callMainActivity(View view) {
		
		Intent intent = new Intent(getApplication(), MainActivity.class);
		startActivity(intent);
	
	}
	
}
