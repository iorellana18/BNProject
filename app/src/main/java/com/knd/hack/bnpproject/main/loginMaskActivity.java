package com.knd.hack.bnpproject.main;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.knd.hack.bnpproject.EDA.Usuario;
import com.knd.hack.bnpproject.R;

/**
 * Created by Ian on 15-10-2017.
 */

public class loginMaskActivity extends Activity {
    EditText correo;
    EditText pass;
    private ProgressDialog mDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masklogin);


    }

    public void login(View view){
        Firebase.setAndroidContext(this);

        correo = (EditText)findViewById(R.id.nombre);
        pass = (EditText)findViewById(R.id.pass);

        Firebase ref = new Firebase("https://hackea3-476ce.firebaseio.com/users");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                boolean flag=false;
                Usuario user = new Usuario();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    user = postSnapshot.getValue(Usuario.class);
                    if(user.getCorreo().matches(correo.getText().toString())) {
                        flag = true;
                        break;
                    }
                }
                if(flag){
                    Intent intent = new Intent(loginMaskActivity.this,loginActivity.class);
                    intent.putExtra("user",user);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
    }

}
