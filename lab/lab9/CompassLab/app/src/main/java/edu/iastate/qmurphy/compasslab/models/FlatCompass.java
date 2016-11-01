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

public class FlatCompass implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mMagField;
    private SensorUpdateCallback mCallback;

    public FlatCompass(Context context, SensorUpdateCallback callback) {
        // TODO Get the Sensor Service using the application context
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        // TODO Get a magnetic field sensor
        if(mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null){
            mMagField = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        }

        mCallback = callback;
    }

    public void start() {
        // TODO Register listener
        mSensorManager.registerListener(this, mMagField,SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void stop() {
        // TODO Unregister listener
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float orientation = 0.0f;
        // TODO Calculate orientation
        double x = event.values[0];
        double y = event.values[1];

        if(x!=0){
            orientation = (float) Math.atan2(x,y)*180/ (float) Math.PI;
        }
        mCallback.update(orientation);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
