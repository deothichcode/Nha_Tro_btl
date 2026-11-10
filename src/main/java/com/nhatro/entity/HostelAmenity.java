package com.nhatro.entity;

import jakarta.persistence.*;
import java.io.Serializable;

/** Bảng NHATRO_TIENICH: bảng trung gian cho quan hệ N-N "Có tiện ích". */
@Entity
@Table(name = "NHATRO_TIENICH")
@IdClass(HostelAmenityId.class)
public class HostelAmenity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "MaNT")
    private Integer maNT;

    @Id
    @Column(name = "MaTI")
    private Integer maTI;

    public HostelAmenity() {
    }

    public HostelAmenity(Integer maNT, Integer maTI) {
        this.maNT = maNT;
        this.maTI = maTI;
    }

    public Integer getMaNT() { return maNT; }
    public void setMaNT(Integer maNT) { this.maNT = maNT; }

    public Integer getMaTI() { return maTI; }
    public void setMaTI(Integer maTI) { this.maTI = maTI; }

    @Override
    public String toString() {
        return "HostelAmenity{maNT=" + maNT + ", maTI=" + maTI + "}";
    }
}
