package com.knd.hack.bnpproject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.knd.hack.bnpproject.EDA.Usuario;

/**
 * Created by Ian on 15-10-2017.
 */

public class finalClass extends Activity {

    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        text = (TextView)findViewById(R.id.text);

        final Usuario user = (Usuario) getIntent().getSerializableExtra("user");

        text.setText(user.getApellido());

    }
}
