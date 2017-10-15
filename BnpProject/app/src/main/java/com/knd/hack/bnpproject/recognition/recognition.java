package com.knd.hack.bnpproject.recognition;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.knd.hack.bnpproject.R;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by RockHopper on 14-10-2017.
 */

public class recognition extends AppCompatActivity {

    SurfaceView cameraView;
    TextView textView;
    CameraSource cameraSource;
    final int RequestCameraPermissionID = 1001;
    String letters ="[A-Z&&[^aeiouAEIOU]]";
    String numbers ="[0-9]";
    Pattern pletters = Pattern.compile(letters);
    Pattern pnumbers = Pattern.compile(numbers);

    public boolean checkletter(Pattern patron,char letra){
        String texto = String.valueOf(letra);
        Matcher revisador = patron.matcher(texto);
        return revisador.find();
    }

    public boolean checknumber(Pattern patron,char numero){
        String texto = String.valueOf(numero);
        Matcher revisador = patron.matcher(texto);
        return revisador.find();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)     {
        switch (requestCode){
            case RequestCameraPermissionID:{
                if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                        return;
                    }
                    try {
                        cameraSource.start(cameraView.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recognition);

        cameraView = (SurfaceView) findViewById(R.id.surface_view);
        textView = (TextView) findViewById(R.id.text_view);

        final TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
        if (!textRecognizer.isOperational()) {
            Log.w("TxtRecognition", "Detector dependecies are not yet available");
        } else {
            cameraSource = new CameraSource.Builder(getApplicationContext(), textRecognizer)
                    .setFacing(CameraSource.CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(1280, 1024)
                    .setRequestedFps(2.0f)
                    .setAutoFocusEnabled(true)
                    .build();
            cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    try {
                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                            ActivityCompat.requestPermissions(recognition.this,
                                    new String[]{Manifest.permission.CAMERA},
                                    RequestCameraPermissionID);
                            return;
                        }
                        cameraSource.start(cameraView.getHolder());
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                    cameraSource.stop();
                }
            });

            textRecognizer.setProcessor(new Detector.Processor<TextBlock>() {
                @Override
                public void release() {

                }

                @Override
                public void receiveDetections(Detector.Detections<TextBlock> detections) {
                    final SparseArray<TextBlock> items = detections.getDetectedItems();
                    if (items.size() != 0 ){
                        textView.post(new Runnable() {
                            @Override
                            public void run() {
                                StringBuilder stringBuilder = new StringBuilder();
                                for (int i = 0; i < 1; i++) {
                                    Log.d("Mensaje",String.valueOf(items.size()));
                                    TextBlock item = items.valueAt(i);
                                    stringBuilder.append(item.getValue());
                                    Log.e("Palabra",String.valueOf(stringBuilder));
                                }
                                textView.setText(stringBuilder.toString());
                                String patente = stringBuilder.toString();
                                if(patente.length()==6) {
                                    if ((checkletter(pletters, patente.charAt(0))) && (checkletter(pletters, patente.charAt(1))) && (checknumber(pnumbers, patente.charAt(2))) && (checknumber(pnumbers, patente.charAt(3))) && (checknumber(pnumbers, patente.charAt(4))) && (checknumber(pnumbers, patente.charAt(5)))) {
                                        textRecognizer.release();
                                        Intent intent = new Intent(recognition.this,confirmActivity.class);
                                        intent.putExtra("patente",patente);
                                        finish();
                                        startActivity(intent);

                                    } else if ((checkletter(pletters, patente.charAt(0))) && (checkletter(pletters, patente.charAt(1))) && (checkletter(pletters, patente.charAt(2))) && (checkletter(pletters, patente.charAt(3))) && (checknumber(pnumbers, patente.charAt(4))) && (checknumber(pnumbers, patente.charAt(5)))) {
                                        textRecognizer.release();
                                        Intent intent = new Intent(recognition.this,confirmActivity.class);
                                        intent.putExtra("patente",patente);
                                        finish();
                                        startActivity(intent);

                                    }
                                }

                            }
                        });
                    }
                }
            });
        }


    }
}
