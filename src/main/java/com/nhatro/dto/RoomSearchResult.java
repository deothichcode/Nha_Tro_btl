package com.nhatro.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Kết quả truy vấn Q1 (phòng trống theo khu vực) và Q2 (tìm phòng theo nhu cầu).
 * Mỗi đối tượng là một phòng trọ kèm thông tin nhà trọ chứa nó.
 */
public class RoomSearchResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer maPT;
    private Integer maNT;
    private String tenNhaTro;
    private String diaChi;
    private String khuVuc;
    private BigDecimal dienTich;
    private Long giaThue;
    private Integer soNguoiOToiDa;
    private String noiThat;

    public RoomSearchResult() {
    }

    public RoomSearchResult(Integer maPT, Integer maNT, String tenNhaTro, String diaChi, String khuVuc,
                            BigDecimal dienTich, Long giaThue, Integer soNguoiOToiDa, String noiThat) {
        this.maPT = maPT;
        this.maNT = maNT;
        this.tenNhaTro = tenNhaTro;
        this.diaChi = diaChi;
        this.khuVuc = khuVuc;
        this.dienTich = dienTich;
        this.giaThue = giaThue;
        this.soNguoiOToiDa = soNguoiOToiDa;
        this.noiThat = noiThat;
    }

    public Integer getMaPT() { return maPT; }
    public void setMaPT(Integer maPT) { this.maPT = maPT; }

    public Integer getMaNT() { return maNT; }
    public void setMaNT(Integer maNT) { this.maNT = maNT; }

    public String getTenNhaTro() { return tenNhaTro; }
    public void setTenNhaTro(String tenNhaTro) { this.tenNhaTro = tenNhaTro; }

    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    public String getKhuVuc() { return khuVuc; }
    public void setKhuVuc(String khuVuc) { this.khuVuc = khuVuc; }

    public BigDecimal getDienTich() { return dienTich; }
    public void setDienTich(BigDecimal dienTich) { this.dienTich = dienTich; }

    public Long getGiaThue() { return giaThue; }
    public void setGiaThue(Long giaThue) { this.giaThue = giaThue; }

    public Integer getSoNguoiOToiDa() { return soNguoiOToiDa; }
    public void setSoNguoiOToiDa(Integer soNguoiOToiDa) { this.soNguoiOToiDa = soNguoiOToiDa; }

    public String getNoiThat() { return noiThat; }
    public void setNoiThat(String noiThat) { this.noiThat = noiThat; }

    @Override
    public String toString() {
        return "RoomSearchResult{maPT=" + maPT + ", maNT=" + maNT + ", tenNhaTro='" + tenNhaTro
                + "', diaChi='" + diaChi + "', khuVuc='" + khuVuc + "', dienTich=" + dienTich
                + ", giaThue=" + giaThue + ", soNguoiOToiDa=" + soNguoiOToiDa
                + ", noiThat='" + noiThat + "'}";
    }
}
