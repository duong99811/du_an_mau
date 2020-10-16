package com.e.du_an_mau.sqLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.e.du_an_mau.model.NguoiDung;
import com.e.du_an_mau.model.TheLoai;

import java.util.ArrayList;
import java.util.List;


public class TheLoaiDAO {
    SQLiteDatabase sqLiteDatabase;
    MySqlite mySqlite;

    public TheLoaiDAO(MySqlite mySqlite) {
        this.mySqlite = mySqlite;
    }
    public boolean addTheLoai(TheLoai theLoai) {
        SQLiteDatabase sqLiteDatabase = mySqlite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("matheloai",theLoai.matheloai);
        contentValues.put("tentheloai",theLoai.tentheloai);
        contentValues.put("vitri",theLoai.vitri);
        contentValues.put("mota",theLoai.mota);
        long result = sqLiteDatabase.insert("THELOAI", null, contentValues);
        return result > 0;
    }
    public boolean updateTheLoai(TheLoai theLoai) {
        SQLiteDatabase sqLiteDatabase = mySqlite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("matheloai",theLoai.matheloai);
        contentValues.put("tentheloai",theLoai.tentheloai);
        contentValues.put("vitri",theLoai.vitri);
        contentValues.put("mota",theLoai.mota);
        long result = sqLiteDatabase.update("THELOAI", contentValues, "matheloai=?", new String[]{theLoai.matheloai});
        return result > 0;
    }
    public boolean deleteTheLoai(String matheloai) {
        SQLiteDatabase sqLiteDatabase = mySqlite.getWritableDatabase();
        long result = sqLiteDatabase.delete("THELOAI", "matheloai=?", new String[]{matheloai});
        return result > 0;
    }
    public List<TheLoai> getAllTheLoai() {
        List<TheLoai> theLoaiList = new ArrayList<>();
        String sql = "SELECT * FROM THELOAI";
        Cursor cursor = mySqlite.getReadableDatabase().rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String matheloai = cursor.getString(0);
                String tentheloai = cursor.getString(1);
                String vitri = cursor.getString(2);
                String mota = cursor.getString(3);
                TheLoai theLoai = new TheLoai(matheloai,tentheloai,vitri,mota);
                theLoaiList.add(theLoai);
                cursor.moveToNext();
            }
        }
        return theLoaiList;
    }
}
