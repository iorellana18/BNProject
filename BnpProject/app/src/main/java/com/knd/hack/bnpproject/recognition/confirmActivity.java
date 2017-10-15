package com.knd.hack.bnpproject.recognition;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.knd.hack.bnpproject.R;

/**
 * Created by RockHopper on 14-10-2017.
 */

public class confirmActivity extends Activity {
    TextView patente;
    Button si;
    Button no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar);

        patente = (TextView)findViewById(R.id.patente);
        si = (Button)findViewById(R.id.si);
        no = (Button)findViewById(R.id.no);

        si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(confirmActivity.this,genericActivity.class);
                startActivity(intent);
                finish();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(confirmActivity.this,recognition.class);
                startActivity(intent);
                finish();
            }
        });

        patente.setText(getIntent().getStringExtra("patente"));

    }
}
