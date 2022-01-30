package com.example.final_app_development;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DBname = "Footballdb";

    private static final int DBversion = 1;

    private static final String TableName = "Teams";

    private static final String Id_COL = "id";

    private static final String ClubName_COL = "Club";

    private static final String Wins_COL = "Wins";

    private static final String Loses_COL = "Loses";

    private static final String Points_COL = "Points";

    public DatabaseHelper(Context context){
        super(context,DBname,null,DBversion);
    }

    @Override
    public void onCreate (SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TableName + "("
                + Id_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ClubName_COL + " TEXT,"
                + Wins_COL + " TEXT,"
                + Loses_COL + " TEXT,"
                + Points_COL + " TEXT )";
        sqLiteDatabase.execSQL (query);

    }

    public void addteam(String ClubName, String wins, String loses, String points){
        SQLiteDatabase db = this.getWritableDatabase ();

        ContentValues values = new ContentValues ();
        values.put (ClubName_COL, ClubName);
        values.put (Wins_COL, wins);
        values.put (Loses_COL, loses);
        values.put (Points_COL, points);

        db.insert (TableName, null, values);
        db.close ();
    }

    public ArrayList<Club> getclubs(){
        SQLiteDatabase db = this.getReadableDatabase ();
        Cursor cursor = db.rawQuery ("SELECT * FROM "+ TableName, null);
        ArrayList<Club> Clubs = new ArrayList<> ();
        if(cursor.moveToFirst ()){
            do{
                Clubs.add (new Club(cursor.getString (1)
                        , Integer.parseInt (cursor.getString (2))
                        , Integer.parseInt (cursor.getString (3))
                        , Integer.parseInt (cursor.getString (4))));
            }while (cursor.moveToNext ());
        }

        return Clubs;
    }

    public void updateClub(String OClub, String ClubName, String wins, String loses , String points) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase ();
        ContentValues values = new ContentValues ();

        values.put (ClubName_COL, ClubName);
        values.put (Wins_COL, wins);
        values.put (Loses_COL, loses);
        values.put (Points_COL, points);

        sqLiteDatabase.update (TableName, values,"Club=?", new String []{OClub});
        sqLiteDatabase.close ();
    }


    @Override
    public void onUpgrade (SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL ("DROP TABLE IF EXISTS " + TableName);
        onCreate (sqLiteDatabase);
    }
}
