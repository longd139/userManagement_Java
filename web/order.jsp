<%-- 
    Document   : oder
    Created on : Mar 5, 2026, 10:27:15 PM
    Author     : ADMIN
--%>

<%@page import="fa26.t2s2.users.UserDTO"%>
<%@page import="fa26.t2s2.users.shopping.OrderDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Page</title>
    </head>
    <body>
        <h1>Your orders.</h1>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if(loginUser == null || !"US".equals(loginUser.getRoleID())){
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <%
            List<OrderDTO> listOrder = (List) request.getAttribute("LIST_ORDER");
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>OderID</th>
                    <th>Date</th>
                    <th>Total</th>
                    <th>Detail</th>
                </tr>
            </thead>
            <tbody>
            <%
                if(listOrder != null){
                    for (OrderDTO od : listOrder) {                           
                        
            %>
                <tr>
                    <td><%= od.getOrderID()%></td>
                    <td><%= od.getDate()%></td>
                    <td><%= od.getTotal()%></td>
                    <td>detail</td>
                </tr>
            <%
                    }
                }
            %>
            </tbody>
        </table>

    </body>
</html>
