-- =====================================================================
--  HỆ THỐNG TÌM KIẾM NHÀ TRỌ VÀ GHÉP BẠN CÙNG PHÒNG
--  File: queries_tv1.sql  —  3 truy vấn cơ bản (Thành viên 1)
--  Chạy sau schema.sql và data.sql.
--  Giá trị in trong ngoặc nhọn ở comment là tham số: khi chuyển sang Java
--  thay giá trị cụ thể bằng dấu ? (PreparedStatement / JdbcTemplate).
-- =====================================================================
USE nha_tro_db;

-- ---------------------------------------------------------------------
-- TRUY VẤN 1: Liệt kê các phòng trọ đang còn trống theo khu vực người dùng
--             chọn, sắp xếp theo giá thuê từ thấp đến cao.
-- Tham số: {khu_vuc}
-- ---------------------------------------------------------------------
SELECT  pt.MaPT,
        nt.TenNhaTro,
        nt.DiaChi,
        pt.DienTich,
        pt.GiaThue,
        pt.SoNguoiOToiDa,
        pt.NoiThat
FROM    PHONG_TRO pt
JOIN    NHA_TRO   nt ON nt.MaNT = pt.MaNT
WHERE   pt.TinhTrangPhong = 'Trống'
  AND   nt.KhuVuc = 'Hà Đông'                -- {khu_vuc}
ORDER BY pt.GiaThue ASC;


-- ---------------------------------------------------------------------
-- TRUY VẤN 2: Tìm kiếm phòng trọ phù hợp với nhu cầu dựa trên khu vực,
--             mức giá thuê, diện tích và số người ở.
-- Tham số: {khu_vuc}, {gia_toi_da}, {dien_tich_toi_thieu}, {so_nguoi}
-- ---------------------------------------------------------------------
SELECT  pt.MaPT,
        nt.TenNhaTro,
        nt.KhuVuc,
        pt.DienTich,
        pt.GiaThue,
        pt.SoNguoiOToiDa,
        pt.NoiThat
FROM    PHONG_TRO pt
JOIN    NHA_TRO   nt ON nt.MaNT = pt.MaNT
WHERE   pt.TinhTrangPhong = 'Trống'
  AND   nt.KhuVuc        =  'Hà Đông'        -- {khu_vuc}
  AND   pt.GiaThue       <= 3000000          -- {gia_toi_da}
  AND   pt.DienTich      >= 20               -- {dien_tich_toi_thieu}
  AND   pt.SoNguoiOToiDa >= 2                -- {so_nguoi}
ORDER BY pt.GiaThue ASC;

-- TRUY VẤN 2 (mở rộng): lấy tiêu chí trực tiếp từ các bài đăng tìm phòng,
-- gợi ý phòng phù hợp cho từng bài đăng.
-- Thêm  AND bd.MaBDTP = {ma_bai_dang}  để chỉ xem một bài.
SELECT  bd.MaBDTP,
        nd.HoTen       AS NguoiTimPhong,
        bd.KhuVuc,
        bd.GiaToiDa,
        pt.MaPT,
        nt.TenNhaTro,
        pt.DienTich,
        pt.GiaThue,
        pt.SoNguoiOToiDa
FROM    BAI_DANG_TIM_PHONG bd
JOIN    NGUOI_DUNG nd ON nd.MaND   = bd.MaND
JOIN    NHA_TRO    nt ON nt.KhuVuc = bd.KhuVuc
JOIN    PHONG_TRO  pt ON pt.MaNT   = nt.MaNT
WHERE   pt.TinhTrangPhong = 'Trống'
  AND   pt.GiaThue       <= bd.GiaToiDa
  AND   pt.DienTich      >= bd.DienTich
  AND   pt.SoNguoiOToiDa >= bd.SoNguoiO
ORDER BY bd.MaBDTP, pt.GiaThue;


-- ---------------------------------------------------------------------
-- TRUY VẤN 3: Hiển thị thông tin chi tiết của một nhà trọ, bao gồm địa chỉ,
--             số điện thoại, tiện ích và danh sách các phòng thuộc nhà trọ đó.
-- Tham số: {ma_nha_tro}
-- ---------------------------------------------------------------------

-- 3a. Thông tin chung: số điện thoại lấy từ chủ trọ (NGUOI_DUNG),
--     tiện ích gom thành một chuỗi. LEFT JOIN để nhà trọ chưa có tiện ích
--     nào vẫn hiển thị.
SELECT  nt.MaNT,
        nt.TenNhaTro,
        nt.DiaChi,
        nt.KhuVuc,
        nd.HoTen        AS ChuTro,
        nd.SoDienThoai,
        GROUP_CONCAT(ti.TenTienIch ORDER BY ti.MaTI SEPARATOR ', ') AS TienIch
FROM    NHA_TRO nt
JOIN    NGUOI_DUNG     nd  ON nd.MaND  = nt.MaND
LEFT JOIN NHATRO_TIENICH nti ON nti.MaNT = nt.MaNT
LEFT JOIN TIEN_ICH       ti  ON ti.MaTI  = nti.MaTI
WHERE   nt.MaNT = 1                          -- {ma_nha_tro}
GROUP BY nt.MaNT, nt.TenNhaTro, nt.DiaChi, nt.KhuVuc, nd.HoTen, nd.SoDienThoai;

-- 3b. Danh sách phòng thuộc nhà trọ đó (kèm số ảnh của mỗi phòng)
SELECT  pt.MaPT,
        pt.DienTich,
        pt.GiaThue,
        pt.TienDien,
        pt.TienNuoc,
        pt.NoiThat,
        pt.SoNguoiOToiDa,
        pt.TinhTrangPhong,
        COUNT(ha.MaHA)  AS SoHinhAnh
FROM    PHONG_TRO pt
LEFT JOIN HINH_ANH_PHONG ha ON ha.MaPT = pt.MaPT
WHERE   pt.MaNT = 1                          -- {ma_nha_tro}
GROUP BY pt.MaPT, pt.DienTich, pt.GiaThue, pt.TienDien, pt.TienNuoc,
         pt.NoiThat, pt.SoNguoiOToiDa, pt.TinhTrangPhong
ORDER BY pt.MaPT;
