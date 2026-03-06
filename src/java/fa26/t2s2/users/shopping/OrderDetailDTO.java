package fa26.t2s2.users.shopping;

public class OrderDetailDTO {

    private int odid;
    private int oid;
    private int pid;
    private double price;
    private int quantity;

    public OrderDetailDTO(int odid, int oid, int pid, double price, int quantity) {
        this.odid = odid;
        this.oid = oid;
        this.pid = pid;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderDetailDTO(int oid, int pid, double price, int quantity) {
        this.oid = oid;
        this.pid = pid;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderDetailDTO() {

    }

    public int getOdid() {
        return odid;
    }

    public void setOdid(int odid) {
        this.odid = odid;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
