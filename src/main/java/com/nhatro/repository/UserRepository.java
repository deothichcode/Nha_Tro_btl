package com.nhatro.repository;

import com.nhatro.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Bảng NGUOI_DUNG: thêm, sửa, xóa, tìm theo khóa chính do JpaRepository cung cấp sẵn
 * (save, findById, findAll, deleteById, count...).
 * Các hàm bên dưới Spring Data tự sinh câu SQL từ tên hàm.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findBySoDienThoai(String soDienThoai);

    Optional<User> findByEmail(String email);
}
