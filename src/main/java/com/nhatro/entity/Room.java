package com.nhatro.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/** Bảng PHONG_TRO: phòng trọ, thuộc một nhà trọ. */
@Entity
@Table(name = "PHONG_TRO")
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaPT")
    private Integer maPT;

    /** Khóa ngoại tới NHA_TRO */
    @Column(name = "MaNT", nullable = false)
    private Integer maNT;

    /** m2 — DECIMAL(5,1) */
    @Column(name = "DienTich", nullable = false, precision = 5, scale = 1)
    private BigDecimal dienTich;

    /** VNĐ/tháng */
    @Column(name = "GiaThue", nullable = false)
    private Long giaThue;

    /** VNĐ/kWh */
    @Column(name = "TienDien", nullable = false)
    private Long tienDien;

    /** VNĐ/m3 */
    @Column(name = "TienNuoc", nullable = false)
    private Long tienNuoc;

    @Column(name = "NoiThat", length = 255)
    private String noiThat;

    @Column(name = "SoNguoiOToiDa", nullable = false)
    private Integer soNguoiOToiDa;

    /** 'Trống' hoặc 'Đã thuê' */
    @Column(name = "TinhTrangPhong", nullable = false, length = 10)
    private String tinhTrangPhong;

    public Room() {
    }

    public Room(Integer maPT, Integer maNT, BigDecimal dienTich, Long giaThue, Long tienDien,
                Long tienNuoc, String noiThat, Integer soNguoiOToiDa, String tinhTrangPhong) {
        this.maPT = maPT;
        this.maNT = maNT;
        this.dienTich = dienTich;
        this.giaThue = giaThue;
        this.tienDien = tienDien;
        this.tienNuoc = tienNuoc;
        this.noiThat = noiThat;
        this.soNguoiOToiDa = soNguoiOToiDa;
        this.tinhTrangPhong = tinhTrangPhong;
    }

    /** JPA gửi NULL nếu trường bị bỏ trống, ghi đè DEFAULT của MySQL -> tự gán mặc định. */
    @PrePersist
    private void ganMacDinh() {
        if (tinhTrangPhong == null) tinhTrangPhong = "Trống";
    }

    public Integer getMaPT() { return maPT; }
    public void setMaPT(Integer maPT) { this.maPT = maPT; }

    public Integer getMaNT() { return maNT; }
    public void setMaNT(Integer maNT) { this.maNT = maNT; }

    public BigDecimal getDienTich() { return dienTich; }
    public void setDienTich(BigDecimal dienTich) { this.dienTich = dienTich; }

    public Long getGiaThue() { return giaThue; }
    public void setGiaThue(Long giaThue) { this.giaThue = giaThue; }

    public Long getTienDien() { return tienDien; }
    public void setTienDien(Long tienDien) { this.tienDien = tienDien; }

    public Long getTienNuoc() { return tienNuoc; }
    public void setTienNuoc(Long tienNuoc) { this.tienNuoc = tienNuoc; }

    public String getNoiThat() { return noiThat; }
    public void setNoiThat(String noiThat) { this.noiThat = noiThat; }

    public Integer getSoNguoiOToiDa() { return soNguoiOToiDa; }
    public void setSoNguoiOToiDa(Integer soNguoiOToiDa) { this.soNguoiOToiDa = soNguoiOToiDa; }

    public String getTinhTrangPhong() { return tinhTrangPhong; }
    public void setTinhTrangPhong(String tinhTrangPhong) { this.tinhTrangPhong = tinhTrangPhong; }

    @Override
    public String toString() {
        return "Room{maPT=" + maPT + ", maNT=" + maNT + ", dienTich=" + dienTich
                + ", giaThue=" + giaThue + ", tienDien=" + tienDien + ", tienNuoc=" + tienNuoc
                + ", noiThat='" + noiThat + "', soNguoiOToiDa=" + soNguoiOToiDa
                + ", tinhTrangPhong='" + tinhTrangPhong + "'}";
    }
}
