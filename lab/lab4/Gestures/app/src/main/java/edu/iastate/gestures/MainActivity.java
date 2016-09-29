package edu.iastate.gestures;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;


/**
 * Main activity which represents the middle view.
 */
public class MainActivity extends CustomGestureListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		super.setGestureDetector(new GestureDetector(this.getApplicationContext(), this));
		super.setLeftRight(ThirdActivity.class, SecondActivity.class);



		Button bt = (Button) findViewById(R.id.bt);
		bt.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view) {
				EditText et = (EditText) findViewById(R.id.et);
				String msg = et.getText().toString();
				changeToSecondActivity(view, msg);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	protected void changeToSecondActivity(View v, String msg){
		Intent i = new Intent();
		i.putExtra("msg", msg);
		i.setClass(this, SecondActivity.class);
		startActivity(i);
	}

}
