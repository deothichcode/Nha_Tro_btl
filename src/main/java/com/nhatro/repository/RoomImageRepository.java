package com.nhatro.repository;

import com.nhatro.entity.RoomImage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Bảng HINH_ANH_PHONG: thêm, sửa, xóa, tìm theo khóa chính do JpaRepository cung cấp sẵn
 * (save, findById, findAll, deleteById, count...).
 * Các hàm bên dưới Spring Data tự sinh câu SQL từ tên hàm.
 */
@Repository
public interface RoomImageRepository extends JpaRepository<RoomImage, Integer> {

    List<RoomImage> findByMaPT(Integer maPT);
}
