package com.e.du_an_mau.sqLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqlite extends SQLiteOpenHelper {

    public MySqlite(Context context) {
        super(context, "mydata.sql", null,1 );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String user_table = "CREATE TABLE USER (username char(15) primary key,password varchar,numberPhone char,name varchar)";
        String theloai_table = "CREATE TABLE THELOAI(matheloai char(15) primary key,tentheloai varchar,vitri char,mota varchar)";
        String sach_table = "CREATE TABLE SACH(masach char(15) primary key,matheloai char(15),tensach varchar,tacgia varchar," +
                "NXB varchar,giabia char,soluong char)";
        String hoadonct_table = "CREATE TABLE HOADONCHITIET (mahoadonchitiet integer primary key AUTOINCREMENT,mahoadon" +
                "varchar, masach varchar, soluongmua varchar)";
        String hoadon_table ="CREATE TABLE HoaDon (mahoadon text primary key, ngaymua date)";
        sqLiteDatabase.execSQL(user_table);
        sqLiteDatabase.execSQL(theloai_table);
        sqLiteDatabase.execSQL(sach_table);
        sqLiteDatabase.execSQL(hoadonct_table);
        sqLiteDatabase.execSQL(hoadon_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
