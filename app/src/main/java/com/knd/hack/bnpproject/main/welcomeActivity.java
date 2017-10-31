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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

    }


    public void toLogin(View view){
        Intent intent = new Intent(welcomeActivity.this,loginMaskActivity.class);
        startActivity(intent);
    }

    public void toRegistro(View view){
        Toast.makeText(getApplicationContext(),"En construcción!",Toast.LENGTH_LONG).show();
    }
}
