package com.spoobrain.fragments;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		FragmentManager manager = getFragmentManager();
		
		// To signal it is ready to do fragment stuff
		FragmentTransaction transaction = manager.beginTransaction();
		
		MyFragment frag = new MyFragment();
		
		transaction.add(R.id.fragmentTextView, frag);
		
		transaction.commit();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
