package com.knd.hack.bnpproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.knd.hack.bnpproject.EDA.Usuario;
import com.knd.hack.bnpproject.main.mainActivity;

import org.w3c.dom.Text;

/**
 * Created by Ian on 15-10-2017.
 */


public class finalClass extends Activity {

    Button button;
    TextView nombre;
    TextView apellido;
    TextView rut;
    TextView motivo;
    TextView patente;
    TextView patente2;
    TextView correo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        button = (Button)findViewById(R.id.acceptButton);
        nombre = (TextView)findViewById(R.id.nombre);
        apellido = (TextView)findViewById(R.id.apellido);
        rut = (TextView)findViewById(R.id.rut);
        motivo = (TextView)findViewById(R.id.motivo);
        patente = (TextView)findViewById(R.id.patente);
        patente2 = (TextView)findViewById(R.id.patente2);
        correo = (TextView)findViewById(R.id.coreo);

        final Usuario user = (Usuario) getIntent().getSerializableExtra("user");

        nombre.setText(user.getNombre());
        apellido.setText(user.getApellido());
        rut.setText(user.getRut());
        patente.setText(user.getPatente());
        motivo.setText(getIntent().getStringExtra("voice"));
        patente2.setText(getIntent().getStringExtra("patente"));
        correo.setText(user.getCorreo());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(finalClass.this, mainActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });

    }
}
