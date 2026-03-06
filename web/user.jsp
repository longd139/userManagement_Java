<%-- 
    Document   : user
    Created on : Jan 22, 2026, 10:42:09 AM
    Author     : ADMIN
--%>

<%@page import="fa26.t2s2.users.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if(loginUser == null || !"US".equals(loginUser.getRoleID())){
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        
        <!--<a href="shopp.jsp">Shopping</a>-->
        <a href="MainController?action=Shop">Shopping</a> <br>
        <a href="MainController?action=Order">Your order</a> <br>

        UserID: <%=loginUser.getUserID()%> </br>
        Fullname: <%=loginUser.getFullName()%>  </br>
        RoleID: <%=loginUser.getRoleID()%> </br>
        Password: <%=loginUser.getPassword()%> </br>
        <form action="MainController" method="post">
            
            <input type="hidden" name="action" value="Logout" />
            <input type="submit" value="Logout"/>
            
        </form>
        <%
            String msg = (String) request.getAttribute("MSG");
            if(msg == null) msg = "";
            
        %>
        <h3><%=msg%></h3>
        
        
    </body>
    
    
</html>
