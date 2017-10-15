package com.knd.hack.bnpproject.siniestro;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

import com.knd.hack.bnpproject.EDA.Usuario;
import com.knd.hack.bnpproject.R;
import com.knd.hack.bnpproject.recognition.textRecognitionActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Ian on 14-10-2017.
 */

public class asistenteActivity2 extends Activity{

    private static final long SPLASH_SCREEN_DELAY = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setContentView(R.layout.activity_asistente2);
        final Usuario user = (Usuario)getIntent().getSerializableExtra("user");

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent mainIntent = new Intent().setClass(
                        asistenteActivity2.this, textRecognitionActivity.class);
                mainIntent.putExtra("user",user);
                startActivity(mainIntent);


            }
        };
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);

    }

}
