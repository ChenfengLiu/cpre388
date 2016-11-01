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

public class BetterCompass implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mMagField;
    private Sensor mAcc;
    private SensorUpdateCallback mCallback;

    private float[] mAccelerometerReading = new float[3];
    private float[] mMagnetometerReading = new float[3];

    public BetterCompass(Context context, SensorUpdateCallback callback) {
        // TODO Get the Sensor Service using the application context
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

        // TODO Get a magnetic field sensor
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null) {
            mMagField = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        }

        // TODO Get an accelerometer
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            mAcc = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
        mCallback = callback;
    }

    public void start() {
        // TODO Register listeners
        mSensorManager.registerListener(this, mMagField, SensorManager.SENSOR_DELAY_FASTEST);
        mSensorManager.registerListener(this, mAcc, SensorManager.SENSOR_DELAY_FASTEST);

    }

    public void stop() {
        // TODO Unregister listeners
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            // TODO Store magnetic field data in mMagnetometerReading
            mMagnetometerReading[0] = event.values[0];
            mMagnetometerReading[1] = event.values[1];
            mMagnetometerReading[2] = event.values[2];

        } else if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            // TODO Store accelerometer data in mAccelerometerReading
            mAccelerometerReading[0] = event.values[0];
            mAccelerometerReading[1] = event.values[1];
            mAccelerometerReading[2] = event.values[2];
        }

        // TODO Get orientation from magnetometer and accelerometer
        float orientation = 0.0f;
        float[] mRotationMatrix = new float[9];
        float[] mOrientationAngles = new float[3];
        SensorManager.getRotationMatrix(mRotationMatrix, null, mAccelerometerReading, mMagnetometerReading);
        SensorManager.getOrientation(mRotationMatrix, mOrientationAngles);
        orientation = (float) (-mOrientationAngles[0] * 180 / Math.PI);
        mCallback.update(orientation);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do nothing
    }
}
