package com.e.du_an_mau.sqLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.e.du_an_mau.model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private SQLiteDatabase sqLiteDatabase;
    private MySqlite mySqlite;

    public UserDAO(MySqlite mySqlite) {
        this.mySqlite = mySqlite;
    }
    //add
    public boolean addUser(NguoiDung nguoiDung) {
        SQLiteDatabase sqLiteDatabase = mySqlite.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("username", nguoiDung.username);
        contentValues.put("name", nguoiDung.ten);
        contentValues.put("password", nguoiDung.password);
        contentValues.put("numberPhone", nguoiDung.sdt);
        long result = sqLiteDatabase.insert("USER", null, contentValues);

        if (result > 0) return true;
        else return false;
    }
    //update
    public boolean updateUser(NguoiDung nguoiDung) {
        SQLiteDatabase sqLiteDatabase = mySqlite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", nguoiDung.username);
        contentValues.put("name", nguoiDung.ten);
        contentValues.put("password", nguoiDung.password);
        contentValues.put("numberPhone", nguoiDung.sdt);
        long result = sqLiteDatabase.update("USER",  contentValues,"username=?",new String[]{nguoiDung.username});
        if (result > 0) return true;
        else return false;
    }
    //delete

    public boolean deleteUser(String username){
        SQLiteDatabase sqLiteDatabase = mySqlite.getWritableDatabase();
        long result = sqLiteDatabase.delete("USER",  "username=?",new String[]{username});
        if (result > 0) return true;
        else return false;
    }
    //getAllUser

    public List<NguoiDung> getAllUser(){
        List<NguoiDung> nguoiDungList = new ArrayList<>();
        String sql =  "SELECT * FROM USER";
        Cursor cursor = mySqlite.getReadableDatabase().rawQuery(sql,null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                String username = cursor.getString(0);
                String name = cursor.getString(1);
                String password = cursor.getString(2);
                String sdt = cursor.getString(3);

                NguoiDung nguoiDung = new NguoiDung();
                nguoiDung.username = username;
                nguoiDung.ten = name;
                nguoiDung.password= password;
                nguoiDung.sdt = sdt;

                nguoiDungList.add(nguoiDung);
                cursor.moveToNext();
            }
        }
        return  nguoiDungList;
    }
}
