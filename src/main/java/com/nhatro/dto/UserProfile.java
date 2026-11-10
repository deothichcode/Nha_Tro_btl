package com.nhatro.dto;

import com.nhatro.entity.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** Kết quả truy vấn Q7: thông tin cá nhân của một người dùng và toàn bộ bài đăng của họ. */
public class UserProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    private User nguoiDung;
    private List<UserPostItem> baiDang = new ArrayList<>();

    public UserProfile() {
    }

    public UserProfile(User nguoiDung, List<UserPostItem> baiDang) {
        this.nguoiDung = nguoiDung;
        this.baiDang = baiDang;
    }

    public User getNguoiDung() { return nguoiDung; }
    public void setNguoiDung(User nguoiDung) { this.nguoiDung = nguoiDung; }

    public List<UserPostItem> getBaiDang() { return baiDang; }
    public void setBaiDang(List<UserPostItem> baiDang) { this.baiDang = baiDang; }

    @Override
    public String toString() {
        return "UserProfile{nguoiDung=" + nguoiDung + ", soBaiDang="
                + (baiDang == null ? 0 : baiDang.size()) + "}";
    }
}
