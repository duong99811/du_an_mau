package com.e.du_an_mau.model;

public class Sach {
    private String masach;
    private String matheloai;
    private String tensach;
    private String tacgia;
    private String NXB;
    private String giabia;
    private String soluong;

    public Sach(String masach, String matheloai, String tensach, String tacgia, String NXB, String giabia, String soluong) {
        this.masach = masach;
        this.matheloai = matheloai;
        this.tensach = tensach;
        this.tacgia = tacgia;
        this.NXB = NXB;
        this.giabia = giabia;
        this.soluong = soluong;
    }

    public String getMasach() {
        return masach;
    }

    public void setMasach(String masach) {
        this.masach = masach;
    }

    public String getMatheloai() {
        return matheloai;
    }

    public void setMatheloai(String matheloai) {
        this.matheloai = matheloai;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getNXB() {
        return NXB;
    }

    public void setNXB(String NXB) {
        this.NXB = NXB;
    }

    public String getGiabia() {
        return giabia;
    }

    public void setGiabia(String giabia) {
        this.giabia = giabia;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }
}

