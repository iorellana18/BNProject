package com.knd.hack.bnpproject.recognition;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.knd.hack.bnpproject.R;

/**
 * Created by RockHopper on 14-10-2017.
 */

public class confirmActivity extends Activity {
    TextView patente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar);

        patente = (TextView)findViewById(R.id.patente);

        patente.setText(getIntent().getStringExtra("patente"));

    }
}
