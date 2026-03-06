package fa26.t2s2.controllers.shopping;

import fa26.t2s2.users.UserDTO;
import fa26.t2s2.users.shopping.Cart;
import fa26.t2s2.users.shopping.OrderDAO;
import fa26.t2s2.users.shopping.OrderDTO;
import fa26.t2s2.users.shopping.OrderDetailDAO;
import fa26.t2s2.users.shopping.OrderDetailDTO;
import fa26.t2s2.users.shopping.Product;
import fa26.t2s2.users.shopping.ProductDAO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CheckoutController", urlPatterns = {"/CheckoutController"})
public class CheckoutController extends HttpServlet {

    private static final String ERROR = "viewCart.jsp";
    private static final String SUCCESS = "user.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            // kiem tra session
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart == null) {
                request.setAttribute("ERROR_MSG", "You dont have items to checkout !");
            } else {
                ProductDAO p_dao = new ProductDAO();
                // khoi tao danh sach san pham het hang!
                List<Product> listEmptyProduct = new ArrayList<>();
                for (Product p : cart.getCart().values()) {
                    if (!p_dao.checkQuantity(p)) {
                        listEmptyProduct.add(p);
                    }
                }
                if (listEmptyProduct.isEmpty()) {
                    // Them vao order
                    UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
                    OrderDAO order_dao = new OrderDAO();
                    OrderDTO order = new OrderDTO(LocalDate.now(), cart.getTotal(), user.getUserID());
                    //-----------------
                    // neu them vao order loi lap tuc huy order
                    boolean addOrder = order_dao.add(order);
                    if (addOrder) {

                        // them vao order detail
                        OrderDetailDAO orderDetail_dao = new OrderDetailDAO();
                        boolean isAdd = orderDetail_dao.add(order.getNewOrderID(), cart);
                        // --------------------------------
                        // checkout
                        if (isAdd) {
                            for (Product p : cart.getCart().values()) {
                                p_dao.updateQuantity(p);

                            }
                            cart = null;
                            session.setAttribute("CART", cart);
                            url = SUCCESS;
                            request.setAttribute("MSG", "Checkout successfully! Check your oder !");
                        }
                        // -------------------
                    }
                } else {
                    for (Product p : cart.getCart().values()) {
                        for (Product p_empty : listEmptyProduct) {
                            if (p.getId().equals(p_empty.getId())) {
                                cart.edit(p.getId(), p_dao.getQuantity(p.getId()));
                                p_empty.setQuantity(p_dao.getQuantity(p.getId()));
                            }
                        }
                    }
                    request.setAttribute("LIST_EMPTY_PRODUCT", listEmptyProduct);
                }
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
