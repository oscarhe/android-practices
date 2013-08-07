package com.spoobrain.readerfrag;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Articles extends Fragment {
	
	final static String POSITION = "position";
	int currentPos = -1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		if(savedInstanceState != null) {
			
			currentPos = savedInstanceState.getInt(POSITION);
			
		}

		return inflater.inflate(R.layout.article_view, container, false);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {

		super.onSaveInstanceState(outState);
		
		outState.putInt(POSITION, currentPos);
		
	}

	@Override
	public void onStart() {

		super.onStart();
		
		Bundle args = getArguments();
		
		if(args != null) {
			
			updateArticleView(args.getInt(POSITION));
			
		} else if(currentPos != -1) {
			
			updateArticleView(currentPos);
			
		} 
		
	}
	
	public void updateArticleView(int position) {
		
		TextView article = (TextView) getActivity().findViewById(R.id.articleTextView);
		
		article.setText(Ipsum.Articles[position]);
		
		currentPos = position;
		
	}

}
