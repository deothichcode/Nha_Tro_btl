-- =====================================================================
--  HỆ THỐNG TÌM KIẾM NHÀ TRỌ VÀ GHÉP BẠN CÙNG PHÒNG
--  File: queries_tv2.sql  —  5 truy vấn phức tạp Q4–Q8 (Thành viên 2)
--  Chạy sau schema.sql và data.sql.
--  Giá trị có comment {ten_tham_so} là tham số: trong Java thay bằng dấu ?.
-- =====================================================================
USE nha_tro_db;

-- ---------------------------------------------------------------------
-- TRUY VẤN 4: Liệt kê các bài đăng tìm phòng theo khu vực, mức giá và
--             diện tích để người dùng (VD chủ trọ) dễ dàng tìm kiếm.
-- Cách hiểu: lọc bài theo khu vực, mức giá tối đa người tìm chấp nhận nằm
--            trong khoảng [gia_min, gia_max], diện tích mong muốn >= dien_tich.
--            Kèm tên, số điện thoại người đăng để liên hệ. Bài mới nhất trước.
-- Tham số: {khu_vuc}, {gia_min}, {gia_max}, {dien_tich}
-- ---------------------------------------------------------------------
SELECT  tp.MaBDTP,
        tp.MaND,
        nd.HoTen,
        nd.SoDienThoai,
        tp.KhuVuc,
        tp.GiaToiDa,
        tp.DienTich,
        tp.SoNguoiO,
        tp.NoiDung,
        tp.NgayDang
FROM    BAI_DANG_TIM_PHONG tp
JOIN    NGUOI_DUNG nd ON nd.MaND = tp.MaND
WHERE   tp.KhuVuc = 'Hà Đông'                              -- {khu_vuc}
  AND   tp.GiaToiDa BETWEEN 2500000 AND 4000000            -- {gia_min}, {gia_max}
  AND   tp.DienTich >= 20                                  -- {dien_tich}
ORDER BY tp.NgayDang DESC;


-- ---------------------------------------------------------------------
-- TRUY VẤN 5: Liệt kê các bài đăng tìm bạn cùng phòng theo khu vực và mức
--             tiền thuê để người dùng tìm người có nhu cầu tương tự.
-- Chỉ lấy bài còn 'Đang tìm'. Tuổi tính từ ngày sinh tại thời điểm truy vấn.
-- Tham số: {khu_vuc}, {gia_min}, {gia_max}
-- ---------------------------------------------------------------------
SELECT  tb.MaBDTB,
        tb.MaND,
        nd.HoTen,
        nd.GioiTinh,
        TIMESTAMPDIFF(YEAR, nd.NgaySinh, CURDATE()) AS Tuoi,
        nd.SoDienThoai,
        tb.KhuVuc,
        tb.GiaThue,
        tb.GioiTinhMongMuon,
        tb.NgayBatDau,
        tb.ThoiHan,
        tb.NoiDungYeuCau,
        tb.NgayDang
FROM    BAI_DANG_TIM_BAN tb
JOIN    NGUOI_DUNG nd ON nd.MaND = tb.MaND
WHERE   tb.TrangThai = 'Đang tìm'
  AND   tb.KhuVuc = 'Hà Đông'                              -- {khu_vuc}
  AND   tb.GiaThue BETWEEN 1000000 AND 2000000             -- {gia_min}, {gia_max}
ORDER BY tb.NgayDang DESC;


-- ---------------------------------------------------------------------
-- TRUY VẤN 6: Tìm những người dùng phù hợp để ghép bạn cùng phòng dựa trên
--             khu vực, mức tiền thuê và giới tính mong muốn.
-- Điều kiện ghép hai bài a và b:
--   * cùng khu vực, của hai người khác nhau, cả hai bài đang tìm;
--   * mức tiền thuê chênh nhau không quá {chenh_lech} (VNĐ);
--   * giới tính HỢP THEO CẢ HAI CHIỀU: người đăng b đúng giới tính a mong muốn
--     và người đăng a đúng giới tính b mong muốn ('Không yêu cầu' hợp với mọi người).
-- Self-join bảng BAI_DANG_TIM_BAN; JOIN NGUOI_DUNG hai lần để lấy giới tính thật.
-- a.MaBDTB < b.MaBDTB để mỗi cặp chỉ xuất hiện một lần.
-- ---------------------------------------------------------------------
SELECT  a.KhuVuc,
        a.MaBDTB  AS MaBDTB1, na.HoTen AS HoTen1, na.GioiTinh AS GioiTinh1, a.GiaThue AS GiaThue1,
        b.MaBDTB  AS MaBDTB2, nb.HoTen AS HoTen2, nb.GioiTinh AS GioiTinh2, b.GiaThue AS GiaThue2,
        ABS(a.GiaThue - b.GiaThue) AS ChenhLechGia
