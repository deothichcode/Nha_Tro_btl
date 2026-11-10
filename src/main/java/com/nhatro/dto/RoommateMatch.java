package com.nhatro.dto;

import java.io.Serializable;

/**
 * Kết quả truy vấn Q6: một cặp bài đăng tìm bạn ghép được với nhau
 * (cùng khu vực, chênh giá trong ngưỡng, giới tính hợp theo cả hai chiều).
 */
public class RoommateMatch implements Serializable {

    private static final long serialVersionUID = 1L;

    private String khuVuc;

    private Integer maBDTB1;
    private Integer maND1;
    private String hoTen1;
    private String gioiTinh1;
    private Long giaThue1;

    private Integer maBDTB2;
    private Integer maND2;
    private String hoTen2;
    private String gioiTinh2;
    private Long giaThue2;

    /** ABS(giaThue1 - giaThue2) */
    private Long chenhLechGia;

    public RoommateMatch() {
    }

    public RoommateMatch(String khuVuc, Integer maBDTB1, Integer maND1, String hoTen1, String gioiTinh1,
                         Long giaThue1, Integer maBDTB2, Integer maND2, String hoTen2, String gioiTinh2,
                         Long giaThue2, Long chenhLechGia) {
        this.khuVuc = khuVuc;
        this.maBDTB1 = maBDTB1;
        this.maND1 = maND1;
        this.hoTen1 = hoTen1;
        this.gioiTinh1 = gioiTinh1;
        this.giaThue1 = giaThue1;
        this.maBDTB2 = maBDTB2;
        this.maND2 = maND2;
        this.hoTen2 = hoTen2;
        this.gioiTinh2 = gioiTinh2;
        this.giaThue2 = giaThue2;
        this.chenhLechGia = chenhLechGia;
    }

    public String getKhuVuc() { return khuVuc; }
    public void setKhuVuc(String khuVuc) { this.khuVuc = khuVuc; }

    public Integer getMaBDTB1() { return maBDTB1; }
    public void setMaBDTB1(Integer maBDTB1) { this.maBDTB1 = maBDTB1; }

    public Integer getMaND1() { return maND1; }
    public void setMaND1(Integer maND1) { this.maND1 = maND1; }

    public String getHoTen1() { return hoTen1; }
    public void setHoTen1(String hoTen1) { this.hoTen1 = hoTen1; }

    public String getGioiTinh1() { return gioiTinh1; }
    public void setGioiTinh1(String gioiTinh1) { this.gioiTinh1 = gioiTinh1; }

    public Long getGiaThue1() { return giaThue1; }
    public void setGiaThue1(Long giaThue1) { this.giaThue1 = giaThue1; }

    public Integer getMaBDTB2() { return maBDTB2; }
    public void setMaBDTB2(Integer maBDTB2) { this.maBDTB2 = maBDTB2; }

    public Integer getMaND2() { return maND2; }
    public void setMaND2(Integer maND2) { this.maND2 = maND2; }

    public String getHoTen2() { return hoTen2; }
    public void setHoTen2(String hoTen2) { this.hoTen2 = hoTen2; }

    public String getGioiTinh2() { return gioiTinh2; }
    public void setGioiTinh2(String gioiTinh2) { this.gioiTinh2 = gioiTinh2; }

    public Long getGiaThue2() { return giaThue2; }
    public void setGiaThue2(Long giaThue2) { this.giaThue2 = giaThue2; }

    public Long getChenhLechGia() { return chenhLechGia; }
    public void setChenhLechGia(Long chenhLechGia) { this.chenhLechGia = chenhLechGia; }

    @Override
    public String toString() {
        return "RoommateMatch{khuVuc='" + khuVuc + "', bai " + maBDTB1 + " (" + hoTen1 + ", " + gioiTinh1
                + ", " + giaThue1 + ") <-> bai " + maBDTB2 + " (" + hoTen2 + ", " + gioiTinh2 + ", " + giaThue2
                + "), chenhLechGia=" + chenhLechGia + "}";
    }
}
