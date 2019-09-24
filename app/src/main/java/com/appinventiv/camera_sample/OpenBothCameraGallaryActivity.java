package com.appinventiv.camera_sample;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class OpenBothCameraGallaryActivity extends AppCompatActivity {

    private ImageView imageView;
    private static final int PICK_IMAGE_ID = 234; // the number doesn't matter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_both_camera_gallary);

        imageView = findViewById(R.id.img_openabothcameragallary_show_pics);
        
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

        Glide.with(this).load(R.drawable.ic_launcher_background).circleCrop().placeholder(R.drawable.ic_launcher_background).into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPickImage();
            }
        });

    }

    public void onPickImage() {
        Intent chooseImageIntent = ImagePicker.getPickImageIntent(this);
        startActivityForResult(chooseImageIntent, PICK_IMAGE_ID);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case PICK_IMAGE_ID:

                Bitmap bitmap = ImagePicker.getImageFromResult(this, resultCode, data);
                Glide.with(this).load(bitmap).circleCrop().placeholder(R.drawable.ic_launcher_background).into(imageView);
                // TODO use bitmap
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

}


