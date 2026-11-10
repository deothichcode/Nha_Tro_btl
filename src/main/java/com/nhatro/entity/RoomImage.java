package com.nhatro.entity;

import jakarta.persistence.*;
import java.io.Serializable;

/** Bảng HINH_ANH_PHONG: một phòng có nhiều hình ảnh. */
@Entity
@Table(name = "HINH_ANH_PHONG")
public class RoomImage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaHA")
    private Integer maHA;

    /** Khóa ngoại tới PHONG_TRO */
    @Column(name = "MaPT", nullable = false)
    private Integer maPT;

    @Column(name = "TenHinhAnh", nullable = false, length = 100)
    private String tenHinhAnh;

    @Column(name = "DuongDan", nullable = false, length = 255)
    private String duongDan;

    public RoomImage() {
    }

    public RoomImage(Integer maHA, Integer maPT, String tenHinhAnh, String duongDan) {
        this.maHA = maHA;
        this.maPT = maPT;
        this.tenHinhAnh = tenHinhAnh;
        this.duongDan = duongDan;
    }

    public Integer getMaHA() { return maHA; }
    public void setMaHA(Integer maHA) { this.maHA = maHA; }

    public Integer getMaPT() { return maPT; }
    public void setMaPT(Integer maPT) { this.maPT = maPT; }

    public String getTenHinhAnh() { return tenHinhAnh; }
    public void setTenHinhAnh(String tenHinhAnh) { this.tenHinhAnh = tenHinhAnh; }

    public String getDuongDan() { return duongDan; }
    public void setDuongDan(String duongDan) { this.duongDan = duongDan; }

    @Override
    public String toString() {
        return "RoomImage{maHA=" + maHA + ", maPT=" + maPT + ", tenHinhAnh='" + tenHinhAnh
                + "', duongDan='" + duongDan + "'}";
    }
}
