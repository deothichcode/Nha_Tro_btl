package com.nhatro.repository;

import com.nhatro.entity.RoommatePost;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Bảng BAI_DANG_TIM_BAN: thêm, sửa, xóa, tìm theo khóa chính do JpaRepository cung cấp sẵn
 * (save, findById, findAll, deleteById, count...).
 * Các hàm bên dưới Spring Data tự sinh câu SQL từ tên hàm.
 */
@Repository
public interface RoommatePostRepository extends JpaRepository<RoommatePost, Integer> {

    List<RoommatePost> findByMaND(Integer maND);
}
