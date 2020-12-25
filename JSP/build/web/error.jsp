<%-- 
    Document   : error
    Created on : Feb 20, 2020, 2:43:05 PM
    Author     : Peter
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>ERROR PAGE</h1>
        <%
            String error = (String) request.getAttribute("ERROR");
        %>
        <h2>
            <font color="red">
            <%= error%>
            </font>
        </h2>
        <a href="index.jsp">Back to Login Page</a>
    </body>
</html>
