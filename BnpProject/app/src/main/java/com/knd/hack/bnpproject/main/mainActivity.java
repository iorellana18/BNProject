package com.knd.hack.bnpproject.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.knd.hack.bnpproject.R;
import com.knd.hack.bnpproject.siniestro.listaDenunciasActivity;

/**
 * Created by Ian on 14-10-2017.
 */

public class mainActivity extends Activity {

    RelativeLayout siniestro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        siniestro = (RelativeLayout)findViewById(R.id.panel3);

        siniestro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity.this,listaDenunciasActivity.class);
                startActivity(intent);
            }
        });

    }
}
