    <%-- 
    Document   : admin
    Created on : Jan 22, 2026, 10:46:08 AM
    Author     : ADMIN
--%>

<%@page import="java.util.List"%>
<%@page import="fa26.t2s2.users.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>admin Page</title>
    </head>
    <body>
        <%
            String keyword = (String) request.getParameter("search");
            if (keyword == null) keyword = "";
        %>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            
            if (loginUser == null || !"AD".equals(loginUser.getRoleID())) {
                response.sendRedirect("login.jsp");
                return;
            } 
        %>
        
        <h1>Welcome: <%= loginUser.getFullName()%></h1>
        <form action="MainController">
            FullName: <input type="text" name="search" value="<%= keyword%>" required="">
            <input type="submit" name="action" value="Search">
        </form>

        <%
            List<UserDTO> listUser = (List<UserDTO>) request.getAttribute("LIST_USER");
            if (listUser != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>UserID</th>
                    <th>FullName</th>
                    <th>RoleID</th>
                    <th>Password</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (UserDTO user : listUser) {
                %>
                <form action="MainController">
                   
                    <tr>
                        <td><%= count++ %></td>
                        <td><input type="text" name="userID" value="<%=user.getUserID()%>" readonly=""></td>
                        <td><input type="text" name="fullname" value="<%=user.getFullName()%>" required=""></td>
                        <td><input type="text" name="roleID" value="<%=user.getRoleID()%>"  required=""></td>
                        <td><%= user.getPassword() %></td>
                        <td>
                            <input type="submit" name="action" value="Update">
                            <input type="hidden" name="search" value="<%=keyword%>">
                        </td>
                        <td>
                            <a href="MainController?userID=<%= user.getUserID()%>&action=Delete&search=<%= keyword%>">Delete</a>
                        </td>
                    </tr>
                </form>
                
                <%}%>
            </tbody>
        </table>
            <%
                String errorMSG = (String) request.getAttribute("ERROR_MSG");
                if (errorMSG == null) {
                    errorMSG = "";
                }
            %>
            <%=errorMSG%>
        <%
        } else {
            String emptyMsg = (String) request.getAttribute("EMPTY_MSG");
            if (emptyMsg == null) {
                emptyMsg = "";
            }

        %>
        <%=emptyMsg%>
        <%
            }
            
        %>

        <a href="MainController?action=Logout">Logout</a>
        <a href="createUser.jsp">Create User</a>


    </body>
</html>
