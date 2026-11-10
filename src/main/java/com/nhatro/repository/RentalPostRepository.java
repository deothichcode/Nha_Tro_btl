package com.nhatro.repository;

import com.nhatro.entity.RentalPost;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Bảng BAI_DANG_CHO_THUE: thêm, sửa, xóa, tìm theo khóa chính do JpaRepository cung cấp sẵn
 * (save, findById, findAll, deleteById, count...).
 * Các hàm bên dưới Spring Data tự sinh câu SQL từ tên hàm.
 */
@Repository
public interface RentalPostRepository extends JpaRepository<RentalPost, Integer> {

    List<RentalPost> findByMaND(Integer maND);

    List<RentalPost> findByMaPT(Integer maPT);
}
