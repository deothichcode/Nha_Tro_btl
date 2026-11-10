package com.nhatro.dao;

import com.nhatro.dto.HostelDetail;
import com.nhatro.dto.HostelRoomItem;
import com.nhatro.dto.HostelRoomStats;
import com.nhatro.dto.RoomSearchResult;
import com.nhatro.dto.RoomSeekingPostView;
import com.nhatro.dto.RoommateMatch;
import com.nhatro.dto.RoommatePostView;
import com.nhatro.dto.UserPostItem;
import com.nhatro.dto.UserProfile;
import com.nhatro.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Cài đặt NhaTroDao bằng JdbcTemplate.
 *
 * - Mọi tham số đều truyền qua dấu ? (PreparedStatement) để chống SQL Injection,
 *   không bao giờ cộng chuỗi giá trị người dùng nhập vào câu SQL.
 * - Tiêu chí lọc tùy chọn dùng mẫu (? IS NULL OR cot <= ?): truyền null thì
 *   điều kiện luôn đúng, tức là không lọc theo tiêu chí đó. Vì thế tham số
 *   lọc được truyền hai lần.
 */
@Repository
public class NhaTroDaoImpl implements NhaTroDao {

    private final JdbcTemplate jdbc;

    public NhaTroDaoImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    // =================================================================
    //  CÂU LỆNH SQL
    // =================================================================

    /** Phần chung của Q1 và Q2: phòng trống kèm thông tin nhà trọ. */
    private static final String SQL_PHONG_TRONG = """
            SELECT  pt.MaPT, nt.MaNT, nt.TenNhaTro, nt.DiaChi, nt.KhuVuc,
                    pt.DienTich, pt.GiaThue, pt.SoNguoiOToiDa, pt.NoiThat
            FROM    PHONG_TRO pt
            JOIN    NHA_TRO   nt ON nt.MaNT = pt.MaNT
            WHERE   pt.TinhTrangPhong = 'Trống'
            """;

    static final String SQL_Q1 = SQL_PHONG_TRONG + """
              AND   nt.KhuVuc = ?
            ORDER BY pt.GiaThue ASC
            """;

    static final String SQL_Q2 = SQL_PHONG_TRONG + """
              AND   (? IS NULL OR nt.KhuVuc        =  ?)
              AND   (? IS NULL OR pt.GiaThue       <= ?)
              AND   (? IS NULL OR pt.DienTich      >= ?)
              AND   (? IS NULL OR pt.SoNguoiOToiDa >= ?)
            ORDER BY pt.GiaThue ASC
            """;

    static final String SQL_Q3_NHA_TRO = """
            SELECT  nt.MaNT, nt.TenNhaTro, nt.DiaChi, nt.KhuVuc,
                    nd.HoTen AS ChuTro, nd.SoDienThoai,
                    GROUP_CONCAT(ti.TenTienIch ORDER BY ti.MaTI SEPARATOR ', ') AS TienIch
            FROM    NHA_TRO nt
            JOIN    NGUOI_DUNG nd ON nd.MaND = nt.MaND
            LEFT JOIN NHATRO_TIENICH nti ON nti.MaNT = nt.MaNT
            LEFT JOIN TIEN_ICH       ti  ON ti.MaTI  = nti.MaTI
            WHERE   nt.MaNT = ?
            GROUP BY nt.MaNT, nt.TenNhaTro, nt.DiaChi, nt.KhuVuc, nd.HoTen, nd.SoDienThoai
            """;

    static final String SQL_Q3_PHONG = """
            SELECT  pt.MaPT, pt.DienTich, pt.GiaThue, pt.TienDien, pt.TienNuoc,
                    pt.NoiThat, pt.SoNguoiOToiDa, pt.TinhTrangPhong,
                    COUNT(ha.MaHA) AS SoHinhAnh
            FROM    PHONG_TRO pt
            LEFT JOIN HINH_ANH_PHONG ha ON ha.MaPT = pt.MaPT
            WHERE   pt.MaNT = ?
            GROUP BY pt.MaPT, pt.DienTich, pt.GiaThue, pt.TienDien, pt.TienNuoc,
                     pt.NoiThat, pt.SoNguoiOToiDa, pt.TinhTrangPhong
            ORDER BY pt.MaPT
            """;

    static final String SQL_Q4 = """
            SELECT  tp.MaBDTP, tp.MaND, nd.HoTen, nd.SoDienThoai, tp.KhuVuc,
                    tp.GiaToiDa, tp.DienTich, tp.SoNguoiO, tp.NoiDung, tp.NgayDang
            FROM    BAI_DANG_TIM_PHONG tp
            JOIN    NGUOI_DUNG nd ON nd.MaND = tp.MaND
            WHERE   (? IS NULL OR tp.KhuVuc   =  ?)
              AND   (? IS NULL OR tp.GiaToiDa >= ?)
              AND   (? IS NULL OR tp.GiaToiDa <= ?)
              AND   (? IS NULL OR tp.DienTich >= ?)
            ORDER BY tp.NgayDang DESC
            """;

