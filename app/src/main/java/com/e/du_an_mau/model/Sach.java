package com.e.du_an_mau.model;

public class Sach {
    public String masach;
    public String matheloai;
    public String tensach;
    public String tacgia;
    public String NXB;
    public String giabia;
    public String soluong;

    public Sach() {
    }

    public Sach(String masach, String matheloai, String tensach, String tacgia, String NXB, String giabia, String soluong) {
        this.masach = masach;
        this.matheloai = matheloai;
        this.tensach = tensach;
        this.tacgia = tacgia;
        this.NXB = NXB;
        this.giabia = giabia;
        this.soluong = soluong;
    }
    public String toString() {
        return masach+" | "+tensach;
    }
}

