package com.knd.hack.bnpproject.siniestro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.knd.hack.bnpproject.EDA.Seguro;
import com.knd.hack.bnpproject.EDA.Usuario;
import com.knd.hack.bnpproject.R;

import java.util.ArrayList;

/**
 * Created by Ian on 14-10-2017.
 */

public class listaDenunciasActivity extends Activity {

    siniestrosAdapter adapter;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listadenucias);

        final ArrayList<Seguro> seguros = cargarDenuncias();

        lista = (ListView)findViewById(R.id.list);
        adapter = new siniestrosAdapter(getApplicationContext(),seguros);
        lista.setAdapter(adapter);

        final Usuario user = (Usuario)getIntent().getSerializableExtra("user");

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(listaDenunciasActivity.this, asistenteActivity.class);
                intent.putExtra("seguro",seguros.get(i).getTitulo());
                intent.putExtra("user",user);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });
    }

    public ArrayList<Seguro> cargarDenuncias(){
        ArrayList<Seguro> seguros = new ArrayList<>();
        Seguro seguro1 = new Seguro("Seguro de Vida");
        Seguro seguro2 = new Seguro("Seguro para accidentes de auto");
        Seguro seguro3 = new Seguro("Seguro para accidentes personales");
        seguros.add(seguro1);
        seguros.add(seguro2);
        seguros.add(seguro3);
        return seguros;
    }

}