    static final String SQL_Q5 = """
            SELECT  tb.MaBDTB, tb.MaND, nd.HoTen, nd.GioiTinh,
                    TIMESTAMPDIFF(YEAR, nd.NgaySinh, CURDATE()) AS Tuoi,
                    nd.SoDienThoai, tb.KhuVuc, tb.GiaThue, tb.GioiTinhMongMuon,
                    tb.NgayBatDau, tb.ThoiHan, tb.NoiDungYeuCau, tb.NgayDang
            FROM    BAI_DANG_TIM_BAN tb
            JOIN    NGUOI_DUNG nd ON nd.MaND = tb.MaND
            WHERE   tb.TrangThai = 'Đang tìm'
              AND   (? IS NULL OR tb.KhuVuc  =  ?)
              AND   (? IS NULL OR tb.GiaThue >= ?)
              AND   (? IS NULL OR tb.GiaThue <= ?)
            ORDER BY tb.NgayDang DESC
            """;

    /** Phần SELECT/FROM chung của Q6 và biến thể. */
    private static final String SQL_GHEP_CAP = """
            SELECT  a.KhuVuc,
                    a.MaBDTB AS MaBDTB1, na.MaND AS MaND1, na.HoTen AS HoTen1,
                    na.GioiTinh AS GioiTinh1, a.GiaThue AS GiaThue1,
                    b.MaBDTB AS MaBDTB2, nb.MaND AS MaND2, nb.HoTen AS HoTen2,
                    nb.GioiTinh AS GioiTinh2, b.GiaThue AS GiaThue2,
                    ABS(a.GiaThue - b.GiaThue) AS ChenhLechGia
            FROM    BAI_DANG_TIM_BAN a
            JOIN    NGUOI_DUNG na ON na.MaND = a.MaND
            JOIN    BAI_DANG_TIM_BAN b ON b.KhuVuc = a.KhuVuc
            JOIN    NGUOI_DUNG nb ON nb.MaND = b.MaND
            WHERE   a.MaND <> b.MaND
              AND   a.TrangThai = 'Đang tìm'
              AND   b.TrangThai = 'Đang tìm'
              AND   ABS(a.GiaThue - b.GiaThue) <= ?
              AND   a.GioiTinhMongMuon IN (nb.GioiTinh, 'Không yêu cầu')
              AND   b.GioiTinhMongMuon IN (na.GioiTinh, 'Không yêu cầu')
            """;

    /** a.MaBDTB < b.MaBDTB: mỗi cặp chỉ xuất hiện một lần. */
    static final String SQL_Q6 = SQL_GHEP_CAP + """
              AND   a.MaBDTB < b.MaBDTB
            ORDER BY a.KhuVuc, ChenhLechGia
            """;

    static final String SQL_Q6_THEO_BAI = SQL_GHEP_CAP + """
              AND   a.MaBDTB = ?
            ORDER BY ChenhLechGia
            """;

    static final String SQL_Q7_NGUOI_DUNG = """
            SELECT  MaND, HoTen, NgaySinh, QueQuan, GioiTinh, SoDienThoai, Email
            FROM    NGUOI_DUNG
            WHERE   MaND = ?
            """;

    static final String SQL_Q7_BAI_DANG = """
            SELECT  'Cho thuê' AS LoaiBaiDang, ct.MaBDCT AS MaBaiDang, nt.KhuVuc,
                    pt.GiaThue AS MucGia, ct.NoiDung, ct.NgayDang, ct.TrangThai
            FROM    BAI_DANG_CHO_THUE ct
            JOIN    PHONG_TRO pt ON pt.MaPT = ct.MaPT
            JOIN    NHA_TRO   nt ON nt.MaNT = pt.MaNT
            WHERE   ct.MaND = ?
            UNION ALL
            SELECT  'Tìm phòng', tp.MaBDTP, tp.KhuVuc,
                    tp.GiaToiDa, tp.NoiDung, tp.NgayDang, NULL
            FROM    BAI_DANG_TIM_PHONG tp
            WHERE   tp.MaND = ?
            UNION ALL
            SELECT  'Tìm bạn', tb.MaBDTB, tb.KhuVuc,
                    tb.GiaThue, tb.NoiDungYeuCau, tb.NgayDang, tb.TrangThai
            FROM    BAI_DANG_TIM_BAN tb
            WHERE   tb.MaND = ?
            ORDER BY NgayDang DESC
            """;