FROM    BAI_DANG_TIM_BAN a
JOIN    BAI_DANG_TIM_BAN b ON b.KhuVuc = a.KhuVuc AND a.MaBDTB < b.MaBDTB
JOIN    NGUOI_DUNG na ON na.MaND = a.MaND
JOIN    NGUOI_DUNG nb ON nb.MaND = b.MaND
WHERE   a.MaND <> b.MaND
  AND   a.TrangThai = 'Đang tìm'
  AND   b.TrangThai = 'Đang tìm'
  AND   ABS(a.GiaThue - b.GiaThue) <= 500000               -- {chenh_lech}
  AND   a.GioiTinhMongMuon IN (nb.GioiTinh, 'Không yêu cầu')
  AND   b.GioiTinhMongMuon IN (na.GioiTinh, 'Không yêu cầu')
ORDER BY a.KhuVuc, ChenhLechGia;

-- TRUY VẤN 6 (biến thể): những bài ghép được với MỘT bài cụ thể.
SELECT  a.KhuVuc,
        a.MaBDTB  AS MaBDTB1, na.HoTen AS HoTen1, na.GioiTinh AS GioiTinh1, a.GiaThue AS GiaThue1,
        b.MaBDTB  AS MaBDTB2, nb.HoTen AS HoTen2, nb.GioiTinh AS GioiTinh2, b.GiaThue AS GiaThue2,
        ABS(a.GiaThue - b.GiaThue) AS ChenhLechGia
FROM    BAI_DANG_TIM_BAN a
JOIN    BAI_DANG_TIM_BAN b ON b.KhuVuc = a.KhuVuc AND b.MaBDTB <> a.MaBDTB
JOIN    NGUOI_DUNG na ON na.MaND = a.MaND
JOIN    NGUOI_DUNG nb ON nb.MaND = b.MaND
WHERE   a.MaBDTB = 1                                       -- {ma_bai_dang}
  AND   a.MaND <> b.MaND
  AND   b.TrangThai = 'Đang tìm'
  AND   ABS(a.GiaThue - b.GiaThue) <= 500000               -- {chenh_lech}
  AND   a.GioiTinhMongMuon IN (nb.GioiTinh, 'Không yêu cầu')
  AND   b.GioiTinhMongMuon IN (na.GioiTinh, 'Không yêu cầu')
ORDER BY ChenhLechGia;


-- ---------------------------------------------------------------------
-- TRUY VẤN 7: Hiển thị thông tin cá nhân và các bài đăng của một người dùng.
-- Tham số: {ma_nguoi_dung}
-- ---------------------------------------------------------------------

-- 7a. Thông tin cá nhân
SELECT  MaND, HoTen, NgaySinh, QueQuan, GioiTinh, SoDienThoai, Email
FROM    NGUOI_DUNG
WHERE   MaND = 3;                                          -- {ma_nguoi_dung}

-- 7b. Toàn bộ bài đăng: gộp 3 loại bài về chung các cột bằng UNION ALL.
--     Bài cho thuê lấy khu vực và giá qua phòng -> nhà trọ.
--     Bài tìm phòng không có cột trạng thái nên để NULL.
SELECT  'Cho thuê' AS LoaiBaiDang, ct.MaBDCT AS MaBaiDang, nt.KhuVuc,
        pt.GiaThue AS MucGia, ct.NoiDung, ct.NgayDang, ct.TrangThai
FROM    BAI_DANG_CHO_THUE ct
JOIN    PHONG_TRO pt ON pt.MaPT = ct.MaPT
JOIN    NHA_TRO   nt ON nt.MaNT = pt.MaNT
WHERE   ct.MaND = 3                                        -- {ma_nguoi_dung}
UNION ALL
SELECT  'Tìm phòng', tp.MaBDTP, tp.KhuVuc,
        tp.GiaToiDa, tp.NoiDung, tp.NgayDang, NULL
FROM    BAI_DANG_TIM_PHONG tp
WHERE   tp.MaND = 3                                        -- {ma_nguoi_dung}
UNION ALL
SELECT  'Tìm bạn', tb.MaBDTB, tb.KhuVuc,
        tb.GiaThue, tb.NoiDungYeuCau, tb.NgayDang, tb.TrangThai
FROM    BAI_DANG_TIM_BAN tb
WHERE   tb.MaND = 3                                        -- {ma_nguoi_dung}
ORDER BY NgayDang DESC;


-- ---------------------------------------------------------------------
-- TRUY VẤN 8: Thống kê số lượng phòng trống và phòng đã được thuê của
--             từng nhà trọ.
-- LEFT JOIN để nhà trọ chưa có phòng nào vẫn hiển thị với số 0.
-- ---------------------------------------------------------------------
SELECT  nt.MaNT,
        nt.TenNhaTro,
        nt.KhuVuc,
        SUM(CASE WHEN pt.TinhTrangPhong = 'Trống'   THEN 1 ELSE 0 END) AS SoPhongTrong,
        SUM(CASE WHEN pt.TinhTrangPhong = 'Đã thuê' THEN 1 ELSE 0 END) AS SoPhongDaThue,
        COUNT(pt.MaPT) AS TongSoPhong
FROM    NHA_TRO nt
LEFT JOIN PHONG_TRO pt ON pt.MaNT = nt.MaNT
GROUP BY nt.MaNT, nt.TenNhaTro, nt.KhuVuc
ORDER BY nt.MaNT;
