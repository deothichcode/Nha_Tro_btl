package com.nhatro.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Kết quả truy vấn Q3: chi tiết một nhà trọ gồm địa chỉ, số điện thoại chủ trọ,
 * tiện ích (gom thành một chuỗi) và danh sách các phòng.
 */
public class HostelDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer maNT;
    private String tenNhaTro;
    private String diaChi;
    private String khuVuc;
    private String chuTro;
    private String soDienThoai;
    /** VD: "Điều hòa, Nóng lạnh, Wifi miễn phí, Camera an ninh" — null nếu chưa có tiện ích */
    private String tienIch;
    private List<HostelRoomItem> danhSachPhong = new ArrayList<>();

    public HostelDetail() {
    }

    public HostelDetail(Integer maNT, String tenNhaTro, String diaChi, String khuVuc, String chuTro,
                        String soDienThoai, String tienIch, List<HostelRoomItem> danhSachPhong) {
        this.maNT = maNT;
        this.tenNhaTro = tenNhaTro;
        this.diaChi = diaChi;
        this.khuVuc = khuVuc;
        this.chuTro = chuTro;
        this.soDienThoai = soDienThoai;
        this.tienIch = tienIch;
        this.danhSachPhong = danhSachPhong;
    }

    public Integer getMaNT() { return maNT; }
    public void setMaNT(Integer maNT) { this.maNT = maNT; }

    public String getTenNhaTro() { return tenNhaTro; }
    public void setTenNhaTro(String tenNhaTro) { this.tenNhaTro = tenNhaTro; }

    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    public String getKhuVuc() { return khuVuc; }
    public void setKhuVuc(String khuVuc) { this.khuVuc = khuVuc; }

    public String getChuTro() { return chuTro; }
    public void setChuTro(String chuTro) { this.chuTro = chuTro; }

    public String getSoDienThoai() { return soDienThoai; }
    public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }

    public String getTienIch() { return tienIch; }
    public void setTienIch(String tienIch) { this.tienIch = tienIch; }

    public List<HostelRoomItem> getDanhSachPhong() { return danhSachPhong; }
    public void setDanhSachPhong(List<HostelRoomItem> danhSachPhong) { this.danhSachPhong = danhSachPhong; }

    @Override
    public String toString() {
        return "HostelDetail{maNT=" + maNT + ", tenNhaTro='" + tenNhaTro + "', diaChi='" + diaChi
                + "', khuVuc='" + khuVuc + "', chuTro='" + chuTro + "', soDienThoai='" + soDienThoai
                + "', tienIch='" + tienIch + "', soPhong=" + (danhSachPhong == null ? 0 : danhSachPhong.size()) + "}";
    }
}
