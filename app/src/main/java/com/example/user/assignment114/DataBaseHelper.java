package com.example.user.assignment114;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by user on 09-11-2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        //writing query creating table that contain's ID , first name and last name
        String createTable=" CREATE  TABLE "+Constants.TABLE_NAME + " ("
                + Constants.ID +  " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Constants.FIRST_NAME + " TEXT ,"
                + Constants.LAST_NAME + " TEXT ) ";

        sqLiteDatabase.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    //method to count number of rows in the table to access the data
    public int getRowCountFromTable() {
        int rowcount = 0;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor= database.query(false, Constants.TABLE_NAME, null, null, null, null, null, null, null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
            rowcount=cursor.getCount();
        }
        return rowcount;
    }
    //contentValues class  used to insert data in database & it maps the data to the corresponding row & column
    public long insertFriend(ContentValues values)
    {
        long count=0;
        SQLiteDatabase database = this.getWritableDatabase();
        try {
            count= database.insert(Constants.TABLE_NAME,null,values);

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return count;
    }

    //method to return list of friend's to the mainActivity
    public ArrayList<Friends> getAllFriends()
    {
        ArrayList<Friends> list = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "select * from "+Constants.TABLE_NAME;

        //corsor object to access each row of the table
        Cursor cursor = database.rawQuery(query,null);
        Friends friends = null;
        if (cursor!=null)
        {
            cursor.moveToFirst();
            do{
                friends = new Friends();
                friends.setId(cursor.getString(0));
                friends.setFirst_name(cursor.getString(1));
                friends.setLast_name(cursor.getString(2));
                list.add(friends);
            }while (cursor.moveToNext());
        }
        return list;
    }
}
