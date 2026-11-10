package com.nhatro.entity;

import jakarta.persistence.*;
import java.io.Serializable;

/** Bảng NHA_TRO: nhà trọ, thuộc sở hữu của một người dùng (chủ trọ). */
@Entity
@Table(name = "NHA_TRO")
public class Hostel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaNT")
    private Integer maNT;

    /** Khóa ngoại tới NGUOI_DUNG (chủ trọ) */
    @Column(name = "MaND", nullable = false)
    private Integer maND;

    @Column(name = "TenNhaTro", nullable = false, length = 100)
    private String tenNhaTro;

    @Column(name = "DiaChi", nullable = false, length = 255)
    private String diaChi;

    /** Tên quận/huyện, VD: Hà Đông */
    @Column(name = "KhuVuc", nullable = false, length = 50)
    private String khuVuc;

    public Hostel() {
    }

    public Hostel(Integer maNT, Integer maND, String tenNhaTro, String diaChi, String khuVuc) {
        this.maNT = maNT;
        this.maND = maND;
        this.tenNhaTro = tenNhaTro;
        this.diaChi = diaChi;
        this.khuVuc = khuVuc;
    }

    public Integer getMaNT() { return maNT; }
    public void setMaNT(Integer maNT) { this.maNT = maNT; }

    public Integer getMaND() { return maND; }
    public void setMaND(Integer maND) { this.maND = maND; }

    public String getTenNhaTro() { return tenNhaTro; }
    public void setTenNhaTro(String tenNhaTro) { this.tenNhaTro = tenNhaTro; }

    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    public String getKhuVuc() { return khuVuc; }
    public void setKhuVuc(String khuVuc) { this.khuVuc = khuVuc; }

    @Override
    public String toString() {
        return "Hostel{maNT=" + maNT + ", maND=" + maND + ", tenNhaTro='" + tenNhaTro
                + "', diaChi='" + diaChi + "', khuVuc='" + khuVuc + "'}";
    }
}
