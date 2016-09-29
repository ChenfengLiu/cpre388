package edu.iastate.gestures;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.widget.TextView;

/**
 * Activity which represents the right view.
 */
public class SecondActivity extends CustomGestureListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		super.setGestureDetector(new GestureDetector(this.getApplicationContext(), this));
		super.setLeftRight(MainActivity.class, ThirdActivity.class);

		Bundle b = this.getIntent().getExtras();
		if(b!=null){
			String msg = b.getString("msg");
			TextView tv2 = (TextView) findViewById(R.id.tv2);
			tv2.setText(msg);
		}


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_second, menu);
		return true;
	}
}
