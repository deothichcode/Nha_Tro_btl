package com.nhatro.dao;

import com.nhatro.dto.HostelDetail;
import com.nhatro.dto.HostelRoomStats;
import com.nhatro.dto.RoomSearchResult;
import com.nhatro.dto.RoomSeekingPostView;
import com.nhatro.dto.RoommateMatch;
import com.nhatro.dto.RoommatePostView;
import com.nhatro.dto.UserProfile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Cài đặt NhaTroDao bằng JdbcTemplate.
 *
 * LẦN 2: mới là bản khung. Mỗi hàm ném UnsupportedOperationException để
 * Thành viên 3 đã có thể tiêm (inject) NhaTroDao và dựng menu, cache song song.
 * Các lần đẩy sau sẽ lần lượt thay phần thân bằng câu SQL thật.
 */
@Repository
public class NhaTroDaoImpl implements NhaTroDao {

    private final JdbcTemplate jdbc;

    public NhaTroDaoImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private static UnsupportedOperationException chuaCaiDat(String truyVan) {
        return new UnsupportedOperationException(truyVan + " chưa được cài đặt");
    }

    @Override
    public List<RoomSearchResult> findAvailableRoomsByArea(String khuVuc) {
        throw chuaCaiDat("Q1");
    }

    @Override
    public List<RoomSearchResult> searchRooms(String khuVuc, Long giaToiDa, BigDecimal dienTichToiThieu, Integer soNguoi) {
        throw chuaCaiDat("Q2");
    }

    @Override
    public HostelDetail getHostelDetail(int maNT) {
        throw chuaCaiDat("Q3");
    }

    @Override
    public List<RoomSeekingPostView> findRoomSeekingPosts(String khuVuc, Long giaMin, Long giaMax, BigDecimal dienTichToiThieu) {
        throw chuaCaiDat("Q4");
    }

    @Override
    public List<RoommatePostView> findRoommatePosts(String khuVuc, Long giaMin, Long giaMax) {
        throw chuaCaiDat("Q5");
    }

    @Override
    public List<RoommateMatch> findRoommateMatches(long chenhLechGiaToiDa) {
        throw chuaCaiDat("Q6");
    }

    @Override
    public List<RoommateMatch> findMatchesForPost(int maBDTB, long chenhLechGiaToiDa) {
        throw chuaCaiDat("Q6 (biến thể)");
    }

    @Override
    public UserProfile getUserWithPosts(int maND) {
        throw chuaCaiDat("Q7");
    }

    @Override
    public List<HostelRoomStats> getRoomStatsByHostel() {
        throw chuaCaiDat("Q8");
    }

    @Override
    public int updateRoomStatus(int maPT, String tinhTrangMoi) {
        throw chuaCaiDat("Cập nhật tình trạng phòng");
    }
}
