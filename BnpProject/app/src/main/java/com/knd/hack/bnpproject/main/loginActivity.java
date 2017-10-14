package com.knd.hack.bnpproject.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.knd.hack.bnpproject.R;

/**
 * Created by Ian on 14-10-2017.
 */

public class loginActivity extends Activity {

    Button escanear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        escanear = (Button)findViewById(R.id.escanear);

        escanear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this,mainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
