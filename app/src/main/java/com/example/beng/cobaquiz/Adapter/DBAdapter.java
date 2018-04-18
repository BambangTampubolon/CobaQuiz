package com.example.beng.cobaquiz.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.beng.cobaquiz.Model.Ronde;
import com.example.beng.cobaquiz.Model.Room;

/**
 * Created by Beng on 4/3/2018.
 */

public class DBAdapter extends SQLiteOpenHelper{


    //setting property database room
    private static final String TABLE_ROOM = "ROOM_DATABASE";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_NAMA_USER = "USER_LIST";

    //setting property database round
    private static final String TABLE_ROUND = "ROUND_DATABASE";
    private static final String COLUMN_ID_ROOM = "ROOM_ID";
    private static final String COLUMN_NAMA_USER_BENAR = "USER_BENAR";
    public static final String COLUMN_RONDE_KE = "ID";
    public static final String COLUMN_CARD = "CARD_LIST";

    public DBAdapter(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableRoom = "CREATE TABLE " + TABLE_ROOM + "(" + COLUMN_ID
                + " INTEGER PRIMARY KEY," + COLUMN_NAMA_USER + " TEXT);";
        String createTableRound = "CREATE TABLE " +  TABLE_ROUND + "(" + COLUMN_RONDE_KE
                + " INTEGER PRIMARY KEY," + COLUMN_ID_ROOM + " INTEGER,"
                + COLUMN_CARD + " TEXT," + COLUMN_NAMA_USER_BENAR + " TEXT,"
                + "FOREIGN KEY(" + COLUMN_ID_ROOM + ") REFERENCES " + TABLE_ROOM
                + "(" + COLUMN_ID + "));";
        sqLiteDatabase.execSQL(createTableRoom);
        sqLiteDatabase.execSQL(createTableRound);
        sqLiteDatabase.execSQL("PRAGMA foreign_keys = ON;");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ROOM);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ROUND);
        onCreate(sqLiteDatabase);
    }

    public int addRoom(Room room){
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, room.getIdRoom());
        values.put(COLUMN_NAMA_USER, room.getListUserinString());
        for(Ronde r : room.getListRonde()){
            ContentValues roundValue = new ContentValues();
            roundValue.put(COLUMN_ID_ROOM, r.getIdRoom());
            roundValue.put(COLUMN_RONDE_KE, r.getIdRonde());
            roundValue.put(COLUMN_CARD, r.getListCardinString());
        }

        return 0;
    }
}
