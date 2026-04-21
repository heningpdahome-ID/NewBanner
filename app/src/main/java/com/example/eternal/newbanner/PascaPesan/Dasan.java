package com.example.eternal.newbanner.PascaPesan;

/**
 * Created by eternal on 3/11/18.
 */

public class Dasan {
    private String id_detail_pesanan,id_pelanggan,File_Pelanggan,Jumlah_Pesanan,
            Header,Konten,Subtitle,Ukuran,Width,Height,Finishing,id_template,id_skema_warna,Estimasi_Harga,Time,Email,Phone,Status;
    public Dasan(){

    }
/*
    public Dasan(String id_detail_pesanan,String file_Pelanggan, String jumlah_Pesanan, String header, String konten, String subtitle, String time, String status) {
       id_detail_pesanan = id_detail_pesanan;
        File_Pelanggan = file_Pelanggan;
        Jumlah_Pesanan = jumlah_Pesanan;
        Header = header;
        Konten = konten;
        Subtitle = subtitle;
        Time = time;
        Status = status;
    }
    */

    public Dasan(String id_detail_pesanan, String file_Pelanggan, String jumlah_Pesanan, String header, String konten, String subtitle, String time, String status) {
        this.id_detail_pesanan = id_detail_pesanan;
        File_Pelanggan = file_Pelanggan;
        Jumlah_Pesanan = jumlah_Pesanan;
        Header = header;
        Konten = konten;
        Subtitle = subtitle;
        Time = time;
        Status = status;
    }

    public String getId_detail_pesanan() {
        return id_detail_pesanan;
    }

    public void setId_detail_pesanan(String id_detail_pesanan) {
        this.id_detail_pesanan = id_detail_pesanan;
    }

    public String getId_pelanggan() {
        return id_pelanggan;
    }

    public void setId_pelanggan(String id_pelanggan) {
        this.id_pelanggan = id_pelanggan;
    }

    public String getFile_Pelanggan() {
        return File_Pelanggan;
    }

    public void setFile_Pelanggan(String file_Pelanggan) {
        File_Pelanggan = file_Pelanggan;
    }

    public String getJumlah_Pesanan() {
        return Jumlah_Pesanan;
    }

    public void setJumlah_Pesanan(String jumlah_Pesanan) {
        Jumlah_Pesanan = jumlah_Pesanan;
    }

    public String getHeader() {
        return Header;
    }

    public void setHeader(String header) {
        Header = header;
    }

    public String getKonten() {
        return Konten;
    }

    public void setKonten(String konten) {
        Konten = konten;
    }

    public String getSubtitle() {
        return Subtitle;
    }

    public void setSubtitle(String subtitle) {
        Subtitle = subtitle;
    }

    public String getUkuran() {
        return Ukuran;
    }

    public void setUkuran(String ukuran) {
        Ukuran = ukuran;
    }

    public String getWidth() {
        return Width;
    }

    public void setWidth(String width) {
        Width = width;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getFinishing() {
        return Finishing;
    }

    public void setFinishing(String finishing) {
        Finishing = finishing;
    }

    public String getId_template() {
        return id_template;
    }

    public void setId_template(String id_template) {
        this.id_template = id_template;
    }

    public String getId_skema_warna() {
        return id_skema_warna;
    }

    public void setId_skema_warna(String id_skema_warna) {
        this.id_skema_warna = id_skema_warna;
    }

    public String getEstimasi_Harga() {
        return Estimasi_Harga;
    }

    public void setEstimasi_Harga(String estimasi_Harga) {
        Estimasi_Harga = estimasi_Harga;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
