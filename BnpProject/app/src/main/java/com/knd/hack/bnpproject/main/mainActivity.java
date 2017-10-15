package com.knd.hack.bnpproject.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.knd.hack.bnpproject.EDA.Usuario;
import com.knd.hack.bnpproject.R;
import com.knd.hack.bnpproject.siniestro.listaDenunciasActivity;

import org.w3c.dom.Text;

/**
 * Created by Ian on 14-10-2017.
 */

public class mainActivity extends Activity {

    RelativeLayout siniestro;
    TextView nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        siniestro = (RelativeLayout)findViewById(R.id.panel3);
        nombre = (TextView)findViewById(R.id.nombre);

        final Usuario user = (Usuario)getIntent().getSerializableExtra("user");

        nombre.setText("Hola "+user.getNombre()+" "+user.getApellido()+"!");

        siniestro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity.this,listaDenunciasActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

    }
}
