package com.nhatro.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/** Bảng BAI_DANG_CHO_THUE: chủ trọ đăng bài cho thuê một phòng. */
@Entity
@Table(name = "BAI_DANG_CHO_THUE")
public class RentalPost implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaBDCT")
    private Integer maBDCT;

    /** Người đăng — phải là chủ của nhà trọ chứa phòng (trigger trong DB kiểm tra) */
    @Column(name = "MaND", nullable = false)
    private Integer maND;

    /** Khóa ngoại tới PHONG_TRO */
    @Column(name = "MaPT", nullable = false)
    private Integer maPT;

    @Column(name = "NoiDung", nullable = false, columnDefinition = "TEXT")
    private String noiDung;

    @Column(name = "NgayDang", nullable = false)
    private LocalDateTime ngayDang;

    /** 'Đang hiển thị' hoặc 'Đã ẩn' */
    @Column(name = "TrangThai", nullable = false, length = 20)
    private String trangThai;

    public RentalPost() {
    }

    public RentalPost(Integer maBDCT, Integer maND, Integer maPT, String noiDung,
                      LocalDateTime ngayDang, String trangThai) {
        this.maBDCT = maBDCT;
        this.maND = maND;
        this.maPT = maPT;
        this.noiDung = noiDung;
        this.ngayDang = ngayDang;
        this.trangThai = trangThai;
    }

    /** JPA gửi NULL nếu trường bị bỏ trống, ghi đè DEFAULT của MySQL -> tự gán mặc định. */
    @PrePersist
    private void ganMacDinh() {
        if (ngayDang == null) ngayDang = LocalDateTime.now();
        if (trangThai == null) trangThai = "Đang hiển thị";
    }

    public Integer getMaBDCT() { return maBDCT; }
    public void setMaBDCT(Integer maBDCT) { this.maBDCT = maBDCT; }

    public Integer getMaND() { return maND; }
    public void setMaND(Integer maND) { this.maND = maND; }

    public Integer getMaPT() { return maPT; }
    public void setMaPT(Integer maPT) { this.maPT = maPT; }

    public String getNoiDung() { return noiDung; }
    public void setNoiDung(String noiDung) { this.noiDung = noiDung; }

    public LocalDateTime getNgayDang() { return ngayDang; }
    public void setNgayDang(LocalDateTime ngayDang) { this.ngayDang = ngayDang; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    @Override
    public String toString() {
        return "RentalPost{maBDCT=" + maBDCT + ", maND=" + maND + ", maPT=" + maPT
                + ", noiDung='" + noiDung + "', ngayDang=" + ngayDang
                + ", trangThai='" + trangThai + "'}";
    }
}