    static final String SQL_Q8 = """
            SELECT  nt.MaNT, nt.TenNhaTro, nt.KhuVuc,
                    SUM(CASE WHEN pt.TinhTrangPhong = 'Trống'   THEN 1 ELSE 0 END) AS SoPhongTrong,
                    SUM(CASE WHEN pt.TinhTrangPhong = 'Đã thuê' THEN 1 ELSE 0 END) AS SoPhongDaThue,
                    COUNT(pt.MaPT) AS TongSoPhong
            FROM    NHA_TRO nt
            LEFT JOIN PHONG_TRO pt ON pt.MaNT = nt.MaNT
            GROUP BY nt.MaNT, nt.TenNhaTro, nt.KhuVuc
            ORDER BY nt.MaNT
            """;

    static final String SQL_CAP_NHAT_TINH_TRANG = """
            UPDATE PHONG_TRO SET TinhTrangPhong = ? WHERE MaPT = ?
            """;

    // =================================================================
    //  ÁNH XẠ MỘT DÒNG KẾT QUẢ -> ĐỐI TƯỢNG JAVA (RowMapper)
    // =================================================================

    private static final RowMapper<RoomSearchResult> ROOM_SEARCH_MAPPER = (rs, i) -> new RoomSearchResult(
            rs.getInt("MaPT"),
            rs.getInt("MaNT"),
            rs.getString("TenNhaTro"),
            rs.getString("DiaChi"),
            rs.getString("KhuVuc"),
            rs.getBigDecimal("DienTich"),
            rs.getLong("GiaThue"),
            rs.getInt("SoNguoiOToiDa"),
            rs.getString("NoiThat"));

    private static final RowMapper<HostelDetail> HOSTEL_DETAIL_MAPPER = (rs, i) -> {
        HostelDetail d = new HostelDetail();
        d.setMaNT(rs.getInt("MaNT"));
        d.setTenNhaTro(rs.getString("TenNhaTro"));
        d.setDiaChi(rs.getString("DiaChi"));
        d.setKhuVuc(rs.getString("KhuVuc"));
        d.setChuTro(rs.getString("ChuTro"));
        d.setSoDienThoai(rs.getString("SoDienThoai"));
        d.setTienIch(rs.getString("TienIch"));
        return d;
    };

    private static final RowMapper<HostelRoomItem> HOSTEL_ROOM_MAPPER = (rs, i) -> new HostelRoomItem(
            rs.getInt("MaPT"),
            rs.getBigDecimal("DienTich"),
            rs.getLong("GiaThue"),
            rs.getLong("TienDien"),
            rs.getLong("TienNuoc"),
            rs.getString("NoiThat"),
            rs.getInt("SoNguoiOToiDa"),
            rs.getString("TinhTrangPhong"),
            rs.getInt("SoHinhAnh"));

    private static final RowMapper<RoomSeekingPostView> ROOM_SEEKING_MAPPER = (rs, i) -> new RoomSeekingPostView(
            rs.getInt("MaBDTP"),
            rs.getInt("MaND"),
            rs.getString("HoTen"),
            rs.getString("SoDienThoai"),
            rs.getString("KhuVuc"),
            rs.getLong("GiaToiDa"),
            rs.getBigDecimal("DienTich"),
            rs.getInt("SoNguoiO"),
            rs.getString("NoiDung"),
            rs.getObject("NgayDang", LocalDateTime.class));

    private static final RowMapper<RoommatePostView> ROOMMATE_POST_MAPPER = (rs, i) -> new RoommatePostView(
            rs.getInt("MaBDTB"),
            rs.getInt("MaND"),
            rs.getString("HoTen"),
            rs.getString("GioiTinh"),
            rs.getInt("Tuoi"),
            rs.getString("SoDienThoai"),
            rs.getString("KhuVuc"),
            rs.getLong("GiaThue"),
            rs.getString("GioiTinhMongMuon"),
            rs.getObject("NgayBatDau", LocalDate.class),
            rs.getInt("ThoiHan"),
            rs.getString("NoiDungYeuCau"),
            rs.getObject("NgayDang", LocalDateTime.class));

    private static final RowMapper<RoommateMatch> ROOMMATE_MATCH_MAPPER = (rs, i) -> new RoommateMatch(
            rs.getString("KhuVuc"),
            rs.getInt("MaBDTB1"),
            rs.getInt("MaND1"),
            rs.getString("HoTen1"),
            rs.getString("GioiTinh1"),
            rs.getLong("GiaThue1"),
            rs.getInt("MaBDTB2"),
            rs.getInt("MaND2"),
            rs.getString("HoTen2"),
            rs.getString("GioiTinh2"),
            rs.getLong("GiaThue2"),
            rs.getLong("ChenhLechGia"));

