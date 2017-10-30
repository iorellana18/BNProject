package com.knd.hack.bnpproject.siniestro;

import android.content.Context;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.knd.hack.bnpproject.EDA.Seguro;
import com.knd.hack.bnpproject.R;

import java.util.ArrayList;

/**
 * Created by Ian on 14-10-2017.
 */

public class siniestrosAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    TextView titulo;



    ArrayList<Seguro> seguros;

    public siniestrosAdapter(Context context, ArrayList<Seguro> seguros) {
        this.context = context;
        this.seguros=seguros;
    }


    @Override
    public int getCount() {
        return seguros.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.style_siniestro, parent, false);

        titulo = (TextView)itemView.findViewById(R.id.titulo);

        titulo.setText(seguros.get(position).getTitulo());

        return itemView;
    }

}
