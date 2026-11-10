package com.nhatro;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Hệ thống tìm kiếm nhà trọ và ghép bạn cùng phòng.
 * Điểm khởi chạy của ứng dụng.
 */
@SpringBootApplication
public class NhaTroApplication {

    public static void main(String[] args) {
        SpringApplication.run(NhaTroApplication.class, args);
    }

    /**
     * Kiểm tra kết nối CSDL khi khởi động: đếm số người dùng và số nhà trọ.
     * Với data.sql mẫu, kết quả đúng là 15 người dùng và 5 nhà trọ.
     * Thành viên 3 sẽ thay đoạn này bằng menu Console.
     */
    @Bean
    CommandLineRunner kiemTraKetNoi(JdbcTemplate jdbc) {
        return args -> {
            Integer soNguoiDung = jdbc.queryForObject("SELECT COUNT(*) FROM NGUOI_DUNG", Integer.class);
            Integer soNhaTro = jdbc.queryForObject("SELECT COUNT(*) FROM NHA_TRO", Integer.class);
            System.out.println("=================================================");
            System.out.println(" Kết nối CSDL nha_tro_db thành công!");
            System.out.println(" Số người dùng: " + soNguoiDung);
            System.out.println(" Số nhà trọ   : " + soNhaTro);
            System.out.println("=================================================");
        };
    }
}
