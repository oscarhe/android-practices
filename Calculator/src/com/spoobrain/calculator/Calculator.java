package com.spoobrain.calculator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Calculator extends Activity {
	
	String numericalInput;
	private double prevResult;
	private double result;
	private boolean noPrevInput = true;
	
	TextView equationAnswerTextView;
	
	// Numerical buttons
	Button oneButton;
	Button twoButton;
	Button threeButton;
	Button fourButton;
	Button fiveButton;
	Button sixButton;
	Button sevenButton;
	Button eightButton;
	Button nineButton;
	Button zeroButton;
	
	// Operation buttons
	Button cancelEntryButton;
	Button plusButton;
	Button minusButton;
	Button multButton;
	Button divButton;
	Button equalsButton;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculator);
		
		numericalInput = null;
		prevResult = 0;
		result = 0;
		
		equationAnswerTextView = (TextView) findViewById(R.id.equationAnswerTextView);
		
		oneButton 	= (Button) findViewById(R.id.oneButton);
		twoButton 	= (Button) findViewById(R.id.twoButton);
		threeButton = (Button) findViewById(R.id.threeButton);
		fourButton 	= (Button) findViewById(R.id.fourButton);
		fiveButton 	= (Button) findViewById(R.id.fiveButton);
		sixButton 	= (Button) findViewById(R.id.sixButton);
		sevenButton = (Button) findViewById(R.id.sevenButton);
		eightButton = (Button) findViewById(R.id.eightButton);
		nineButton 	= (Button) findViewById(R.id.nineButton);
		zeroButton 	= (Button) findViewById(R.id.zeroButton);
		
		cancelEntryButton 	= (Button) findViewById(R.id.cancelEntryButton);
		plusButton 			= (Button) findViewById(R.id.plusButton);
		minusButton 		= (Button) findViewById(R.id.minusButton);
		multButton 			= (Button) findViewById(R.id.multButton);
		divButton 			= (Button) findViewById(R.id.divButton);
		equalsButton 		= (Button) findViewById(R.id.equalsButton);
		
		setButtonClickListeners();
		
		
	}
	
	private void setButtonClickListeners() {
		
		oneButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				
			}
			
		});
		
		twoButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		threeButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		fourButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		fiveButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		sixButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		sevenButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		eightButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		nineButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		zeroButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		cancelEntryButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		plusButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		minusButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		multButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		divButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		equalsButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}
	
	private void calculate(double num, String op) {
		
		if (noPrevInput) {
			
			prevResult = num;
			noPrevInput = false;
			
		}
		else {
			
			if (op == "CE") {
				
				prevResult = 0;
				result = 0;
				noPrevInput = true;
				
			}
			else if (op == "+") {
				
				result = result + prevResult;
				prevResult = num;
				
			}
			else if (op == "-") {
				
				result = result + prevResult;
				prevResult = -num;
				
			}
			else if (op == "*") {
				
				prevResult = prevResult * num;
				
			}
			else if (op == "/") {
				
				prevResult = prevResult / num;
				
			}
			else {
				
				// For op == "="
				result = result + prevResult;
				equationAnswerTextView.setText(String.valueOf(result));
				
			}
			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calculator, menu);
		return true;
	}

}
