package com.e.du_an_mau.model;

public class HoaDonChiTiet {
    public String mahoadonchitiet;
    public HoaDon hoaDon;
    public Sach sach;
    public String soluongmua;


    public HoaDonChiTiet(HoaDon hoaDon, String tensach, String soLuong) {
    }

    public HoaDonChiTiet(String mahoadonchitiet, HoaDon hoaDon, Sach sach, String soluongmua) {
        this.mahoadonchitiet = mahoadonchitiet;
        this.hoaDon = hoaDon;
        this.sach = sach;
        this.soluongmua = soluongmua;
    }
}


