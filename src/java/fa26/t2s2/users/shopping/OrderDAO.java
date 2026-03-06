package fa26.t2s2.users.shopping;

import fa26.t2s2.users.UserDTO;
import fa26.t2s2.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    private static final String ADD = "INSERT INTO Orders (\"date\",total,uid) VALUES (?,?,?) ";
    private static final String GET_ORDER = "SELECT * FROM Orders WHERE uid = ?";

    public boolean add(OrderDTO order) throws SQLException {
        boolean isAdded = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD);
                ptm.setString(1, order.getDate().toString());
                ptm.setDouble(2, order.getTotal());
                ptm.setString(3, order.getUid());
                isAdded = (ptm.executeUpdate() > 0);
            }
        } catch (Exception e) {
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return isAdded;
    }

    public List<OrderDTO> getOrders(UserDTO user) throws SQLException {
        List<OrderDTO> listOrders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ORDER);
                ptm.setString(1, user.getUserID());
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int oid = rs.getInt("oid");
                    LocalDate date = rs.getObject("date", LocalDate.class);
                    double total = rs.getDouble("total");

                    listOrders.add(new OrderDTO(oid, date, total, user.getUserID()));
                }
            }
        } catch (Exception e) {
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listOrders;
    }

}
