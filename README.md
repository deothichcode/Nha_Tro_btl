# Hệ thống tìm kiếm nhà trọ và ghép bạn cùng phòng

Bài tập lớn môn Cơ sở dữ liệu — Học viện Công nghệ Bưu chính Viễn thông (PTIT).

Hệ thống cho phép người dùng tìm phòng trọ theo khu vực, giá, diện tích và số người ở;
chủ trọ đăng bài cho thuê; người dùng đăng bài tìm phòng, tìm bạn cùng phòng và được
gợi ý người phù hợp để ở ghép.

## Thành viên

| Thành viên | Họ tên | Phụ trách |
|---|---|---|
| TV1 | (điền tên) | Thiết kế CSDL, dữ liệu mẫu, truy vấn Q1–Q3, Entity |
| TV2 | (điền tên) | Cấu hình project, truy vấn Q4–Q8, DAO/Repository |
| TV3 | (điền tên) | Redis cache, giao diện Console, kiểm thử, báo cáo |

## Công nghệ

- MySQL 8.0.16 trở lên (cần hỗ trợ ràng buộc `CHECK`)
- Java 17 trở lên, Spring Boot 3.5, Spring Data JPA, JdbcTemplate
- Redis (cổng mặc định 6379)

## Cấu trúc thư mục

```
database/                  Các file SQL
  schema.sql               Tạo CSDL, bảng, ràng buộc, index, trigger
  data.sql                 Dữ liệu mẫu
  queries_tv1.sql          Truy vấn Q1–Q3
  queries_tv2.sql          Truy vấn Q4–Q8
docs/                      Lược đồ ER
src/main/java/com/nhatro/
  entity/                  Ánh xạ các bảng CSDL
  dto/                     Đối tượng chứa kết quả truy vấn
  repository/              Interface JpaRepository (thêm/sửa/xóa)
  dao/                     Thực thi 8 truy vấn bằng JdbcTemplate
  cache/                   Tích hợp Redis
  console/                 Menu giao diện Console
```

## Cách chạy

1. **Tạo CSDL:** mở MySQL Workbench, chạy lần lượt `database/schema.sql` rồi `database/data.sql`.
2. **Đặt mật khẩu MySQL** (không ghi mật khẩu vào code):
   - IntelliJ: *Run → Edit Configurations → NhaTroApplication → Environment variables*,
     thêm `DB_USERNAME=root;DB_PASSWORD=mat_khau_cua_ban`.
   - Hoặc dòng lệnh Windows (PowerShell): `$env:DB_PASSWORD="mat_khau_cua_ban"`.
3. **Bật Redis** (cài trực tiếp hoặc `docker run -d -p 6379:6379 redis`).
4. **Chạy ứng dụng:** chạy class `NhaTroApplication` trong IntelliJ,
   hoặc dòng lệnh `./mvnw spring-boot:run` (Windows: `mvnw.cmd spring-boot:run`).

Chạy đúng sẽ thấy dòng: `Kết nối CSDL nha_tro_db thành công!` với 15 người dùng và 5 nhà trọ.

## Quy ước làm việc nhóm

- Mỗi thành viên làm trên nhánh riêng (`tv1-...`, `tv2-...`, `tv3-...`), xong tạo Pull Request vào `main`.
- Mỗi người tự commit phần việc của mình; commit message ghi rõ nội dung thay đổi.
- `database/schema.sql` là bản thiết kế duy nhất: muốn sửa CSDL thì sửa file này và báo cả nhóm.
