package edu.iastate.gestures;

import android.app.Activity;
import android.content.Intent;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * This is a base class for handling swipes in the application.
 */
public class CustomGestureListener extends Activity implements OnGestureListener{
	/*
	 * These variables store activity specific values.
	 */
	private GestureDetector gesture = null;
	private Class<?> leftActivity = null;
	private Class<?> rightActivity = null;
	
    @Override
   public boolean onTouchEvent(MotionEvent me)
   {
    	if (gesture != null)
    		return gesture.onTouchEvent(me);
    	else
    		return false;
   }

	@Override
	public boolean onDown(MotionEvent e) {
		//insert code here
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		int d = (int) (e2.getX() - e1.getX());

		if(Math.abs(d) > 100 && Math.abs(velocityX) > Math.abs(velocityY)){
			if(velocityX > 0){
				//move to the right activity
				Toast toast = Toast.makeText(getApplicationContext(), "Right Swipe ", Toast.LENGTH_SHORT);
				toast.show();
				Intent i = new Intent();
				i.setClass(this, rightActivity);
				startActivity(i);
			}else{
				//move to the left activity
				Toast toast = Toast.makeText(getApplicationContext(), "Left Swipe ", Toast.LENGTH_SHORT);
				toast.show();
				Intent i = new Intent();
				i.setClass(this, leftActivity);
				startActivity(i);

			}
			return true;
		}
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Sets the gesture detector for the activity
	 * @param gesture the gesture detector specific to the activity
	 */
	public void setGestureDetector(GestureDetector gesture){
		this.gesture = gesture;
	}
	
	/**
	 * Sets the left and right activity classes which are swiped to
	 * @param leftActivity	The class for the left Activity
	 * @param rightActivity The class for the right Activity
	 */
	public void setLeftRight(Class<?> leftActivity, Class<?> rightActivity){
		this.leftActivity = leftActivity;
		this.rightActivity = rightActivity;
	}


}
