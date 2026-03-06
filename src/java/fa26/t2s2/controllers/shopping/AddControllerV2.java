package fa26.t2s2.controllers.shopping;

import fa26.t2s2.users.shopping.Cart;
import fa26.t2s2.users.shopping.Product;
import fa26.t2s2.users.shopping.ProductDAO;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AddControllerV2", urlPatterns = {"/AddControllerV2"})
public class AddControllerV2 extends HttpServlet {

    private static final String ERROR = "ShopController";
    private static final String SUCCESS = "ShopController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int pid = Integer.parseInt(request.getParameter("pid"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            ProductDAO listp = new ProductDAO();
            listp.getListProductFormSQL();

            Product product = listp.getProduct(pid, quantity);
            // kiem tra khi nguoi dung bam add - kiem tra so luong 

            // ----------------------------------------------------
            if (product != null) {

                boolean isValid = listp.checkQuantity(product);
                if (isValid) {
                    String name = product.getName();
                    HttpSession ss = request.getSession();
                    Cart cart = (Cart) ss.getAttribute("CART");
                    if (cart == null) {
                        cart = new Cart();
                    }

                    boolean check = cart.add(product);
                    if (check) {
                        ss.setAttribute("CART", cart);
                        request.setAttribute("MSG", "Added " + quantity + " items " + name);
                        url = SUCCESS;
                    }
                } else {
                    request.setAttribute("ERROR_MSG", "Not enough items available.");
                }
            }

        } catch (Exception e) {
            log("Error at AddController: " + e.toString());
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
