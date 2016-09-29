package edu.iastate.gestures;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Activity which represents the left view.
 */
public class ThirdActivity extends CustomGestureListener {
	private GestureDetector gesture = null;
	TextView tv3, tv4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);

		gesture = new GestureDetector(this.getApplicationContext(), this);
		super.setGestureDetector(gesture);
		super.setLeftRight(SecondActivity.class, MainActivity.class);

		tv3 = (TextView) findViewById(R.id.tV3);
		tv4 = (TextView) findViewById(R.id.tV4);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_third, menu);
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent me)
	{
		if (gesture != null){
			tv3.setText("positionX: " + me.getX());
			tv4.setText("positionY: " + me.getY());
			return gesture.onTouchEvent(me);
		}
		else
			return false;
	}
}
