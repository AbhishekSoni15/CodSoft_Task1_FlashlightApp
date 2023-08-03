package com.example.flashlightapp;

import androidx.appcompat.app.AppCompatActivity;


import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Switch aSwitch;
    String TextResult,CamID;
    TextView OnOffText;
    private CameraManager CameraManager1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aSwitch=findViewById(R.id.myswitchid);
        OnOffText=findViewById(R.id.OnOffText);
        if(this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
            Toast.makeText(MainActivity.this, "This device has Flashlight", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(MainActivity.this, "This device Doesn't have Flashlight", Toast.LENGTH_SHORT).show();
        }

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                light(isChecked);

            }
        });

    }

    private void light(boolean isChecked) {
        //for getting the service for flashlight...

        try {
            CameraManager1=(CameraManager)getSystemService(CAMERA_SERVICE);
            CamID = CameraManager1.getCameraIdList()[0];//Return the list of currently connected camera devices by identifier, including cameras that may be in use by other camera API clients...from google android reference..
            CameraManager1.setTorchMode(CamID,isChecked);
            TextResult=isChecked?"ON":"OFF";
            OnOffText.setText(TextResult);

        } catch (CameraAccessException e) {
            throw new RuntimeException(e);
        }

    }
}