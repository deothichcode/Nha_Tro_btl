package com.nhatro.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/** Bảng BAI_DANG_TIM_BAN: người dùng đăng nhu cầu tìm bạn cùng phòng. */
@Entity
@Table(name = "BAI_DANG_TIM_BAN")
public class RoommatePost implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaBDTB")
    private Integer maBDTB;

    /** Người đăng — khóa ngoại tới NGUOI_DUNG */
    @Column(name = "MaND", nullable = false)
    private Integer maND;

    /** Khu vực mong muốn */
    @Column(name = "KhuVuc", nullable = false, length = 50)
    private String khuVuc;

    /** Mức tiền thuê dự kiến mỗi người (VNĐ/tháng) */
    @Column(name = "GiaThue", nullable = false)
    private Long giaThue;

    /** 'Nam', 'Nữ' hoặc 'Không yêu cầu' */
    @Column(name = "GioiTinhMongMuon", nullable = false, length = 15)
    private String gioiTinhMongMuon;

    @Column(name = "NgayBatDau", nullable = false)
    private LocalDate ngayBatDau;

    /** Số tháng muốn ở */
    @Column(name = "ThoiHan", nullable = false)
    private Integer thoiHan;

    @Column(name = "NoiDungYeuCau", columnDefinition = "TEXT")
    private String noiDungYeuCau;

    @Column(name = "NgayDang", nullable = false)
    private LocalDateTime ngayDang;

    /** 'Đang tìm' hoặc 'Đã tìm được' */
    @Column(name = "TrangThai", nullable = false, length = 20)
    private String trangThai;

    public RoommatePost() {
    }

    public RoommatePost(Integer maBDTB, Integer maND, String khuVuc, Long giaThue,
                        String gioiTinhMongMuon, LocalDate ngayBatDau, Integer thoiHan,
                        String noiDungYeuCau, LocalDateTime ngayDang, String trangThai) {
        this.maBDTB = maBDTB;
        this.maND = maND;
        this.khuVuc = khuVuc;
        this.giaThue = giaThue;
        this.gioiTinhMongMuon = gioiTinhMongMuon;
        this.ngayBatDau = ngayBatDau;
        this.thoiHan = thoiHan;
        this.noiDungYeuCau = noiDungYeuCau;
        this.ngayDang = ngayDang;
        this.trangThai = trangThai;
    }

    /** JPA gửi NULL nếu trường bị bỏ trống, ghi đè DEFAULT của MySQL -> tự gán mặc định. */
    @PrePersist
    private void ganMacDinh() {
        if (ngayDang == null) ngayDang = LocalDateTime.now();
        if (trangThai == null) trangThai = "Đang tìm";
    }

    public Integer getMaBDTB() { return maBDTB; }
    public void setMaBDTB(Integer maBDTB) { this.maBDTB = maBDTB; }

    public Integer getMaND() { return maND; }
    public void setMaND(Integer maND) { this.maND = maND; }

    public String getKhuVuc() { return khuVuc; }
    public void setKhuVuc(String khuVuc) { this.khuVuc = khuVuc; }

    public Long getGiaThue() { return giaThue; }
    public void setGiaThue(Long giaThue) { this.giaThue = giaThue; }

    public String getGioiTinhMongMuon() { return gioiTinhMongMuon; }
    public void setGioiTinhMongMuon(String gioiTinhMongMuon) { this.gioiTinhMongMuon = gioiTinhMongMuon; }

    public LocalDate getNgayBatDau() { return ngayBatDau; }
    public void setNgayBatDau(LocalDate ngayBatDau) { this.ngayBatDau = ngayBatDau; }

    public Integer getThoiHan() { return thoiHan; }
    public void setThoiHan(Integer thoiHan) { this.thoiHan = thoiHan; }

    public String getNoiDungYeuCau() { return noiDungYeuCau; }
    public void setNoiDungYeuCau(String noiDungYeuCau) { this.noiDungYeuCau = noiDungYeuCau; }

    public LocalDateTime getNgayDang() { return ngayDang; }
    public void setNgayDang(LocalDateTime ngayDang) { this.ngayDang = ngayDang; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    @Override
    public String toString() {
        return "RoommatePost{maBDTB=" + maBDTB + ", maND=" + maND + ", khuVuc='" + khuVuc
                + "', giaThue=" + giaThue + ", gioiTinhMongMuon='" + gioiTinhMongMuon
                + "', ngayBatDau=" + ngayBatDau + ", thoiHan=" + thoiHan
                + ", noiDungYeuCau='" + noiDungYeuCau + "', ngayDang=" + ngayDang
                + ", trangThai='" + trangThai + "'}";
    }
}
