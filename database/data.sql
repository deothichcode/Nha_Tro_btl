-- =====================================================================
--  HỆ THỐNG TÌM KIẾM NHÀ TRỌ VÀ GHÉP BẠN CÙNG PHÒNG
--  File: data.sql  —  Dữ liệu mẫu (DML)
--  Chạy SAU schema.sql. Muốn chạy lại từ đầu: chạy lại schema.sql trước
--  (schema.sql xóa và tạo lại CSDL), rồi mới chạy file này.
-- =====================================================================
USE nha_tro_db;

-- 1. NGUOI_DUNG (15 người: 5 chủ trọ là 1, 2, 6, 10, 15; còn lại tìm phòng/tìm bạn)
INSERT INTO NGUOI_DUNG (MaND, HoTen, NgaySinh, QueQuan, GioiTinh, SoDienThoai, Email) VALUES
(1,  'Nguyễn Văn An',    '1985-05-12', 'Hà Nội',     'Nam', '0912345678', 'an.nguyen@gmail.com'),
(2,  'Trần Thị Bình',    '1988-08-20', 'Nam Định',   'Nữ',  '0923456789', 'binh.tran@gmail.com'),
(3,  'Lê Hoàng Cường',   '2002-11-03', 'Thanh Hóa',  'Nam', '0934567890', 'cuong.le@gmail.com'),
(4,  'Phạm Minh Đức',    '2003-01-15', 'Nghệ An',    'Nam', '0945678901', 'duc.pham@gmail.com'),
(5,  'Đỗ Thùy Dung',     '2002-04-22', 'Hải Phòng',  'Nữ',  '0956789012', 'dung.do@gmail.com'),
(6,  'Vũ Anh Tuấn',      '1990-09-30', 'Thái Bình',  'Nam', '0967890123', 'tuan.vu@gmail.com'),
(7,  'Hoàng Lan Anh',    '2003-07-19', 'Phú Thọ',    'Nữ',  '0978901234', 'anh.hoang@gmail.com'),
(8,  'Đặng Quốc Khánh',  '2001-12-05', 'Hà Tĩnh',    'Nam', '0989012345', 'khanh.dang@gmail.com'),
(9,  'Ngô Bảo Ngọc',     '2002-02-28', 'Hà Nội',     'Nữ',  '0990123456', 'ngoc.ngo@gmail.com'),
(10, 'Bùi Văn Hùng',     '1982-06-14', 'Bắc Ninh',   'Nam', '0901234567', 'hung.bui@gmail.com'),
(11, 'Trịnh Thị Mai',    '2003-10-10', 'Hải Dương',  'Nữ',  '0911223344', 'mai.trinh@gmail.com'),
(12, 'Lý Văn Nam',       '2002-03-25', 'Hòa Bình',   'Nam', '0922334455', 'nam.ly@gmail.com'),
(13, 'Dương Khánh Linh', '2003-08-14', 'Quảng Ninh', 'Nữ',  '0933445566', 'linh.duong@gmail.com'),
(14, 'Phan Hữu Phước',   '2001-05-09', 'Đà Nẵng',    'Nam', '0944556677', 'phuoc.phan@gmail.com'),
(15, 'Cao Thị Hoa',      '1987-11-18', 'Hà Nội',     'Nữ',  '0955667788', 'hoa.cao@gmail.com');

-- 2. NHA_TRO (5 nhà trọ, mỗi khu vực một nhà)
INSERT INTO NHA_TRO (MaNT, MaND, TenNhaTro, DiaChi, KhuVuc) VALUES
(1, 1,  'Nhà trọ Xanh Hà Đông',         'Số 12 Ngõ 4 Trần Phú, Hà Nội',  'Hà Đông'),
(2, 2,  'Chung cư mini Cầu Giấy',       'Số 45 Ngõ 68 Cầu Giấy, Hà Nội', 'Cầu Giấy'),
(3, 6,  'Nhà trọ Sinh Viên Thanh Xuân', 'Số 89 Nguyễn Trãi, Hà Nội',     'Thanh Xuân'),
(4, 10, 'Trọ An Ninh Nam Từ Liêm',      'Số 15 Phố Nhổn, Hà Nội',        'Nam Từ Liêm'),
(5, 15, 'Nhà trọ Minh Trí Đống Đa',     'Số 88 Chùa Bộc, Hà Nội',        'Đống Đa');

