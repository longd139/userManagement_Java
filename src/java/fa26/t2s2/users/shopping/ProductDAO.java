package fa26.t2s2.users.shopping;

import fa26.t2s2.users.UserDTO;
import fa26.t2s2.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private static final String GET_PRODUCT = "select * from Product";
    private static final String FIND_PRODUCT_BY_ID = "select * from Product WHERE pid = ?";
    private static final String CHECK_QUANTITY = "SELECT quantity FROM Product WHERE pid = ?";
    private static final String UPDATE_QUANTITY = "UPDATE Product SET quantity = quantity - ? WHERE pid = ? AND quantity - ? >= 0";

    public List<Product> getListProductFormSQL() throws SQLException {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_PRODUCT);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String pid = rs.getString("pid");
                    String name = rs.getString("name");
                    double price = Double.parseDouble(rs.getString("price"));
                    int quantity = Integer.parseInt(rs.getString("quantity"));
                    list.add(new Product(pid, name, price, quantity));
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public Product getProduct(int pid, int quantity) throws SQLException {
        Product product = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(FIND_PRODUCT_BY_ID);
                ptm.setInt(1, pid);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String id = String.valueOf(pid);
                    String name = rs.getString("name");
                    double price = Double.parseDouble(rs.getString("price"));

                    product = new Product(id, name, price, quantity);
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return product;
    }

    public boolean updateQuantity(Product product) throws SQLException {
        boolean isValid = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_QUANTITY);
                ptm.setInt(1, product.getQuantity());

                ptm.setInt(2, Integer.parseInt(product.getId()));
                ptm.setInt(3, product.getQuantity());

                isValid = (ptm.executeUpdate() > 0);

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
        return isValid;
    }

    public boolean checkQuantity(Product product) throws SQLException {
        boolean isValid = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_QUANTITY);
                ptm.setInt(1, Integer.parseInt(product.getId()));
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int quantity = rs.getInt("quantity");
                    if (quantity >= product.getQuantity()) {
                        isValid = true;
                    }
                }

            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return isValid;
    }

    public int getQuantity(String id) throws SQLException {
        int quantity = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_QUANTITY);
                ptm.setInt(1, Integer.parseInt(id));
                rs = ptm.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
                }

            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return quantity;
    }

}
