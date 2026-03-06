<%@page import="java.util.List"%>
<%@page import="fa26.t2s2.users.shopping.OrderDetailDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Oder detail page</title>
    </head>
    <body>
        <%
            List<OrderDetailDTO> listOD = (List) request.getAttribute("LIST_ORDER_DETAIL");
            if (listOD != null) {

        %>
        <h1>Oder #<% %></h1>


        <%            }
        %>
    </body>
</html>