-- 3. PHONG_TRO (mỗi nhà 3 phòng; nhà 5 cố ý để toàn phòng trống để test thống kê = 0 phòng đã thuê)
--    TienDien: VNĐ/kWh ; TienNuoc: VNĐ/m3
INSERT INTO PHONG_TRO (MaPT, MaNT, DienTich, GiaThue, TienDien, TienNuoc, NoiThat, SoNguoiOToiDa, TinhTrangPhong) VALUES
(1,  1, 20.0, 2500000, 3500, 30000, 'Điều hòa, Nóng lạnh, Giường',  2, 'Trống'),
(2,  1, 25.0, 3200000, 3500, 30000, 'Full nội thất',                3, 'Trống'),
(3,  1, 18.0, 2200000, 3500, 30000, 'Nóng lạnh, Quạt trần',         2, 'Đã thuê'),
(4,  2, 22.0, 3500000, 3800, 30000, 'Điều hòa, Kệ bếp, Tủ đồ',      2, 'Trống'),
(5,  2, 22.0, 3600000, 3800, 30000, 'Điều hòa, Ban công',           2, 'Đã thuê'),
(6,  2, 28.0, 4200000, 3800, 30000, 'Full nội thất, Thang máy',     3, 'Trống'),
(7,  3, 15.0, 1800000, 3000, 25000, 'Nóng lạnh, Quạt treo tường',   2, 'Trống'),
(8,  3, 18.0, 2000000, 3000, 25000, 'Nóng lạnh, Giường đôi',        2, 'Đã thuê'),
(9,  3, 20.0, 2300000, 3000, 25000, 'Điều hòa, Nóng lạnh',          2, 'Trống'),
(10, 4, 25.0, 2800000, 3500, 25000, 'Điều hòa, Tủ lạnh, Kệ bếp',    3, 'Trống'),
(11, 4, 25.0, 2800000, 3500, 25000, 'Điều hòa, Tủ lạnh, Kệ bếp',    3, 'Đã thuê'),
(12, 4, 32.0, 3800000, 3500, 25000, 'Chung cư mini full đồ',        4, 'Trống'),
(13, 5, 20.0, 2700000, 3500, 30000, 'Điều hòa, Giường tủ',          2, 'Trống'),
(14, 5, 22.0, 3000000, 3500, 30000, 'Điều hòa, Ban công riêng',     2, 'Trống'),
(15, 5, 26.0, 3400000, 3500, 30000, 'Full nội thất',                3, 'Trống');

-- 4. HINH_ANH_PHONG (mỗi phòng ít nhất 1 ảnh)
INSERT INTO HINH_ANH_PHONG (MaHA, MaPT, TenHinhAnh, DuongDan) VALUES
(1,  1,  'Phòng 101 Hà Đông - Tổng quan',   'https://example.com/images/room1_1.jpg'),
(2,  1,  'Phòng 101 Hà Đông - Nhà vệ sinh', 'https://example.com/images/room1_2.jpg'),
(3,  2,  'Phòng 102 Hà Đông - Ban công',    'https://example.com/images/room2_1.jpg'),
(4,  2,  'Phòng 102 Hà Đông - Bếp',         'https://example.com/images/room2_2.jpg'),
(5,  3,  'Phòng 103 Hà Đông - Tổng quan',   'https://example.com/images/room3_1.jpg'),
(6,  4,  'Phòng 201 Cầu Giấy - Tổng quan',  'https://example.com/images/room4_1.jpg'),
(7,  5,  'Phòng 202 Cầu Giấy - Ban công',   'https://example.com/images/room5_1.jpg'),
(8,  6,  'Phòng 203 Cầu Giấy - Tổng quan',  'https://example.com/images/room6_1.jpg'),
(9,  6,  'Phòng 203 Cầu Giấy - Phòng ngủ',  'https://example.com/images/room6_2.jpg'),
(10, 7,  'Phòng 101 Thanh Xuân',            'https://example.com/images/room7_1.jpg'),
(11, 8,  'Phòng 102 Thanh Xuân',            'https://example.com/images/room8_1.jpg'),
(12, 9,  'Phòng 103 Thanh Xuân',            'https://example.com/images/room9_1.jpg'),
(13, 10, 'Phòng 101 Nam Từ Liêm - Bếp',     'https://example.com/images/room10_1.jpg'),
(14, 11, 'Phòng 102 Nam Từ Liêm',           'https://example.com/images/room11_1.jpg'),
(15, 12, 'Phòng 201 Nam Từ Liêm - Phòng khách', 'https://example.com/images/room12_1.jpg'),
(16, 12, 'Phòng 201 Nam Từ Liêm - Phòng ngủ',   'https://example.com/images/room12_2.jpg'),
(17, 13, 'Phòng 101 Đống Đa',               'https://example.com/images/room13_1.jpg'),
(18, 14, 'Phòng 102 Đống Đa - Ban công',    'https://example.com/images/room14_1.jpg'),
(19, 15, 'Phòng 103 Đống Đa - Tổng quan',   'https://example.com/images/room15_1.jpg');

