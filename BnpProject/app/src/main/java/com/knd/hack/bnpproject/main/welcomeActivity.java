package com.knd.hack.bnpproject.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.knd.hack.bnpproject.R;

/**
 * Created by Ian on 14-10-2017.
 */


public class welcomeActivity extends Activity{

    Button login;
    Button registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        login = (Button)findViewById(R.id.login);
        registro = (Button)findViewById(R.id.registro);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(welcomeActivity.this,loginMaskActivity.class);
                startActivity(intent);

            }
        });

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }
}
