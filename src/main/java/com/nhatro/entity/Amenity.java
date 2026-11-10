package com.nhatro.entity;

import jakarta.persistence.*;
import java.io.Serializable;

/** Bảng TIEN_ICH: danh mục tiện ích dùng chung cho mọi nhà trọ. */
@Entity
@Table(name = "TIEN_ICH")
public class Amenity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaTI")
    private Integer maTI;

    @Column(name = "TenTienIch", nullable = false, unique = true, length = 100)
    private String tenTienIch;

    @Column(name = "MoTa", length = 255)
    private String moTa;

    public Amenity() {
    }

    public Amenity(Integer maTI, String tenTienIch, String moTa) {
        this.maTI = maTI;
        this.tenTienIch = tenTienIch;
        this.moTa = moTa;
    }

    public Integer getMaTI() { return maTI; }
    public void setMaTI(Integer maTI) { this.maTI = maTI; }

    public String getTenTienIch() { return tenTienIch; }
    public void setTenTienIch(String tenTienIch) { this.tenTienIch = tenTienIch; }

    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }

    @Override
    public String toString() {
        return "Amenity{maTI=" + maTI + ", tenTienIch='" + tenTienIch + "', moTa='" + moTa + "'}";
    }
}