-- 5. TIEN_ICH (8 tiện ích)
INSERT INTO TIEN_ICH (MaTI, TenTienIch, MoTa) VALUES
(1, 'Điều hòa',       'Trang bị điều hòa 2 chiều'),
(2, 'Nóng lạnh',      'Bình nước nóng phòng tắm'),
(3, 'Wifi miễn phí',  'Internet tốc độ cao phủ toàn nhà'),
(4, 'Chỗ để xe',      'Tầng hầm để xe rộng rãi, an toàn'),
(5, 'Thang máy',      'Thang máy di chuyển nhanh chóng'),
(6, 'Máy giặt chung', 'Khu vực máy giặt sân phơi chung'),
(7, 'Camera an ninh', 'Hệ thống camera hành lang 24/7'),
(8, 'Khóa vân tay',   'Cửa ra vào bảo mật bằng vân tay');

-- 6. NHATRO_TIENICH (gắn tiện ích cho nhà trọ)
INSERT INTO NHATRO_TIENICH (MaNT, MaTI) VALUES
(1, 1), (1, 2), (1, 3), (1, 7),
(2, 1), (2, 2), (2, 5), (2, 8),
(3, 3), (3, 4), (3, 6),
(4, 1), (4, 4), (4, 7),
(5, 1), (5, 2), (5, 6);

-- 7. BAI_DANG_CHO_THUE (người đăng = chủ của nhà trọ chứa phòng, thỏa trigger)
INSERT INTO BAI_DANG_CHO_THUE (MaBDCT, MaND, MaPT, NoiDung, NgayDang, TrangThai) VALUES
(1, 1,  1,  'Cho thuê phòng trọ khép kín sạch sẽ tại Hà Đông',  '2026-08-01 08:00:00', 'Đang hiển thị'),
(2, 1,  2,  'Phòng rộng thoáng mát ưu tiên hộ gia đình',        '2026-08-02 09:30:00', 'Đang hiển thị'),
(3, 2,  4,  'Chung cư mini cao cấp Cầu Giấy full nội thất',     '2026-08-03 10:15:00', 'Đang hiển thị'),
(4, 6,  7,  'Phòng trọ giá sinh viên gần Ngã Tư Sở',            '2026-08-05 14:00:00', 'Đang hiển thị'),
(5, 10, 10, 'Phòng trọ thoáng mát khu Nhổn, Nam Từ Liêm',       '2026-08-10 11:00:00', 'Đang hiển thị');

