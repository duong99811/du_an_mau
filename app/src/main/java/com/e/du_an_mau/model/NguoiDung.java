package com.e.du_an_mau.model;

public class NguoiDung {
    public String username, password, sdt, ten;

    public NguoiDung() {
    }

    public NguoiDung(String username, String password, String sdt, String ten) {
        this.username = username;
        this.password = password;
        this.sdt = sdt;
        this.ten = ten;
    }
}