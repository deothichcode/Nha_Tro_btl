package com.nhatro.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/** Bảng BAI_DANG_TIM_PHONG: người dùng đăng nhu cầu tìm phòng. */
@Entity
@Table(name = "BAI_DANG_TIM_PHONG")
public class RoomSeekingPost implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaBDTP")
    private Integer maBDTP;

    /** Người đăng — khóa ngoại tới NGUOI_DUNG */
    @Column(name = "MaND", nullable = false)
    private Integer maND;

    /** Khu vực mong muốn */
    @Column(name = "KhuVuc", nullable = false, length = 50)
    private String khuVuc;

    /** Mức giá tối đa (VNĐ/tháng) */
    @Column(name = "GiaToiDa", nullable = false)
    private Long giaToiDa;

    /** Diện tích mong muốn (m2) — DECIMAL(5,1) */
    @Column(name = "DienTich", nullable = false, precision = 5, scale = 1)
    private BigDecimal dienTich;

    @Column(name = "SoNguoiO", nullable = false)
    private Integer soNguoiO;

    @Column(name = "NoiDung", columnDefinition = "TEXT")
    private String noiDung;

    @Column(name = "NgayDang", nullable = false)
    private LocalDateTime ngayDang;

    public RoomSeekingPost() {
    }

    public RoomSeekingPost(Integer maBDTP, Integer maND, String khuVuc, Long giaToiDa,
                           BigDecimal dienTich, Integer soNguoiO, String noiDung,
                           LocalDateTime ngayDang) {
        this.maBDTP = maBDTP;
        this.maND = maND;
        this.khuVuc = khuVuc;
        this.giaToiDa = giaToiDa;
        this.dienTich = dienTich;
        this.soNguoiO = soNguoiO;
        this.noiDung = noiDung;
        this.ngayDang = ngayDang;
    }

    /** JPA gửi NULL nếu trường bị bỏ trống, ghi đè DEFAULT của MySQL -> tự gán mặc định. */
    @PrePersist
    private void ganMacDinh() {
        if (ngayDang == null) ngayDang = LocalDateTime.now();
    }

    public Integer getMaBDTP() { return maBDTP; }
    public void setMaBDTP(Integer maBDTP) { this.maBDTP = maBDTP; }

    public Integer getMaND() { return maND; }
    public void setMaND(Integer maND) { this.maND = maND; }

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
        return "RoomSeekingPost{maBDTP=" + maBDTP + ", maND=" + maND + ", khuVuc='" + khuVuc
                + "', giaToiDa=" + giaToiDa + ", dienTich=" + dienTich + ", soNguoiO=" + soNguoiO
                + ", noiDung='" + noiDung + "', ngayDang=" + ngayDang + "}";
    }
}
