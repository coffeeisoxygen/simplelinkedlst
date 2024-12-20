package com.coffecode.data;

public class Data {
    private String nim;
    private String nama;
    private int nilai;

    public Data(String nim, String nama, int nilai) {
        this.nim = nim;
        this.nama = nama;
        this.nilai = nilai;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getNilai() {
        return nilai;
    }

    public void setNilai(int nilai) {
        this.nilai = nilai;
    }

    public void infoData() {

        System.out.println("NIM: " + nim + ", Nama: " + nama + ", Nilai: " + nilai);

    }
}