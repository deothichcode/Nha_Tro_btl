package com.nhatro.repository;

import com.nhatro.entity.Room;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Bảng PHONG_TRO: thêm, sửa, xóa, tìm theo khóa chính do JpaRepository cung cấp sẵn
 * (save, findById, findAll, deleteById, count...).
 * Các hàm bên dưới Spring Data tự sinh câu SQL từ tên hàm.
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    List<Room> findByMaNT(Integer maNT);

    List<Room> findByMaNTAndTinhTrangPhong(Integer maNT, String tinhTrangPhong);
}