-- 8. BAI_DANG_TIM_PHONG
INSERT INTO BAI_DANG_TIM_PHONG (MaBDTP, MaND, KhuVuc, GiaToiDa, DienTich, SoNguoiO, NoiDung, NgayDang) VALUES
(1, 3, 'Hà Đông',     3000000, 20.0, 2, 'Cần tìm phòng trọ khu vực Hà Đông giá dưới 3 triệu',  '2026-08-02 10:00:00'),
(2, 4, 'Cầu Giấy',    4000000, 25.0, 2, 'Tìm phòng gần Đại học Quốc Gia, Cầu Giấy',             '2026-08-04 15:20:00'),
(3, 5, 'Thanh Xuân',  2200000, 18.0, 1, 'Sinh viên cần tìm phòng trọ khép kín Thanh Xuân',      '2026-08-06 09:00:00'),
(4, 7, 'Hà Đông',     3500000, 22.0, 2, 'Tìm phòng trọ rộng rãi khu Mỗ Lao Hà Đông',            '2026-08-08 16:45:00'),
(5, 8, 'Nam Từ Liêm', 2800000, 20.0, 2, 'Cần tìm phòng trọ khu vực Nhổn cho 2 người',           '2026-08-12 13:10:00');

-- 9. BAI_DANG_TIM_BAN  (ThoiHan = số tháng)
--    Kịch bản ghép (cùng khu vực, chênh giá <= 500.000, giới tính hợp theo CẢ HAI chiều):
--      1 <-> 6 : khớp (Hà Đông, 2 nam cùng muốn ở với nam)
--      2 <-> 7 : khớp (Cầu Giấy, 2 nữ cùng muốn ở với nữ)
--      4 -> 6  : chỉ khớp 1 chiều (bài 6 muốn nam, người đăng bài 4 là nữ) -> không ghép
--      3 / 8   : chỉ khớp 1 chiều (bài 3 muốn nữ, người đăng bài 8 là nam) -> không ghép
--      9       : cùng Hà Đông nhưng giá chênh quá 500.000 -> không ghép
--      5       : một mình ở Nam Từ Liêm -> không ghép
INSERT INTO BAI_DANG_TIM_BAN (MaBDTB, MaND, KhuVuc, GiaThue, GioiTinhMongMuon, NgayBatDau, ThoiHan, NoiDungYeuCau, NgayDang, TrangThai) VALUES
(1, 3,  'Hà Đông',     1500000, 'Nam',           '2026-09-01', 6,  'Sạch sẽ, không hút thuốc, thức khuya không ồn', '2026-08-03 08:30:00', 'Đang tìm'),
(2, 5,  'Cầu Giấy',    2000000, 'Nữ',            '2026-09-01', 12, 'Ưu tiên sinh viên, hòa đồng, gọn gàng',         '2026-08-05 12:00:00', 'Đang tìm'),
(3, 7,  'Thanh Xuân',  1200000, 'Nữ',            '2026-09-15', 12, 'Sống trung thực, tự giác vệ sinh chung',        '2026-08-07 14:15:00', 'Đang tìm'),
(4, 9,  'Hà Đông',     1800000, 'Không yêu cầu', '2026-09-01', 6,  'Ngoan ngoãn, không dẫn bạn về ở qua đêm',       '2026-08-09 10:50:00', 'Đang tìm'),
(5, 11, 'Nam Từ Liêm', 1400000, 'Nữ',            '2026-09-01', 12, 'Sinh viên PTIT hoặc Kiến Trúc',                 '2026-08-11 17:30:00', 'Đang tìm'),
(6, 12, 'Hà Đông',     1600000, 'Nam',           '2026-09-01', 6,  'Đi làm giờ hành chính, ngủ sớm, gọn gàng',      '2026-08-13 19:00:00', 'Đang tìm'),
(7, 13, 'Cầu Giấy',    2100000, 'Nữ',            '2026-09-15', 12, 'Nữ sinh viên, không nuôi thú cưng',             '2026-08-14 08:15:00', 'Đang tìm'),
(8, 14, 'Thanh Xuân',  1300000, 'Nữ',            '2026-10-01', 6,  'Muốn ở ghép tiết kiệm chi phí',                 '2026-08-15 21:40:00', 'Đang tìm'),
(9, 8,  'Hà Đông',     2500000, 'Nam',           '2026-10-01', 12, 'Cần phòng rộng, có chỗ để xe máy',              '2026-08-16 10:20:00', 'Đang tìm');
