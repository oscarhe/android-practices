package com.spoobrain.calculator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Calculator extends Activity {

	String numericalInput;
	private String equation;
	private double prevResult;
	private double result;

	EditText equationAnswerEditText;

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

		numericalInput = "";
		prevResult = 0;
		result = 0;

		equationAnswerEditText = (EditText) findViewById(R.id.equationAnswerEditText);

		oneButton = (Button) findViewById(R.id.oneButton);
		twoButton = (Button) findViewById(R.id.twoButton);
		threeButton = (Button) findViewById(R.id.threeButton);
		fourButton = (Button) findViewById(R.id.fourButton);
		fiveButton = (Button) findViewById(R.id.fiveButton);
		sixButton = (Button) findViewById(R.id.sixButton);
		sevenButton = (Button) findViewById(R.id.sevenButton);
		eightButton = (Button) findViewById(R.id.eightButton);
		nineButton = (Button) findViewById(R.id.nineButton);
		zeroButton = (Button) findViewById(R.id.zeroButton);

		cancelEntryButton = (Button) findViewById(R.id.cancelEntryButton);
		plusButton = (Button) findViewById(R.id.plusButton);
		minusButton = (Button) findViewById(R.id.minusButton);
		multButton = (Button) findViewById(R.id.multButton);
		divButton = (Button) findViewById(R.id.divButton);
		equalsButton = (Button) findViewById(R.id.equalsButton);

		setButtonClickListeners();

	}

	private void setButtonClickListeners() {

		oneButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				numericalInput = equationAnswerEditText.getText().toString();
				equationAnswerEditText.setText(numericalInput + 1);

			}

		});

		twoButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				numericalInput = equationAnswerEditText.getText().toString();
				equationAnswerEditText.setText(numericalInput + 2);

			}

		});

		threeButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				numericalInput = equationAnswerEditText.getText().toString();
				equationAnswerEditText.setText(numericalInput + 3);

			}

		});

		fourButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				numericalInput = equationAnswerEditText.getText().toString();
				equationAnswerEditText.setText(numericalInput + 4);

			}

		});

		fiveButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				numericalInput = equationAnswerEditText.getText().toString();
				equationAnswerEditText.setText(numericalInput + 5);

			}

		});

		sixButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				numericalInput = equationAnswerEditText.getText().toString();
				equationAnswerEditText.setText(numericalInput + 6);

			}

		});

		sevenButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				numericalInput = equationAnswerEditText.getText().toString();
				equationAnswerEditText.setText(numericalInput + 7);

			}

		});

		eightButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				numericalInput = equationAnswerEditText.getText().toString();
				equationAnswerEditText.setText(numericalInput + 8);

			}

		});

		nineButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				numericalInput = equationAnswerEditText.getText().toString();
				equationAnswerEditText.setText(numericalInput + 9);

			}

		});

		zeroButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				numericalInput = equationAnswerEditText.getText().toString();
				equationAnswerEditText.setText(numericalInput + 0);

			}

		});

		cancelEntryButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				numericalInput = "";
				equationAnswerEditText.setText("");
				prevResult = 0;
				result = 0;

			}

		});

		plusButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				numericalInput = equationAnswerEditText.getText().toString();
				equationAnswerEditText.setText(numericalInput + "+");

			}

		});

		minusButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				numericalInput = equationAnswerEditText.getText().toString();
				equationAnswerEditText.setText(numericalInput + "-");

			}

		});

		multButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				numericalInput = equationAnswerEditText.getText().toString();
				equationAnswerEditText.setText(numericalInput + "*");

			}

		});

		divButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				numericalInput = equationAnswerEditText.getText().toString();
				equationAnswerEditText.setText(numericalInput + "/");

			}

		});

		equalsButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				String operand = "";
				char op = 0;
				boolean noPrevInput = true;

				equation = equationAnswerEditText.getText().toString();

				for (char c : equation.toCharArray()) {

					// For initial state of calculator
					if (noPrevInput && (c != '+' && c != '-' && c != '*' && c != '/')) {
						
						operand += c;
						
					} else if (noPrevInput) {
						
						noPrevInput = false;
						op = c;
						prevResult = Integer.parseInt(operand);
						operand = "";
						
					}
					
					if (!noPrevInput && (c != '+' && c != '-' && c != '*' && c != '/')) {
						
						operand += c;
						calculate(Integer.parseInt(operand), op);
						
					} else if (!noPrevInput) {
						
						op = c;
						operand = "";
						
					}

				}
				
				result = result + prevResult;
				
				equationAnswerEditText.setText(String.valueOf(result));
				noPrevInput = true;

			}

		});

	}

	private void calculate(int num, char op) {

		if (op == '+') {

			result = (Double) (result + prevResult);
			prevResult = num;

		} else if (op == '-') {

			result = (Double) (result + prevResult);
			prevResult = -num;

		} else if (op == '*') {

			prevResult = (Double) (prevResult * num);

		} else {

			prevResult = (Double) (prevResult / num);

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calculator, menu);
		return true;
	}

}
