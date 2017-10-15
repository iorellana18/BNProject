package com.knd.hack.bnpproject.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.knd.hack.bnpproject.EDA.Seguro;
import com.knd.hack.bnpproject.EDA.Usuario;
import com.knd.hack.bnpproject.R;
import com.knd.hack.bnpproject.siniestro.listaDenunciasActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

/**
 * Created by Ian on 14-10-2017.
 */

public class mainActivity extends AppCompatActivity {

    private FeatureCoverFlow coverFlow;
    private CoverFlowAdapter adapter;
    private ArrayList<Seguro> seguros;
    RelativeLayout siniestro;
    TextView nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coverFlow = (FeatureCoverFlow) findViewById(R.id.coverflow);

        settingDummyData();
        adapter = new CoverFlowAdapter(this, seguros);
        coverFlow.setAdapter(adapter);
        coverFlow.setOnScrollPositionListener(onScrollListener());

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
    private FeatureCoverFlow.OnScrollPositionListener onScrollListener() {
        return new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                Log.v("MainActiivty", "position: " + position);
            }

            @Override
            public void onScrolling() {
                Log.i("MainActivity", "scrolling");
            }
        };
    }

    private void settingDummyData() {
        seguros = new ArrayList<>();
        seguros.add(new Seguro(R.drawable.seguro1, "Seguro Hogar"));
        seguros.add(new Seguro(R.drawable.seguro2, "Seguro Auto"));
        seguros.add(new Seguro(R.drawable.seguro3, "Seguro de Vida"));

    }
}
