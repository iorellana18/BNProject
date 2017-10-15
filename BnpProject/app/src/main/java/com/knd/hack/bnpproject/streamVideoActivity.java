package com.knd.hack.bnpproject;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.knd.hack.bnpproject.formulario.formActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Ian on 14-10-2017.
 */

public class streamVideoActivity extends Activity {

    Button si;
    Button no;
    TextView text;
    boolean flag;
    Button continuar;

    private static ArrayList<String> skypeNames = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_streamvideo);

        si = (Button)findViewById(R.id.si);
        no = (Button)findViewById(R.id.no);
        text = (TextView)findViewById(R.id.text);
        continuar = (Button)findViewById(R.id.continuar);

        flag = true;

        text.setText("Desea realizar una videollamada con nuestros agentes para mejorar su experiencia?");

        chargueAvailableAssistant();

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(streamVideoActivity.this,formActivity.class);
                startActivity(intent);
                finish();
            }
        });

        si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startVideoCall();
                si.setVisibility(View.GONE);
                no.setVisibility(View.GONE);
                continuar.setVisibility(View.VISIBLE);
            }
        });

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(streamVideoActivity.this,formActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void chargueAvailableAssistant(){
        skypeNames.add("hikarusmusic");
        skypeNames.add("ian.orellana1");
    }

    public String getAvailableAssistant(){
        Collections.shuffle(skypeNames);
        return skypeNames.get(0);
    }

    public void startVideoCall(){
        String skypeUri = "skype:" + getAvailableAssistant() + "?call&video=true";
        initiateSkypeUri(getBaseContext(), skypeUri);
    }

    /**
     * Initiate the actions encoded in the specified URI.
     */
    public void initiateSkypeUri(Context myContext, String mySkypeUri) {

        // Make sure the Skype for Android client is installed.
        if (!isSkypeClientInstalled(myContext)) {
            goToMarket(myContext);
            return;
        }

        // Create the Intent from our Skype URI.
        Uri skypeUri = Uri.parse(mySkypeUri);
        Intent myIntent = new Intent(Intent.ACTION_VIEW, skypeUri);

        // Restrict the Intent to being handled by the Skype for Android client only.
        myIntent.setComponent(new ComponentName("com.skype.raider", "com.skype.raider.Main"));
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Initiate the Intent. It should never fail because you've already established the
        // presence of its handler (although there is an extremely minute window where that
        // handler can go away).
        Toast.makeText(this, "Llamando a: " + mySkypeUri, Toast.LENGTH_SHORT).show();
        myContext.startActivity(myIntent);

        return;
    }
    /**
     * Determine whether the Skype for Android client is installed on this device.
     */
    public boolean isSkypeClientInstalled(Context myContext) {
        PackageManager myPackageMgr = myContext.getPackageManager();
        try {
            myPackageMgr.getPackageInfo("com.skype.raider", PackageManager.GET_ACTIVITIES);
        }
        catch (PackageManager.NameNotFoundException e) {
            return (false);
        }
        return (true);
    }
    /**
     * Install the Skype client through the market: URI scheme.
     */
    public void goToMarket(Context myContext) {
        Uri marketUri = Uri.parse("market://details?id=com.skype.raider");
        Intent myIntent = new Intent(Intent.ACTION_VIEW, marketUri);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        myContext.startActivity(myIntent);

        return;
    }


}
