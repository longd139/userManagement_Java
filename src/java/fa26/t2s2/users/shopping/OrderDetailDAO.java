package fa26.t2s2.users.shopping;

import fa26.t2s2.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrderDetailDAO {

    private static final String ADD = "INSERT INTO OrderDetails (orderID, productID, price, quantity) VALUES (?, ?, ?, ?)";

    public boolean add(int newOrderID, Cart cart) throws SQLException {
        boolean isAdded = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // 1. Tắt AutoCommit để quản lý Transaction thủ công
                conn.setAutoCommit(false);

                ptm = conn.prepareStatement(ADD);
                for (Product p : cart.getCart().values()) {
                    // Giả sử các getter của Product khớp với logic của bạn
                    ptm.setInt(1, newOrderID);
                    ptm.setInt(2, Integer.parseInt(p.getId()));
                    ptm.setDouble(3, p.getPrice());
                    ptm.setInt(4, p.getQuantity());
                    ptm.addBatch();
                }

                // 2. Thực thi Batch
                int[] results = ptm.executeBatch();

                // 3. Commit dữ liệu xuống DB
                conn.commit();

                // 4. Kiểm tra xem có dữ liệu nào được insert không
                isAdded = (results.length > 0);
            }
        } catch (Exception e) {
            // 5. Nếu có lỗi, Rollback lại toàn bộ để tránh dữ liệu rác
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace(); // Quan trọng để debug
            isAdded = false;
        } finally {
            // Đóng resource (nên dùng try-with-resources nếu có thể)
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return isAdded;
    }

}
