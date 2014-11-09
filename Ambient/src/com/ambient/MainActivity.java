package com.ambient;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {

	private SensorManager mSensorManager;
	private Sensor mAmbientTemperature;
	private TextView myAmbientTemperatureData;
	private TextView myAmbientTemperatureDataRaw;

	private int eventCount = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		myAmbientTemperatureData = (TextView) this
				.findViewById(R.id.textAmbientTemperatureData);
		myAmbientTemperatureDataRaw = (TextView) this
				.findViewById(R.id.textAmbientTemperatureDataRaw);

		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		mAmbientTemperature = mSensorManager
				.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

		myAmbientTemperatureData.setText(mAmbientTemperature.toString());
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub

		myAmbientTemperatureDataRaw.setText(Float.toString(event.values[0]));

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(this, mAmbientTemperature,
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	protected void onPause() {
		super.onPause();
		mSensorManager.unregisterListener(this);
	}

}
