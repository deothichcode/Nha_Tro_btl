package com.nhatro.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/** Một phòng trong danh sách phòng của truy vấn Q3 (chi tiết nhà trọ), kèm số ảnh. */
public class HostelRoomItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer maPT;
    private BigDecimal dienTich;
    private Long giaThue;
    private Long tienDien;
    private Long tienNuoc;
    private String noiThat;
    private Integer soNguoiOToiDa;
    private String tinhTrangPhong;
    private Integer soHinhAnh;

    public HostelRoomItem() {
    }

    public HostelRoomItem(Integer maPT, BigDecimal dienTich, Long giaThue, Long tienDien, Long tienNuoc,
                          String noiThat, Integer soNguoiOToiDa, String tinhTrangPhong, Integer soHinhAnh) {
        this.maPT = maPT;
        this.dienTich = dienTich;
        this.giaThue = giaThue;
        this.tienDien = tienDien;
        this.tienNuoc = tienNuoc;
        this.noiThat = noiThat;
        this.soNguoiOToiDa = soNguoiOToiDa;
        this.tinhTrangPhong = tinhTrangPhong;
        this.soHinhAnh = soHinhAnh;
    }

    public Integer getMaPT() { return maPT; }
    public void setMaPT(Integer maPT) { this.maPT = maPT; }

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

    public Integer getSoHinhAnh() { return soHinhAnh; }
    public void setSoHinhAnh(Integer soHinhAnh) { this.soHinhAnh = soHinhAnh; }

    @Override
    public String toString() {
        return "HostelRoomItem{maPT=" + maPT + ", dienTich=" + dienTich + ", giaThue=" + giaThue
                + ", tienDien=" + tienDien + ", tienNuoc=" + tienNuoc + ", noiThat='" + noiThat
                + "', soNguoiOToiDa=" + soNguoiOToiDa + ", tinhTrangPhong='" + tinhTrangPhong
                + "', soHinhAnh=" + soHinhAnh + "}";
    }
}
