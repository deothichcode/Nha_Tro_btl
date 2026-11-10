package com.nhatro.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/** Kết quả truy vấn Q4: bài đăng tìm phòng kèm tên và số điện thoại người đăng. */
public class RoomSeekingPostView implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer maBDTP;
    private Integer maND;
    private String hoTen;
    private String soDienThoai;
    private String khuVuc;
    private Long giaToiDa;
    private BigDecimal dienTich;
    private Integer soNguoiO;
    private String noiDung;
    private LocalDateTime ngayDang;

    public RoomSeekingPostView() {
    }

    public RoomSeekingPostView(Integer maBDTP, Integer maND, String hoTen, String soDienThoai, String khuVuc,
                               Long giaToiDa, BigDecimal dienTich, Integer soNguoiO, String noiDung,
                               LocalDateTime ngayDang) {
        this.maBDTP = maBDTP;
        this.maND = maND;
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.khuVuc = khuVuc;
        this.giaToiDa = giaToiDa;
        this.dienTich = dienTich;
        this.soNguoiO = soNguoiO;
        this.noiDung = noiDung;
        this.ngayDang = ngayDang;
    }

    public Integer getMaBDTP() { return maBDTP; }
    public void setMaBDTP(Integer maBDTP) { this.maBDTP = maBDTP; }

    public Integer getMaND() { return maND; }
    public void setMaND(Integer maND) { this.maND = maND; }

    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }

    public String getSoDienThoai() { return soDienThoai; }
    public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }

    public String getKhuVuc() { return khuVuc; }
    public void setKhuVuc(String khuVuc) { this.khuVuc = khuVuc; }

    public Long getGiaToiDa() { return giaToiDa; }
    public void setGiaToiDa(Long giaToiDa) { this.giaToiDa = giaToiDa; }

    public BigDecimal getDienTich() { return dienTich; }
    public void setDienTich(BigDecimal dienTich) { this.dienTich = dienTich; }

    public Integer getSoNguoiO() { return soNguoiO; }
    public void setSoNguoiO(Integer soNguoiO) { this.soNguoiO = soNguoiO; }

    public String getNoiDung() { return noiDung; }
    public void setNoiDung(String noiDung) { this.noiDung = noiDung; }

    public LocalDateTime getNgayDang() { return ngayDang; }
    public void setNgayDang(LocalDateTime ngayDang) { this.ngayDang = ngayDang; }

    @Override
    public String toString() {
        return "RoomSeekingPostView{maBDTP=" + maBDTP + ", maND=" + maND + ", hoTen='" + hoTen
                + "', soDienThoai='" + soDienThoai + "', khuVuc='" + khuVuc + "', giaToiDa=" + giaToiDa
                + ", dienTich=" + dienTich + ", soNguoiO=" + soNguoiO + ", noiDung='" + noiDung
                + "', ngayDang=" + ngayDang + "}";
    }
}
