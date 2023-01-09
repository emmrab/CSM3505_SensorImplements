package com.example.sensorimplement;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView tv4;
    TextView tv6;
    TextView tv8;
    SensorManager sManager;
    Sensor light, rHumidity, pressure;
    List pressureList, ligthList, rHumidityList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv4=findViewById(R.id.textView4);
        tv6=findViewById(R.id.textView6);
        tv8=findViewById(R.id.textView8);
        sManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        light = sManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        rHumidity = sManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        pressure = sManager.getDefaultSensor(Sensor.TYPE_PRESSURE);

        if (light != null){
            sManager.registerListener(this,light,SensorManager.SENSOR_DELAY_NORMAL);
        }else{
            Toast.makeText(this, "Light Sensor Not Found!", Toast.LENGTH_SHORT).show();
        }

        if (rHumidity != null){
            sManager.registerListener(this,rHumidity,SensorManager.SENSOR_DELAY_NORMAL);
        }else{
            Toast.makeText(this, "Relative Humidity Sensor Not Found!", Toast.LENGTH_SHORT).show();
        }

        if (pressure != null){
            sManager.registerListener(this,pressure,SensorManager.SENSOR_DELAY_NORMAL);
        }else{
            Toast.makeText(this, "Pressure Sensor Not Found!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
            String a= Float.toString(sensorEvent.values[0]);
            tv4.setText(a);
        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_PRESSURE) {
            String a= Float.toString(sensorEvent.values[0]);
            tv8.setText(a);
        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {
            String a= Float.toString(sensorEvent.values[0]);
            tv6.setText(a);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    // This is onResume function of our app
    @Override
    public void onResume() {
        super.onResume();
        sManager.registerListener(this,light,SensorManager.SENSOR_DELAY_NORMAL);
        sManager.registerListener(this,rHumidity,SensorManager.SENSOR_DELAY_NORMAL);
        sManager.registerListener(this,pressure,SensorManager.SENSOR_DELAY_NORMAL);
    }

    // This is onPause function of our app
    @Override
    public void onPause() {
        super.onPause();
        sManager.unregisterListener(this);
    }
}