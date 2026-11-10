package com.nhatro.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/** Bảng NGUOI_DUNG: người dùng (người tìm phòng, người tìm bạn, chủ trọ). */
@Entity
@Table(name = "NGUOI_DUNG")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaND")
    private Integer maND;

    @Column(name = "HoTen", nullable = false, length = 100)
    private String hoTen;

    @Column(name = "NgaySinh", nullable = false)
    private LocalDate ngaySinh;

    @Column(name = "QueQuan", length = 100)
    private String queQuan;

    /** 'Nam' hoặc 'Nữ' */
    @Column(name = "GioiTinh", nullable = false, length = 5)
    private String gioiTinh;

    @Column(name = "SoDienThoai", nullable = false, unique = true, length = 10)
    private String soDienThoai;

    @Column(name = "Email", nullable = false, unique = true, length = 100)
    private String email;

    public User() {
    }

    public User(Integer maND, String hoTen, LocalDate ngaySinh, String queQuan,
                String gioiTinh, String soDienThoai, String email) {
        this.maND = maND;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.queQuan = queQuan;
        this.gioiTinh = gioiTinh;
        this.soDienThoai = soDienThoai;
        this.email = email;
    }

    public Integer getMaND() { return maND; }
    public void setMaND(Integer maND) { this.maND = maND; }

    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }

    public LocalDate getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(LocalDate ngaySinh) { this.ngaySinh = ngaySinh; }

    public String getQueQuan() { return queQuan; }
    public void setQueQuan(String queQuan) { this.queQuan = queQuan; }

    public String getGioiTinh() { return gioiTinh; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }

    public String getSoDienThoai() { return soDienThoai; }
    public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "User{maND=" + maND + ", hoTen='" + hoTen + "', ngaySinh=" + ngaySinh
                + ", queQuan='" + queQuan + "', gioiTinh='" + gioiTinh
                + "', soDienThoai='" + soDienThoai + "', email='" + email + "'}";
    }
}
