package com.example.final_app_development;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UpdateDatabase extends AppCompatActivity {

    EditText Oname, NName, Nwins, NLosses, NPoints;
    Button update;


    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_updatedata);

        Oname = findViewById (R.id.originalname);
        NName = findViewById (R.id.newname);
        Nwins = findViewById (R.id.newWins);
        NLosses = findViewById (R.id.newLoses);
        NPoints = findViewById (R.id.newPoints);
        update = findViewById (R.id.button_updatescreen);

        DatabaseHelper dbhelper = new DatabaseHelper (UpdateDatabase.this);

        update.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                int flag = 0;
                ArrayList<Club> clubs = dbhelper.getclubs ();
                for (Club club:clubs) {
                    if(club.getName ().equals (Oname.getText ().toString ()))
                        flag = 1;
                }
                if(Oname.getText ().toString ().isEmpty ()||NName.getText ().toString ().isEmpty ()||Nwins.getText ().toString ().isEmpty ()|| NLosses.getText ().toString ().isEmpty ()|| NPoints.getText ().toString ().isEmpty ()) {
                    Toast.makeText (UpdateDatabase.this, "ENTER ALL VALUES", Toast.LENGTH_LONG).show ();
                }else {
                    for (Club club : clubs) {
                        if (club.getName ().toString ().equals (Oname.getText ().toString ())) {
                            dbhelper.updateClub (Oname.getText ().toString (), NName.getText ().toString (), Nwins.getText ().toString (), NLosses.getText ().toString (), NPoints.getText ().toString ());
                            Toast.makeText (UpdateDatabase.this, "UPDATED", Toast.LENGTH_LONG).show ();
                            flag = 1;
                        }
                    }
                    if(flag == 1)
                        startActivity (new Intent (UpdateDatabase.this, Database.class));
                    else
                        Toast.makeText (UpdateDatabase.this, "ENTER CORRECT VALUES, DATA TO UPDATE NOT FOUND", Toast.LENGTH_LONG).show ();

                }
            }
        });


    }
}
