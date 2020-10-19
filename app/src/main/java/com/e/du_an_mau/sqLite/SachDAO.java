package com.e.du_an_mau.sqLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.e.du_an_mau.model.NguoiDung;
import com.e.du_an_mau.model.Sach;
import com.e.du_an_mau.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    MySqlite mySqlite;

    public SachDAO(MySqlite mySqlite) {
        this.mySqlite = mySqlite;
    }

    public boolean addSach(Sach sach) {
        SQLiteDatabase sqLiteDatabase = mySqlite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("masach", sach.masach);
        contentValues.put("matheloai", sach.matheloai);
        contentValues.put("tensach", sach.tensach);
        contentValues.put("tacgia", sach.tacgia);
        contentValues.put("NXB", sach.NXB);
        contentValues.put("giabia", sach.giabia);
        contentValues.put("soluong", sach.soluong);
        long result = sqLiteDatabase.insert("SACH", null, contentValues);
        return result > 0;
    }

    public boolean updateSach(Sach sach) {
        SQLiteDatabase sqLiteDatabase = mySqlite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("masach", sach.masach);
        contentValues.put("matheloai", sach.matheloai);
        contentValues.put("tensach", sach.tensach);
        contentValues.put("tacgia", sach.tacgia);
        contentValues.put("NXB", sach.NXB);
        contentValues.put("giabia", sach.giabia);
        contentValues.put("soluong", sach.soluong);
        long result = sqLiteDatabase.update("SACH", contentValues, "masach=?", new String[]{sach.masach});
        return result > 0;
    }

    public boolean deleteSach(String masach) {
        SQLiteDatabase sqLiteDatabase = mySqlite.getWritableDatabase();
        long result = sqLiteDatabase.delete("SACH", "masach=?", new String[]{masach});
        return result > 0;
    }
    //getAllUser
    public List<Sach> getAllSach() {
        List<Sach> sachList = new ArrayList<>();
        String sql = "SELECT * FROM SACH";
        Cursor cursor = mySqlite.getReadableDatabase().rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String masach = cursor.getString(0);
                String matheloai = cursor.getString(1);
                String tensach = cursor.getString(2);
                String tacgia = cursor.getString(3);
                String NXB = cursor.getString(4);
                String giabia = cursor.getString(5);
                String soluong = cursor.getString(6);
                Sach sach = new Sach(masach, matheloai, tensach, tacgia, NXB, giabia, soluong);
                sachList.add(sach);
                cursor.moveToNext();
            }
        }
        return sachList;
    }
    public List<Sach> searchSach(String SearchSach) {
        List<Sach> sachList = new ArrayList<>();
        String sql = "SELECT * FROM SACH WHERE tensach LIKE '%" + SearchSach + "%'";
        Cursor cursor = mySqlite.getReadableDatabase().rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String masach = cursor.getString(0);
                String matheloai = cursor.getString(1);
                String tensach = cursor.getString(2);
                String tacgia = cursor.getString(3);
                String NXB = cursor.getString(4);
                String giabia = cursor.getString(5);
                String soluong = cursor.getString(6);
                Sach sach = new Sach();
                sach.masach = masach;
                sach.matheloai = matheloai;
                sach.tensach = tensach;
                sach.tacgia = tacgia;
                sach.NXB = NXB;
                sach.giabia = giabia;
                sach.soluong = soluong;
                sachList.add(sach);
                cursor.moveToNext();
            }
        }

        return sachList;
    }
}
