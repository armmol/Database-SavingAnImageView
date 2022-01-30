package com.example.final_app_development;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button savepage;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        savepage = findViewById (R.id.opensavefilepage);
        savepage.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {

            }
        });
    }
}