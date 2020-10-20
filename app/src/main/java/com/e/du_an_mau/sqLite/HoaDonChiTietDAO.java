package com.e.du_an_mau.sqLite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.e.du_an_mau.model.HoaDonChiTiet;

public class HoaDonChiTietDAO {
    MySqlite mySqlite;
    SQLiteDatabase sqLiteDatabase;

    public HoaDonChiTietDAO(MySqlite mySqlite) {this.mySqlite = mySqlite; }

    public boolean addHDCT(HoaDonChiTiet hdtc) {
        sqLiteDatabase = mySqlite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mahoadonchitiet", hdtc.mahoadonchitiet);
        contentValues.put("mahoadon", hdtc.hoaDon.maHoaDon);
        contentValues.put("tensach", hdtc.sach.tensach);
        contentValues.put("soluongmua", hdtc.soluongmua);
        long result = sqLiteDatabase.insert("HOADONCHITIET", null, contentValues);
        return result > 0;
    }
}