    private static final RowMapper<User> USER_MAPPER = (rs, i) -> new User(
            rs.getInt("MaND"),
            rs.getString("HoTen"),
            rs.getObject("NgaySinh", LocalDate.class),
            rs.getString("QueQuan"),
            rs.getString("GioiTinh"),
            rs.getString("SoDienThoai"),
            rs.getString("Email"));

    private static final RowMapper<UserPostItem> USER_POST_MAPPER = (rs, i) -> new UserPostItem(
            rs.getString("LoaiBaiDang"),
            rs.getInt("MaBaiDang"),
            rs.getString("KhuVuc"),
            rs.getLong("MucGia"),
            rs.getString("NoiDung"),
            rs.getObject("NgayDang", LocalDateTime.class),
            rs.getString("TrangThai"));

    private static final RowMapper<HostelRoomStats> HOSTEL_STATS_MAPPER = (rs, i) -> new HostelRoomStats(
            rs.getInt("MaNT"),
            rs.getString("TenNhaTro"),
            rs.getString("KhuVuc"),
            rs.getInt("SoPhongTrong"),
            rs.getInt("SoPhongDaThue"),
            rs.getInt("TongSoPhong"));

    // =================================================================
    //  CÀI ĐẶT 8 TRUY VẤN
    // =================================================================

    @Override
    public List<RoomSearchResult> findAvailableRoomsByArea(String khuVuc) {
        return jdbc.query(SQL_Q1, ROOM_SEARCH_MAPPER, khuVuc);
    }

    @Override
    public List<RoomSearchResult> searchRooms(String khuVuc, Long giaToiDa,
                                              BigDecimal dienTichToiThieu, Integer soNguoi) {
        return jdbc.query(SQL_Q2, ROOM_SEARCH_MAPPER,
                khuVuc, khuVuc,
                giaToiDa, giaToiDa,
                dienTichToiThieu, dienTichToiThieu,
                soNguoi, soNguoi);
    }

    @Override
    public HostelDetail getHostelDetail(int maNT) {
        List<HostelDetail> ketQua = jdbc.query(SQL_Q3_NHA_TRO, HOSTEL_DETAIL_MAPPER, maNT);
        if (ketQua.isEmpty()) {
            return null;
        }
        HostelDetail chiTiet = ketQua.get(0);
        chiTiet.setDanhSachPhong(jdbc.query(SQL_Q3_PHONG, HOSTEL_ROOM_MAPPER, maNT));
        return chiTiet;
    }

    @Override
    public List<RoomSeekingPostView> findRoomSeekingPosts(String khuVuc, Long giaMin, Long giaMax,
                                                          BigDecimal dienTichToiThieu) {
        return jdbc.query(SQL_Q4, ROOM_SEEKING_MAPPER,
                khuVuc, khuVuc,
                giaMin, giaMin,
                giaMax, giaMax,
                dienTichToiThieu, dienTichToiThieu);
    }

    @Override
    public List<RoommatePostView> findRoommatePosts(String khuVuc, Long giaMin, Long giaMax) {
        return jdbc.query(SQL_Q5, ROOMMATE_POST_MAPPER,
                khuVuc, khuVuc,
                giaMin, giaMin,
                giaMax, giaMax);
    }

    @Override
    public List<RoommateMatch> findRoommateMatches(long chenhLechGiaToiDa) {
        return jdbc.query(SQL_Q6, ROOMMATE_MATCH_MAPPER, chenhLechGiaToiDa);
    }

    @Override
    public List<RoommateMatch> findMatchesForPost(int maBDTB, long chenhLechGiaToiDa) {
        return jdbc.query(SQL_Q6_THEO_BAI, ROOMMATE_MATCH_MAPPER, chenhLechGiaToiDa, maBDTB);
    }

    @Override
    public UserProfile getUserWithPosts(int maND) {
        List<User> nguoiDung = jdbc.query(SQL_Q7_NGUOI_DUNG, USER_MAPPER, maND);
        if (nguoiDung.isEmpty()) {
            return null;
        }
        List<UserPostItem> baiDang = jdbc.query(SQL_Q7_BAI_DANG, USER_POST_MAPPER, maND, maND, maND);
        return new UserProfile(nguoiDung.get(0), baiDang);
    }

    @Override
    public List<HostelRoomStats> getRoomStatsByHostel() {
        return jdbc.query(SQL_Q8, HOSTEL_STATS_MAPPER);
    }

    @Override
    public int updateRoomStatus(int maPT, String tinhTrangMoi) {
        if (!"Trống".equals(tinhTrangMoi) && !"Đã thuê".equals(tinhTrangMoi)) {
            throw new IllegalArgumentException("Tình trạng phòng chỉ nhận 'Trống' hoặc 'Đã thuê'");
        }
        return jdbc.update(SQL_CAP_NHAT_TINH_TRANG, tinhTrangMoi, maPT);
    }
}
