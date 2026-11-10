package com.nhatro.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/** Kết quả truy vấn Q5: bài đăng tìm bạn cùng phòng kèm thông tin người đăng. */
public class RoommatePostView implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer maBDTB;
    private Integer maND;
    private String hoTen;
    private String gioiTinh;
    /** Tính trong SQL: TIMESTAMPDIFF(YEAR, NgaySinh, CURDATE()) */
    private Integer tuoi;
    private String soDienThoai;
    private String khuVuc;
    private Long giaThue;
    private String gioiTinhMongMuon;
    private LocalDate ngayBatDau;
    /** Số tháng */
    private Integer thoiHan;
    private String noiDungYeuCau;
    private LocalDateTime ngayDang;

    public RoommatePostView() {
    }

    public RoommatePostView(Integer maBDTB, Integer maND, String hoTen, String gioiTinh, Integer tuoi,
                            String soDienThoai, String khuVuc, Long giaThue, String gioiTinhMongMuon,
                            LocalDate ngayBatDau, Integer thoiHan, String noiDungYeuCau, LocalDateTime ngayDang) {
        this.maBDTB = maBDTB;
        this.maND = maND;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.tuoi = tuoi;
        this.soDienThoai = soDienThoai;
        this.khuVuc = khuVuc;
        this.giaThue = giaThue;
        this.gioiTinhMongMuon = gioiTinhMongMuon;
        this.ngayBatDau = ngayBatDau;
        this.thoiHan = thoiHan;
        this.noiDungYeuCau = noiDungYeuCau;
        this.ngayDang = ngayDang;
    }

    public Integer getMaBDTB() { return maBDTB; }
    public void setMaBDTB(Integer maBDTB) { this.maBDTB = maBDTB; }

    public Integer getMaND() { return maND; }
    public void setMaND(Integer maND) { this.maND = maND; }

    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }

    public String getGioiTinh() { return gioiTinh; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }

    public Integer getTuoi() { return tuoi; }
    public void setTuoi(Integer tuoi) { this.tuoi = tuoi; }

    public String getSoDienThoai() { return soDienThoai; }
    public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }

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

    @Override
    public String toString() {
        return "RoommatePostView{maBDTB=" + maBDTB + ", maND=" + maND + ", hoTen='" + hoTen
                + "', gioiTinh='" + gioiTinh + "', tuoi=" + tuoi + ", soDienThoai='" + soDienThoai
                + "', khuVuc='" + khuVuc + "', giaThue=" + giaThue + ", gioiTinhMongMuon='" + gioiTinhMongMuon
                + "', ngayBatDau=" + ngayBatDau + ", thoiHan=" + thoiHan + ", noiDungYeuCau='" + noiDungYeuCau
                + "', ngayDang=" + ngayDang + "}";
    }
}
