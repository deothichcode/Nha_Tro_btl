package com.nhatro.repository;

import com.nhatro.entity.Amenity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Bảng TIEN_ICH: thêm, sửa, xóa, tìm theo khóa chính do JpaRepository cung cấp sẵn
 * (save, findById, findAll, deleteById, count...).
 * Các hàm bên dưới Spring Data tự sinh câu SQL từ tên hàm.
 */
@Repository
public interface AmenityRepository extends JpaRepository<Amenity, Integer> {

    Optional<Amenity> findByTenTienIch(String tenTienIch);
}
