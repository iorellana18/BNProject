package com.knd.hack.bnpproject.formulario;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.knd.hack.bnpproject.EDA.Usuario;
import com.knd.hack.bnpproject.R;
import com.knd.hack.bnpproject.finalClass;
import com.knd.hack.bnpproject.main.mainActivity;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Ian on 15-10-2017.
 */

public class formActivity extends AppCompatActivity {

    private TextView voiceInput;
    private TextView speakButton;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        voiceInput = (TextView) findViewById(R.id.voiceInput);
        speakButton = (TextView) findViewById(R.id.btnSpeak);
        final Usuario user = (Usuario)getIntent().getSerializableExtra("user");


        final String patente = getIntent().getStringExtra("patente");
        speakButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(flag) {
                    askSpeechInput();
                    speakButton.setText("finalizar");
                    flag=false;
                }else{
                    Intent intent = new Intent(formActivity.this,finalClass.class);
                    intent.putExtra("user",user);
                    intent.putExtra("patente",patente);
                    intent.putExtra("voice",voiceInput.getText());
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    // Showing google speech input dialog

    private void askSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "es-ES");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Cuentanos tu caso");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    // Receiving speech input

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    voiceInput.setText(result.get(0));
                }
                break;
            }

        }
    }
}
