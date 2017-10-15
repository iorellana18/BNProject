package com.knd.hack.bnpproject.recognition;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.knd.hack.bnpproject.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ian on 14-10-2017.
 */

public class genericActivity extends Activity {

    Button detectar;
    TextView text;    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_TAKE_VIDEO = 1;
    static final int REQUEST_VIDEO_CAPTURE = 1;
    String mCurrentPhotoPath;
    String mCurrentVideoPath;

    private Camera camera = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textrecognition);

        detectar = (Button) findViewById(R.id.detectar);
        text = (TextView) findViewById(R.id.text);

        text.setText("Ahora es recomendable que grabe un corto video del estado del auto");
        detectar.setText("Grabar");

        detectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakeVideoIntent();
            }
        });
    }

        private void dispatchTakePictureIntent() {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // Ensure that there's a camera activity to handle the intent
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                // Create the File where the photo should go
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {
                    // Error occurred while creating the File
                    ex.printStackTrace();
                    Toast.makeText(this, "Error al crear archivo", Toast.LENGTH_SHORT).show();
                }
                // Continue only if the File was successfully created
                if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(this,
                            "com.knd.hack.bnpproject.fileprovider",
                            photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    try {
                        startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                    } catch (Error e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Error al sacar foto", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }

        private File createImageFile() throws IOException {
            // Create an image file name
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = "JPEG_" + timeStamp + "_";
            File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            File image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );

            // Save a file: path for use with ACTION_VIEW intents
            mCurrentPhotoPath = image.getAbsolutePath();
            return image;
        }

        private void dispatchTakeVideoIntent() {
            Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            // Ensure that there's a camera activity to handle the intent
            if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
                // Create the File where the photo should go
                File videoFile = null;
                try {
                    videoFile = createVideoFile();
                } catch (IOException ex) {
                    // Error occurred while creating the File
                    ex.printStackTrace();
                    Toast.makeText(this, "Error al crear archivo", Toast.LENGTH_SHORT).show();
                }
                // Continue only if the File was successfully created
                if (videoFile != null) {
                    Uri videoURI = FileProvider.getUriForFile(this,
                            "com.knd.hack.bnpproject.fileprovider",
                            videoFile);
                    takeVideoIntent.putExtra(MediaStore.EXTRA_OUTPUT, videoURI);
                    try {
                        startActivityForResult(takeVideoIntent, REQUEST_TAKE_VIDEO);
                    } catch (Error e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Error al obtener video", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }

        private File createVideoFile() throws IOException {
            // Create an image file name
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String videoFileName = "MP4_" + timeStamp + "_";
            File storageDir = getExternalFilesDir(Environment.DIRECTORY_MOVIES);
            File video = File.createTempFile(
                    videoFileName,  /* prefix */
                    ".mp4",         /* suffix */
                    storageDir      /* directory */
            );

            // Save a file: path for use with ACTION_VIEW intents
            mCurrentVideoPath = video.getAbsolutePath();
            return video;
        }

        private void galleryAddPic() {
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            File f = new File(mCurrentPhotoPath);
            Uri contentUri = Uri.fromFile(f);
            mediaScanIntent.setData(contentUri);
            this.sendBroadcast(mediaScanIntent);
        }

        private void galleryAddMov() {
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            File f = new File(mCurrentVideoPath);
            Uri contentUri = Uri.fromFile(f);
            mediaScanIntent.setData(contentUri);
            this.sendBroadcast(mediaScanIntent);
        }

        public  void selectPictures(){
            try{
                // TODO access the 4 pictures to send
            }catch(Error e){
                e.printStackTrace();
                Toast.makeText(this, "Error al cargar las fotos", Toast.LENGTH_SHORT).show();
            }
        }

        public void uploadPicturesT(){
            try{
                // TODO  Firebase server

            }catch (Error e){
                e.printStackTrace();
                Toast.makeText(this, "Error al enviar las fotos", Toast.LENGTH_SHORT).show();
            }
        }

        /**
         * Use api camera for get an video
         * */

        /**
         * Use SDK and API of  Quickblox for embed Video Streaming/Chat https://quickblox.com/developers/Sample-webrtc-android
         * */
}


