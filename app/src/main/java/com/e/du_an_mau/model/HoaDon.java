package com.e.du_an_mau.model;

import java.util.Date;

public class HoaDon {
    public String maHoaDon;
    public Date ngayMua;

    public HoaDon() {
    }

    public HoaDon(String maHoaDon, Date ngayMua) {
        this.maHoaDon = maHoaDon;
        this.ngayMua = ngayMua;
    }
}
