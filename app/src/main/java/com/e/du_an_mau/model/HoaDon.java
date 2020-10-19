package com.e.du_an_mau.model;

import java.util.Date;

public class HoaDon {
    private String maHoaDon;
    private Date ngayMua;

    public HoaDon() {
    }

    public HoaDon(String maHoaDon, Date ngayMua) {
        this.maHoaDon = maHoaDon;
        this.ngayMua = ngayMua;
    }
}
