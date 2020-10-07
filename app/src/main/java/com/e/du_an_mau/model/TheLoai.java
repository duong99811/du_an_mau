package com.e.du_an_mau.model;

public class TheLoai {
    private String matheloai;
    private String tentheloai;
    private String vitri;
    private String mota;


    public TheLoai(String matheloai, String tentheloai, String vitri, String mota) {
        this.matheloai = matheloai;
        this.tentheloai = tentheloai;
        this.vitri = vitri;
        this.mota = mota;
    }

    public TheLoai() {
    }

    public String getMatheloai() {
        return matheloai;
    }

    public void setMatheloai(String matheloai) {
        this.matheloai = matheloai;
    }

    public String getTentheloai() {
        return tentheloai;
    }

    public void setTentheloai(String tentheloai) {
        this.tentheloai = tentheloai;
    }

    public String getVitri() {
        return vitri;
    }

    public void setVitri(String vitri) {
        this.vitri = vitri;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}

