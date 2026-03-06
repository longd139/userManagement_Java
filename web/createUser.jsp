<%-- 
    Document   : createUser
    Created on : Feb 26, 2026, 9:56:33 AM
    Author     : ADMIN
--%>

<%@page import="fa26.t2s2.users.UserError"%>
<%@page import="fa26.t2s2.users.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create new user Page</title>
    </head>
    <body>
         <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            
            if (loginUser == null || !"AD".equals(loginUser.getRoleID())) {
                response.sendRedirect("login.jsp");
                return;
            } 

            UserError userError = (UserError) request.getAttribute("USER_ERROR");
            if(userError == null) userError = new UserError();
            
        %>
        Create New User:
        <form action="MainController">
            UserID: <input type="text" name="userID" required><%= userError.getUserIDError()%><br>
            FullName:  <input type="text" name="fullName" required><%= userError.getFullNameError()%><br>
            RoleID: 
            <select name="roleID">
                <option value="AD">AD</option>
                <option value="US">US</option>
            </select><br>
            Password: <input type="password" name="password" required><br>
            Confirm Password: <input type="password" name="c_password" required><%= userError.getConfirmError()%><br>
            <input type="submit" name="action" value="Create">
            <input type="reset" name="action" value="Reset">
            
        </form>
    </body>
</html>
