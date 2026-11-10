package com.nhatro.repository;

import com.nhatro.entity.Hostel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Bảng NHA_TRO: thêm, sửa, xóa, tìm theo khóa chính do JpaRepository cung cấp sẵn
 * (save, findById, findAll, deleteById, count...).
 * Các hàm bên dưới Spring Data tự sinh câu SQL từ tên hàm.
 */
@Repository
public interface HostelRepository extends JpaRepository<Hostel, Integer> {

    List<Hostel> findByMaND(Integer maND);

    List<Hostel> findByKhuVuc(String khuVuc);
}
