package com.e.du_an_mau.sqLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.e.du_an_mau.model.HoaDon;
import com.e.du_an_mau.model.HoaDonChiTiet;
import com.e.du_an_mau.model.Sach;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoaDonChiTietDAO {
    MySqlite mySqlite;
    SQLiteDatabase sqLiteDatabase;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
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
    public List<HoaDonChiTiet> getAllHDCT() {
        List<HoaDonChiTiet> hoaDonChiTietList = new ArrayList<>();
        String sql = "SELECT * FROM HOADONCHITIET";
        Cursor cursor = mySqlite.getReadableDatabase().rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String mahoadonchitiet = cursor.getString(0);
                Date ngaymua = null;
                String mahoadon =cursor.getString(1);
                try {
                    ngaymua = dateFormat.parse(cursor.getString(2));
                } catch (ParseException e) { e.printStackTrace();}
                String masach = cursor.getString(3);
                String matheloai = cursor.getString(4);
                String tensach = cursor.getString(5);
                String tacgia = cursor.getString(6);
                String NXB = cursor.getString(7);
                String giabia = cursor.getString(8);
                String soluong = cursor.getString(9);
                String soluongmua =cursor.getString(10);
                HoaDon hoadon = new HoaDon(mahoadon,ngaymua);
                Sach sach = new Sach(masach,matheloai,tensach,tacgia,NXB,giabia,soluong);
                HoaDonChiTiet hdct = new HoaDonChiTiet(mahoadonchitiet,hoadon,sach,soluongmua);
                hoaDonChiTietList.add(hdct);
                cursor.moveToNext();
            }
        }
        return hoaDonChiTietList;
    }
    public boolean deleteHDCT(String hoaDonChiTiet) {
        SQLiteDatabase sqLiteDatabase = mySqlite.getWritableDatabase();
        long result = sqLiteDatabase.delete("HOADONCHITIET", "mahoadonchitiet=?", new String[]{hoaDonChiTiet});
        return result > 0;
    }
}
