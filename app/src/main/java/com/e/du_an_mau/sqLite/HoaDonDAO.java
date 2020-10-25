package com.e.du_an_mau.sqLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.e.du_an_mau.model.HoaDon;
import com.e.du_an_mau.model.Sach;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoaDonDAO {

    MySqlite mySqlite;
    SQLiteDatabase sqLiteDatabase;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public HoaDonDAO(MySqlite mySqlite) { this.mySqlite = mySqlite;}


    public boolean addHoaDon(HoaDon hoaDon) {
        SQLiteDatabase sqLiteDatabase = mySqlite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mahoadon", hoaDon.maHoaDon);
        try {
            contentValues.put("ngaymua", String.valueOf(dateFormat.parse(String.valueOf(hoaDon.ngayMua))));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long result = sqLiteDatabase.insert("HOADON", null, contentValues);
        return result > 0;
    }
    public List<HoaDon> getAllHoaDon() {
        List<HoaDon> hoaDonList = new ArrayList<>();
        String sql = "SELECT * FROM HOADON";
        Cursor cursor = mySqlite.getReadableDatabase().rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String mahoadon = cursor.getString(0);
                Date ngaymua= null;
                try {
                    ngaymua = dateFormat.parse(cursor.getString(2));
                } catch (ParseException e) { e.printStackTrace();}
                HoaDon hoaDon = new HoaDon(mahoadon, ngaymua);
                hoaDonList.add(hoaDon);
                cursor.moveToNext();
            }
        }
        return hoaDonList;
    }
}
