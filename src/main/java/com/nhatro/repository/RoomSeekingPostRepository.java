package com.nhatro.repository;

import com.nhatro.entity.RoomSeekingPost;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Bảng BAI_DANG_TIM_PHONG: thêm, sửa, xóa, tìm theo khóa chính do JpaRepository cung cấp sẵn
 * (save, findById, findAll, deleteById, count...).
 * Các hàm bên dưới Spring Data tự sinh câu SQL từ tên hàm.
 */
@Repository
public interface RoomSeekingPostRepository extends JpaRepository<RoomSeekingPost, Integer> {

    List<RoomSeekingPost> findByMaND(Integer maND);
}
