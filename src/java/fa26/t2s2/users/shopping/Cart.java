package fa26.t2s2.users.shopping;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<String, Product> cart;

    public Cart() {
        this.cart = new HashMap<>();
    }

    public Cart(Map<String, Product> cart) {
        this.cart = cart;
    }

    public Map<String, Product> getCart() {
        return cart;
    }

    public void setCart(Map<String, Product> cart) {
        this.cart = cart;
    }

    public boolean add(Product product) {
        boolean check = false;
        try {
            if (this.cart == null) {
                this.cart = new HashMap<>();
            }
            if (this.cart.containsKey(product.getId())) {
                int currentQuantity = this.cart.get(product.getId()).getQuantity();
                product.setQuantity(currentQuantity + product.getQuantity());
            }
            this.cart.put(product.getId(), product);
            check = true;
        } catch (Exception e) {

        }
        return check;
    }

    public boolean edit(String id, int quantity) {
        boolean check = false;
        try {
            if (this.cart != null) {
                if (this.cart.containsKey(id)) {
                    Product product = this.cart.get(id);
                    product.setQuantity(quantity);
                    this.cart.put(id, product);
                    check = true;
                }
            }
        } catch (Exception e) {

        }
        return check;
    }

    public boolean remove(String id) {
        boolean check = false;
        try {
            if (this.cart != null) {
                if (this.cart.containsKey(id)) {
                    this.cart.remove(id);
                }
            }
        } catch (Exception e) {

        }
        return check;
    }

    public boolean addV2(Product product) {
        boolean check = false;
        try {
            if (this.cart == null) {
                this.cart = new HashMap<>();
            }
            if (this.cart.containsKey(product.getId())) {
                int currentQuantity = this.cart.get(product.getId()).getQuantity();
                product.setQuantity(currentQuantity + product.getQuantity());
            }
            this.cart.put(product.getId(), product);
            check = true;
        } catch (Exception e) {

        }
        return check;
    }

    public double getTotal() {
        double total = 0;
        for (Product p : getCart().values()) {
            total += p.getPrice();
        }
        return total;
    }

}
