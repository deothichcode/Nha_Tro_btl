package com.nhatro.dto;

import java.io.Serializable;

/** Kết quả truy vấn Q8: số phòng trống và đã thuê của một nhà trọ. */
public class HostelRoomStats implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer maNT;
    private String tenNhaTro;
    private String khuVuc;
    private Integer soPhongTrong;
    private Integer soPhongDaThue;
    private Integer tongSoPhong;

    public HostelRoomStats() {
    }

    public HostelRoomStats(Integer maNT, String tenNhaTro, String khuVuc, Integer soPhongTrong,
                           Integer soPhongDaThue, Integer tongSoPhong) {
        this.maNT = maNT;
        this.tenNhaTro = tenNhaTro;
        this.khuVuc = khuVuc;
        this.soPhongTrong = soPhongTrong;
        this.soPhongDaThue = soPhongDaThue;
        this.tongSoPhong = tongSoPhong;
    }

    public Integer getMaNT() { return maNT; }
    public void setMaNT(Integer maNT) { this.maNT = maNT; }

    public String getTenNhaTro() { return tenNhaTro; }
    public void setTenNhaTro(String tenNhaTro) { this.tenNhaTro = tenNhaTro; }

    public String getKhuVuc() { return khuVuc; }
    public void setKhuVuc(String khuVuc) { this.khuVuc = khuVuc; }

    public Integer getSoPhongTrong() { return soPhongTrong; }
    public void setSoPhongTrong(Integer soPhongTrong) { this.soPhongTrong = soPhongTrong; }

    public Integer getSoPhongDaThue() { return soPhongDaThue; }
    public void setSoPhongDaThue(Integer soPhongDaThue) { this.soPhongDaThue = soPhongDaThue; }

    public Integer getTongSoPhong() { return tongSoPhong; }
    public void setTongSoPhong(Integer tongSoPhong) { this.tongSoPhong = tongSoPhong; }

    @Override
    public String toString() {
        return "HostelRoomStats{maNT=" + maNT + ", tenNhaTro='" + tenNhaTro + "', khuVuc='" + khuVuc
                + "', soPhongTrong=" + soPhongTrong + ", soPhongDaThue=" + soPhongDaThue
                + ", tongSoPhong=" + tongSoPhong + "}";
    }
}
