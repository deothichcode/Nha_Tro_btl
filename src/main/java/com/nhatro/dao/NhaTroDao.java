package com.nhatro.dao;

import com.nhatro.dto.HostelDetail;
import com.nhatro.dto.HostelRoomStats;
import com.nhatro.dto.RoomSearchResult;
import com.nhatro.dto.RoomSeekingPostView;
import com.nhatro.dto.RoommateMatch;
import com.nhatro.dto.RoommatePostView;
import com.nhatro.dto.UserProfile;

import java.math.BigDecimal;
import java.util.List;

/**
 * Các thao tác truy vấn CSDL của hệ thống — 8 truy vấn theo yêu cầu đề bài
 * và một thao tác cập nhật dùng để demo xóa cache.
 *
 * Quy ước tham số lọc: truyền null nghĩa là KHÔNG lọc theo tiêu chí đó.
 * Kết quả mong đợi ghi trong từng hàm tính trên dữ liệu mẫu database/data.sql.
 */
public interface NhaTroDao {

    /**
     * Q1. Phòng còn trống theo khu vực, sắp xếp giá thuê tăng dần.
     * Ví dụ: "Hà Đông" -> phòng 1 (2.500.000), phòng 2 (3.200.000).
     */
    List<RoomSearchResult> findAvailableRoomsByArea(String khuVuc);

    /**
     * Q2. Tìm phòng trống phù hợp nhu cầu, sắp xếp giá thuê tăng dần.
     *
     * @param khuVuc           khu vực, VD "Hà Đông"
     * @param giaToiDa         giá thuê tối đa (VNĐ/tháng)
     * @param dienTichToiThieu diện tích tối thiểu (m2)
     * @param soNguoi          số người ở (phòng phải chứa được ít nhất từng ấy người)
     * Ví dụ: ("Hà Đông", 3000000, 20, 2) -> chỉ phòng 1.
     */
    List<RoomSearchResult> searchRooms(String khuVuc, Long giaToiDa, BigDecimal dienTichToiThieu, Integer soNguoi);

    /**
     * Q3. Chi tiết một nhà trọ: địa chỉ, số điện thoại chủ trọ, tiện ích và danh sách phòng.
     *
     * @return null nếu không tồn tại nhà trọ có mã này.
     * Ví dụ: 1 -> "Nhà trọ Xanh Hà Đông", chủ trọ Nguyễn Văn An (0912345678), 4 tiện ích, 3 phòng.
     */
    HostelDetail getHostelDetail(int maNT);

    /**
     * Q4. Bài đăng tìm phòng theo khu vực, khoảng giá tối đa và diện tích, mới nhất trước.
     *
     * @param giaMin           cận dưới của GiaToiDa
     * @param giaMax           cận trên của GiaToiDa
     * @param dienTichToiThieu diện tích mong muốn tối thiểu
     * Ví dụ: ("Hà Đông", null, null, null) -> bài 4, bài 1.
     */
    List<RoomSeekingPostView> findRoomSeekingPosts(String khuVuc, Long giaMin, Long giaMax, BigDecimal dienTichToiThieu);

    /**
     * Q5. Bài đăng tìm bạn cùng phòng (đang tìm) theo khu vực và khoảng tiền thuê, mới nhất trước.
     * Ví dụ: ("Hà Đông", null, null) -> bài 9, 6, 4, 1.
     */
    List<RoommatePostView> findRoommatePosts(String khuVuc, Long giaMin, Long giaMax);

    /**
     * Q6. Tất cả các cặp ghép bạn cùng phòng: cùng khu vực, chênh giá không quá ngưỡng,
     * giới tính hợp theo cả hai chiều, cả hai bài đang tìm. Mỗi cặp xuất hiện một lần.
     * Ví dụ: (500000) -> đúng 2 cặp: bài 1 <-> bài 6, bài 2 <-> bài 7.
     */
    List<RoommateMatch> findRoommateMatches(long chenhLechGiaToiDa);

    /**
     * Q6 (biến thể). Những bài đăng ghép được với một bài cụ thể.
     * Ví dụ: (1, 500000) -> bài 6; (4, 500000) -> rỗng.
     */
    List<RoommateMatch> findMatchesForPost(int maBDTB, long chenhLechGiaToiDa);

    /**
     * Q7. Thông tin cá nhân và toàn bộ bài đăng (cho thuê, tìm phòng, tìm bạn) của một người.
     *
     * @return null nếu không tồn tại người dùng có mã này.
     * Ví dụ: 3 -> Lê Hoàng Cường, bài tìm phòng 1 và bài tìm bạn 1.
     */
    UserProfile getUserWithPosts(int maND);

    /**
     * Q8. Thống kê số phòng trống và đã thuê của từng nhà trọ.
     * Ví dụ: nhà 1–4 mỗi nhà 2 trống / 1 đã thuê; nhà 5: 3 trống / 0 đã thuê.
     */
    List<HostelRoomStats> getRoomStatsByHostel();

    /**
     * Cập nhật tình trạng phòng ('Trống' hoặc 'Đã thuê').
     * Thành viên 3 gọi hàm này để demo xóa cache khi dữ liệu thay đổi.
     *
     * @return số dòng được cập nhật (0 nếu không có phòng này).
     */
    int updateRoomStatus(int maPT, String tinhTrangMoi);
}
