package com.knd.hack.bnpproject.main;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import java.io.FileNotFoundException;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.knd.hack.bnpproject.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Ian on 14-10-2017.
 */

public class loginActivity extends Activity {

    Button escanear;
    ImageView perfil;
    ImageView perfil2;
    private ProgressDialog mProgress;
    private Uri mImageUri = null;
    private static final  int GALLERY_REQUEST =1;

    private static final int CAMERA_REQUEST_CODE=1;

    private StorageReference mStorage;
    private DatabaseReference mDatabase;

    boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        flag = true;
        escanear = (Button)findViewById(R.id.escanear);
        perfil = (ImageView)findViewById(R.id.logo);
        perfil2 = (ImageView)findViewById(R.id.img);

        escanear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flag) {
                    Intent intent = new Intent(loginActivity.this, mainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    flag=false;
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,0);
                    escanear.setText("Ingresar");
                }
                /*

                */
        }});
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        perfil.setVisibility(View.GONE);
        perfil2.setVisibility(View.VISIBLE);
        perfil2.setImageBitmap(bitmap);
        String path = Environment.getExternalStorageDirectory().toString()+ "/DirName";

        OutputStream fOut = null;
        Integer counter = 0;
        File file = new File(path, "Foto"+counter+".jpg"); // the File to save , append increasing numeric counter to prevent files from getting overwritten.
        if (!file.exists()) {
            File wallpaperDirectory = new File("/sdcard/DirName/");
            wallpaperDirectory.mkdirs();
        }
        if(file.exists()){
            file.delete();
        }
        try {
            fOut = new FileOutputStream(file);
        }catch (FileNotFoundException fnfe){}
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut); // saving the Bitmap to a file compressed as a JPEG with 85% compression rate
         try {
             fOut.flush(); // Not really required
             fOut.close(); // do not forget to close the stream
         }catch (IOException ioe){}
        try {
            MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName());
        }catch (FileNotFoundException fnfe){}

    }

}

