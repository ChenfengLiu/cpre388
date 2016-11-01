package edu.iastate.qmurphy.compasslab.models;

import android.app.Service;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import edu.iastate.qmurphy.compasslab.interfaces.SensorUpdateCallback;

/**
 * Created by Quinn on 10/23/2016.
 */

public class TiltCalculator implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mAcc;
    private SensorUpdateCallback mCallback;

    public TiltCalculator(Context context, SensorUpdateCallback callback) {
        // TODO Get the Sensor Service using the application context
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        // TODO Get an accelerometer
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            mAcc = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
        mCallback = callback;
    }

    public void start() {
        // TODO Register listener
        mSensorManager.registerListener(this, mAcc, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void stop() {
        // TODO Unregister listener
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {


        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        if (z != 0) {
            float orientation = 0.0f;
            // TODO Determine pitch from accelerometer
            double pitch = Math.atan(y / Math.sqrt(Math.pow(x, 2) + Math.pow(z, 2)));

            // TODO Determine roll from accelerometer
            double roll = Math.atan(-x / z);

            if(pitch != 0){
                // TODO Determine orientation from pitch and roll
                orientation = (float) (Math.atan2(pitch, roll) * 180 / Math.PI) + 90.0f;
                mCallback.update(orientation);
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
