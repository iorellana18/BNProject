package com.knd.hack.bnpproject.main;

/**
 * Created by RockHopper on 15-10-2017.
 */

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.knd.hack.bnpproject.EDA.Seguro;
import com.knd.hack.bnpproject.R;

import java.util.ArrayList;

public class CoverFlowAdapter extends BaseAdapter {

    private ArrayList<Seguro> data;
    private AppCompatActivity activity;

    public CoverFlowAdapter(AppCompatActivity context, ArrayList<Seguro> objects) {
        this.activity = context;
        this.data = objects;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Seguro getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_flow_view, null, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.SeguroImage.setImageResource(data.get(position).getImageSource());
        viewHolder.SeguroName.setText(data.get(position).getTitulo());

        convertView.setOnClickListener(onClickListener(position));

        return convertView;
    }

    private View.OnClickListener onClickListener(final int position) {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(activity);
                dialog.setContentView(R.layout.dialog_seguro_info);
                dialog.setCancelable(true); // dimiss when touching outside
                dialog.setTitle("Detalle Seguro");

                TextView text = (TextView) dialog.findViewById(R.id.name);
                text.setText(getItem(position).getTitulo());

                ImageView image = (ImageView) dialog.findViewById(R.id.image);
                image.setImageResource(getItem(position).getImageSource());

                dialog.show();
            }
        };
    }


    private static class ViewHolder {
        private TextView SeguroName;
        private ImageView SeguroImage;

        public ViewHolder(View v) {
            SeguroImage = (ImageView) v.findViewById(R.id.image);
            SeguroName = (TextView) v.findViewById(R.id.name);
            int color = Color.parseColor("#000000");
            SeguroName.setBackgroundColor(color);
        }
    }
}