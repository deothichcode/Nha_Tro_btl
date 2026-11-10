package com.nhatro.repository;

import com.nhatro.entity.HostelAmenity;
import com.nhatro.entity.HostelAmenityId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Bảng NHATRO_TIENICH (khóa chính ghép MaNT, MaTI): thêm, sửa, xóa, tìm theo khóa chính do JpaRepository cung cấp sẵn
 * (save, findById, findAll, deleteById, count...).
 * Các hàm bên dưới Spring Data tự sinh câu SQL từ tên hàm.
 */
@Repository
public interface HostelAmenityRepository extends JpaRepository<HostelAmenity, HostelAmenityId> {

    List<HostelAmenity> findByMaNT(Integer maNT);
}
