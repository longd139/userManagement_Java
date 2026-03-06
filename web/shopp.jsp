<%-- 
    Document   : shopp
    Created on : Mar 2, 2026, 9:55:44 AM
    Author     : ADMIN
--%>

<%@page import="java.util.List"%>
<%@page import="fa26.t2s2.users.shopping.Product_list"%>
<%@page import="fa26.t2s2.users.shopping.Product"%>
<%@page import="fa26.t2s2.users.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Long Store</title>
    </head>
    <body>
        <h1>Welcome to Store !</h1>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if(loginUser == null || !"US".equals(loginUser.getRoleID())){
                response.sendRedirect("login.jsp");
                return;
            }
            
            List<Product> listP = (List) request.getAttribute("LIST_PRODUCTS");
           
        %>
        
        <form action="MainController" method="POST">     
            <select name="pid">
                <%       
                    if(listP != null) {
                        for (Product p : listP) {
                %>
                <option value="<%= p.getId()%>"><%=  p.getName()%> - <%= p.getPrice() %> $ | Stock: <%= p.getQuantity()%></option>
                <%
                        }
                    }
                %>
            </select>
            <select name="quantity">
                <option value="1">1 Item</option>
                <option value="2">2 Items</option>
                <option value="3">3 Items</option>
                <option value="4">4 Items</option>
                <option value="5">5 Items</option>
                <option value="10">10 Items</option>
            </select>
          
            <input type="submit" name="action" value="Add">       
            <input type="submit" name="action" value="View">       
            
        </form>
        
        <%
            String msg = (String) request.getAttribute("MSG");
            if(msg == null) msg = "";
            
        %>
        <h3><%=msg%></h3>
        
         <%
            String error_msg = (String) request.getAttribute("ERROR_MSG");
            if(error_msg == null) error_msg = "";
            
        %>
        <h4 style="color: red"><%=error_msg%></h4>
        
    </body>
</html>
