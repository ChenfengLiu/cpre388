package com.example.stopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 
 * Sample Stopwatch Android activity
 *
 */
public class StopWatchActivity extends Activity {

	/**
	 * REFRESH_RATE defines how often we should update the timer to show how much time has elapsed.
	 * refresh every 100 milliseconds
	 */
	private final int REFRESH_RATE = 100;
    private long currenttime;
    private long lasttime = 0l;
    private long starttime;

    private Handler mHandler = new Handler();

    Button start, stop, reset;
    TextView tv1, tv2;
	/**
	 * A variable to keep track of the seconds 
	 */
    private int hour;
    private int minute;
    private int second;
	private int milSec;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stopwatch);

        start = (Button) findViewById(R.id.btStart);
        stop = (Button) findViewById(R.id.btStop);
        reset = (Button) findViewById(R.id.btReset);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);

        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startClick(v);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                stopClick(v);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resetClick(v);
            }
        });
	}
	

	/**
     * This method will start the current stopwatch clock
     * 
     * @param view the current view
     */
    
   public void startClick(View view){
       showStopButton();
       starttime = SystemClock.uptimeMillis();
       mHandler.postDelayed(startTimer,REFRESH_RATE);


   }
    /**
     * This method will reset the current stopwatch clock
     * 
     * @param view the current view
     */
    public void resetClick(View view){
        currenttime = 0;
        lasttime = 0;
        starttime = 0;
        hour = 0;
        minute = 0;
        second = 0;
        milSec = 0;
        tv1.setText("00:00:00");
        tv2.setText(".0");
        mHandler.removeCallbacks(startTimer);

    }
    
    /**
     * This method will stop the current stopwatch.
     * 
     * @param view the current view
     */
    public void stopClick(View view){
        hideStopButton();
        lasttime = currenttime;
        mHandler.removeCallbacks(startTimer);
    }
    
    /**
     * This method will show the stop button when called by hiding the 
     * start and reset button and making the stop button visible.
     */
    private void showStopButton(){
        stop.setVisibility(View.VISIBLE);
        reset.setVisibility(View.INVISIBLE);
        start.setVisibility(View.INVISIBLE);
    }
    
    /**
     * This method will show the start and reset buttons by hiding the 
     * stop button and making the start and reset buttons visible.
     */
    private void hideStopButton(){
        stop.setVisibility(View.INVISIBLE);
        reset.setVisibility(View.VISIBLE);
        start.setVisibility(View.VISIBLE);
    }
    
    /**
     * Converts the elapsed given time and updates the display
     * 
     * @param time the time to update the current display to
     */
    private void updateTimer (long time){
		//Convert the milliseconds,seconds,minutes,hours to String and format to ensure it has a leading zero when required
        this.milSec = (int) (time%1000);
        this.milSec = milSec/100;
        long totalSeconds=time/1000;
        this.second=(int)(totalSeconds%60);
        long totalMinutes=totalSeconds/60;
        this.minute=(int)(totalMinutes%60);
        long totalHours=totalMinutes/60;
        this.hour=(int)(totalHours%24);

        //Setting the timer text to the elapsed time
        String temp = String.format("%02d:%02d:%02d", hour, minute, second);

        tv1.setText(temp);
        tv2.setText("."+milSec);
	}
    
    /**
     * Create a Runnable startTimer that makes timer runnable.
     */
    private Runnable startTimer = new Runnable() {
    	   public void run() {
               currenttime = SystemClock.uptimeMillis() - starttime + lasttime;
               updateTimer(currenttime);
               mHandler.postDelayed(this,REFRESH_RATE);
    		}
    	};

}
    