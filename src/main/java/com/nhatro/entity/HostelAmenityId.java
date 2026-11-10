package com.nhatro.entity;

import java.io.Serializable;
import java.util.Objects;

/** Khóa chính ghép (MaNT, MaTI) của bảng NHATRO_TIENICH. JPA bắt buộc có equals/hashCode. */
public class HostelAmenityId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer maNT;
    private Integer maTI;

    public HostelAmenityId() {
    }

    public HostelAmenityId(Integer maNT, Integer maTI) {
        this.maNT = maNT;
        this.maTI = maTI;
    }

    public Integer getMaNT() { return maNT; }
    public void setMaNT(Integer maNT) { this.maNT = maNT; }

    public Integer getMaTI() { return maTI; }
    public void setMaTI(Integer maTI) { this.maTI = maTI; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HostelAmenityId)) return false;
        HostelAmenityId other = (HostelAmenityId) o;
        return Objects.equals(maNT, other.maNT) && Objects.equals(maTI, other.maTI);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maNT, maTI);
    }
}
