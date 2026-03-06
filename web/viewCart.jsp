<%-- 
    Document   : viewCart
    Created on : Mar 2, 2026, 10:36:44 AM
    Author     : ADMIN
--%>

<%@page import="java.util.List"%>
<%@page import="fa26.t2s2.users.UserDTO"%>
<%@page import="fa26.t2s2.users.shopping.Cart"%>
<%@page import="fa26.t2s2.users.shopping.Product"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart Page</title>
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if(loginUser == null || !"US".equals(loginUser.getRoleID())){
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <h1>Your selected items !</h1>
        <%
            String msg = (String) request.getAttribute("MSG");
            if(msg == null){
                msg = "";
            }
        %>
        <%= msg%>
        <%      
            Cart cart = (Cart) session.getAttribute("CART");
            if(cart != null && cart.getCart().size() > 0){
        %>      
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total</th>
                    <th>Edit</th>
                    <th>Remove</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    double total = 0;
                    for (Product p : cart.getCart().values()) {    
                    total += p.getPrice() * p.getQuantity();
                    
                %>
            <form action="MainController" method="POST">
                
                <tr>
                    <td><%= count++%></td>
                    <td><input type="text" name="id" value="<%= p.getId()%>" readonly></td>
                    <td><%= p.getName()%></td>
                    <td><%= p.getPrice()%></td>
                    <td>
                        <input type="number" name="quantity" value="<%= p.getQuantity()%>" required="" min="1">
                    </td>
                    <td><b><%= p.getPrice() * p.getQuantity()%></b> $</td>
                    <td><input type="submit" name="action" value="Edit"></td>
                    <td><input type="submit" name="action" value="Remove"></td>
                    
                </tr>
            </form>
                <%
                    }
                %>
            </tbody>
        </table>
                Total: <b style="color: red"><%= String.format("%.2f", total)%></b> $<br>
                <form action="MainController" method="POST">                  
                    <input type="submit" name="action" value="Checkout">
                </form>
                
        <%
            List<Product> listEmptyProduct = (List) request.getAttribute("LIST_EMPTY_PRODUCT");
            if(listEmptyProduct!= null){
                for (Product p : listEmptyProduct) {  
                
        %>
        <p><mark><%= p.getName()%></mark> only <b style="color: red"><%= p.getQuantity()%></b> left in stock.</p>
        <% 
            }
            }
            } else {
        %>
            <%= "Your cart is empty !"%>
        <%
            }
        %>
        <br>
        <a href="MainController?action=Shop">Mua them di !!!</a>
        
    </body>
</html>
