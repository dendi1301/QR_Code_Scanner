package com.dendi.qrcodescanner;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ZXingScannerView.ResultHandler {
    ImageView ivLightButton;
    private ProgressDialog progressDialog;
    //Variables
    Intent i;
    private ZXingScannerView mScannerView;
    private boolean flashState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivLightButton = (ImageView)findViewById(R.id.lightButton);
        progressDialog = new ProgressDialog(this);

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.CAMERA},
                1);

        ViewGroup contentFrame = (ViewGroup) findViewById(R.id.content_frame);
        mScannerView = new ZXingScannerView(this);
        contentFrame.addView(mScannerView);

        ivLightButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == ivLightButton){
            if(flashState==false) {
                v.setBackgroundResource(R.drawable.ic_flash_off);
                Toast.makeText(getApplicationContext(), "Flashlight turned on", Toast.LENGTH_SHORT).show();
                mScannerView.setFlash(true);
                flashState = true;
            }else if(flashState) {
                v.setBackgroundResource(R.drawable.ic_flash_on);
                Toast.makeText(getApplicationContext(), "Flashlight turned off", Toast.LENGTH_SHORT).show();
                mScannerView.setFlash(false);
                flashState = false;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    public void handleResult(final Result rawResult) {
        progressDialog.setMessage("Sedang memeriksa QR Code");
        progressDialog.show();
        String resCamera = rawResult.getText();
        Toast.makeText(this, resCamera, Toast.LENGTH_LONG).show();
        mScannerView.resumeCameraPreview(MainActivity.this);
        progressDialog.dismiss();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    Toast.makeText(MainActivity.this, "Permission denied to camera", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}