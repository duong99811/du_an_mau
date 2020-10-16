package com.e.du_an_mau.model;

public class TheLoai {
    public String matheloai,tentheloai,vitri,mota;

    public TheLoai() {
    }

    public TheLoai(String matheloai, String tentheloai, String vitri, String mota) {
        this.matheloai = matheloai;
        this.tentheloai = tentheloai;
        this.vitri = vitri;
        this.mota = mota;
    }
    public String toString() {
        return tentheloai;
    }
}

