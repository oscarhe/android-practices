package com.spoobrain.readerfrag;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

public class MainActivity extends FragmentActivity
	implements Headlines.OnHeadlineSelectedListener {

	@Override
	public void onArticleSelected(int position) {

		Articles articleFrag = (Articles) getSupportFragmentManager().findFragmentById(R.id.articles_fragment);
		
		if(articleFrag != null) {
			
			articleFrag.updateArticleView(position);
			
		} else {
			
			Articles newFrag = new Articles();
			
			Bundle args = new Bundle();
			
			args.putInt(Articles.POSITION, position);
			
			newFrag.setArguments(args);
			
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			
			transaction.replace(R.id.fragment_container, newFrag);
			
			transaction.addToBackStack(null);
			
			transaction.commit();
			
		}
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.news_articles);
		
		if(findViewById(R.id.fragment_container) != null) {
			
			if(savedInstanceState != null) {
				
				return;
				
			}
			
			Headlines firstFrag = new Headlines();
			
			firstFrag.setArguments(getIntent().getExtras());
			
			getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, firstFrag).commit();
			
		}
		
	}

	

}
