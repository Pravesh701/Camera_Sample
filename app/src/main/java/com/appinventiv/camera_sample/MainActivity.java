package com.appinventiv.camera_sample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;

    ImageView img_click_camera;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img_click_camera = findViewById(R.id.img_main_show_pics);
        btn = findViewById(R.id.btn_main_cameraGallary);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OpenBothCameraGallaryActivity.class);
                startActivity(intent);
                finish();
            }
        });

        img_click_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCamera();
            }
        });

        Glide.with(this).load(R.drawable.ic_launcher_background).circleCrop().into(img_click_camera);
    }

    private void openCamera() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){

            Bundle bundle = data.getExtras();

            Bitmap bitmap = (Bitmap) bundle.get("data");

            Glide.with(this).load(bitmap).circleCrop().placeholder(R.drawable.ic_launcher_background).into(img_click_camera);
        }
    }
}
