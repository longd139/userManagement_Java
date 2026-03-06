package fa26.t2s2.users.shopping;

import java.sql.SQLException;
import java.time.LocalDate;

public class OrderDTO {

    private int OrderID;
    private LocalDate date;
    private double total;
    private String uid;

    public OrderDTO(LocalDate date, double total, String uid) {
        this.date = date;
        this.total = total;
        this.uid = uid;
    }

    public OrderDTO(int OrderID, LocalDate date, double total, String uid) {
        this.OrderID = OrderID;
        this.date = date;
        this.total = total;
        this.uid = uid;
    }

    public OrderDTO() {
        this.date = LocalDate.now();
        this.total = 0;
        this.uid = "";
    }

    public int getOrderID() {
        return OrderID;
    }

    public int getNewOrderID() throws SQLException {
        OrderDAO od_dao = new OrderDAO();
        int oid = od_dao.getNewOid();
        return oid;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

}
