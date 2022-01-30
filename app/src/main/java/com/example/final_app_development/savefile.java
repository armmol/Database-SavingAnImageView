package com.example.final_app_development;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class savefile extends AppCompatActivity {

    Button Save;
    ImageView img;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_savefile);

        Save = findViewById (R.id.save_button);
        img = findViewById (R.id.imageView_savewindow);
        Save.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                clicksavefile();
            }
        });
        img.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                clicktochoosephoto();
            }
        });
    }

    private void clicktochoosephoto () {
        Intent intent = new Intent (Intent.ACTION_GET_CONTENT);
        intent.setType ("image/*");
        startActivityForResult (Intent.createChooser (intent, "Title"), 2);
    }

    public void clicksavefile(){
        Intent intent = new Intent (Intent.ACTION_CREATE_DOCUMENT);
        intent.setType ("image/jpeg");
        intent.addCategory (Intent.CATEGORY_OPENABLE);
        intent.putExtra (Intent.EXTRA_TITLE, "newImage.jpg");
        startActivityForResult (intent,1);
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult (requestCode, resultCode, data);
        if (requestCode == 1) {
                Uri uri = data.getData ();
                try {
                    OutputStream outputStream = getContentResolver ().openOutputStream (uri);
                    Bitmap bitmap = ((BitmapDrawable) img.getDrawable ()).getBitmap ();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream ();
                    bitmap.compress (Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] imageInByte = baos.toByteArray ();
                    outputStream.write (imageInByte);
                    outputStream.close ();
                    Toast.makeText (this, "image saved", Toast.LENGTH_SHORT).show ();
                } catch (IOException e) {
                    Toast.makeText (this, "image not saved", Toast.LENGTH_SHORT).show ();
                }
        }
        else if (requestCode == 2) {
            Uri uri = data.getData ();
            img.setImageURI (uri);
        }
    }
}
