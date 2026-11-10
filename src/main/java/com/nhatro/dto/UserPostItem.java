package com.nhatro.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Một bài đăng bất kỳ của người dùng trong truy vấn Q7.
 * Ba loại bài đăng được gộp về chung các cột bằng UNION ALL.
 */
public class UserPostItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 'Cho thuê', 'Tìm phòng' hoặc 'Tìm bạn' */
    private String loaiBaiDang;
    private Integer maBaiDang;
    private String khuVuc;
    /** Cho thuê: giá phòng; Tìm phòng: giá tối đa; Tìm bạn: giá thuê dự kiến */
    private Long mucGia;
    private String noiDung;
    private LocalDateTime ngayDang;
    /** Bài tìm phòng không có cột trạng thái nên để null */
    private String trangThai;

    public UserPostItem() {
    }

    public UserPostItem(String loaiBaiDang, Integer maBaiDang, String khuVuc, Long mucGia,
                        String noiDung, LocalDateTime ngayDang, String trangThai) {
        this.loaiBaiDang = loaiBaiDang;
        this.maBaiDang = maBaiDang;
        this.khuVuc = khuVuc;
        this.mucGia = mucGia;
        this.noiDung = noiDung;
        this.ngayDang = ngayDang;
        this.trangThai = trangThai;
    }

    public String getLoaiBaiDang() { return loaiBaiDang; }
    public void setLoaiBaiDang(String loaiBaiDang) { this.loaiBaiDang = loaiBaiDang; }

    public Integer getMaBaiDang() { return maBaiDang; }
    public void setMaBaiDang(Integer maBaiDang) { this.maBaiDang = maBaiDang; }

    public String getKhuVuc() { return khuVuc; }
    public void setKhuVuc(String khuVuc) { this.khuVuc = khuVuc; }

    public Long getMucGia() { return mucGia; }
    public void setMucGia(Long mucGia) { this.mucGia = mucGia; }

    public String getNoiDung() { return noiDung; }
    public void setNoiDung(String noiDung) { this.noiDung = noiDung; }

    public LocalDateTime getNgayDang() { return ngayDang; }
    public void setNgayDang(LocalDateTime ngayDang) { this.ngayDang = ngayDang; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    @Override
    public String toString() {
        return "UserPostItem{loaiBaiDang='" + loaiBaiDang + "', maBaiDang=" + maBaiDang
                + ", khuVuc='" + khuVuc + "', mucGia=" + mucGia + ", noiDung='" + noiDung
                + "', ngayDang=" + ngayDang + ", trangThai='" + trangThai + "'}";
    }
}
