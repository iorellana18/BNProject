package com.knd.hack.bnpproject.recognition;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.knd.hack.bnpproject.R;

/**
 * Created by Ian on 14-10-2017.
 */

public class textRecognitionActivity extends Activity {

    Button detectar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textrecognition);

        detectar = (Button)findViewById(R.id.detectar);

        detectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(textRecognitionActivity.this,recognition.class);
                startActivity(intent);
            }
        });
    }
}
