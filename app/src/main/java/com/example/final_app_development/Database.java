package com.example.final_app_development;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Database extends AppCompatActivity {

    private EditText clubname, wins, loses, points;
    private Button Add, delete, read, update;
    private DatabaseHelper databaseHelper;
    private TextView display;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_database);

        clubname = findViewById (R.id.clubname);
        wins = findViewById (R.id.wins);
        loses = findViewById (R.id.loses);
        points = findViewById (R.id.points);
        Add = findViewById (R.id.button_AddtoDB);
        delete = findViewById (R.id.Delete);
        update = findViewById (R.id.update);
        read = findViewById (R.id.ReadDatabse);
        display = findViewById (R.id.databasedisplay);

        databaseHelper = new DatabaseHelper (this);
        Add.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                String name = clubname.getText ().toString ();
                String win = wins.getText ().toString ();
                String loss = loses.getText ().toString ();
                String point = points.getText ().toString ();

                if(name.isEmpty ()||win.isEmpty ()||loss.isEmpty ()||point.isEmpty ()){
                    Toast.makeText (Database.this, "ENTER ALL DATA", Toast.LENGTH_SHORT).show ();
                    return;
                }

                databaseHelper.addteam (name,win,loss,point);
                Toast.makeText (Database.this, " DATA ADDED ", Toast.LENGTH_SHORT).show ();
                clubname.setText ("");
                wins.setText ("");
                loses.setText ("");
                points.setText ("");
            }
        });

        read.setOnClickListener (new View.OnClickListener () {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick (View view) {
                display.setText ("");
                ArrayList<Club> clubs = databaseHelper.getclubs ();
                if(clubs.isEmpty ()){
                    display.setText (getString(R.string.Empty_Database));
                }
                else{
                    for (Club club : clubs) {
                        display.setText (String.format ("%s %s %d %d %d\n",
                                display.getText ().toString (),
                                club.getName (),
                                club.getWins (),
                                club.getLoses (),
                                club.getPoints ()));
                    }
                }
            }
        });

        delete.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {

            }
        });

        update.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                Intent i = new Intent (Database.this, UpdateDatabase.class);
                startActivity (i);
            }
        });

    }


}
