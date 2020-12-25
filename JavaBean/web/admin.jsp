<%-- 
    Document   : admin
    Created on : Feb 27, 2020, 4:46:22 PM
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
        <h1>Hello Admin!</h1>
        -----------------------
        <jsp:useBean id="KhangTLBean" class="khangtl.models.RegistrationBean" scope="session"/>
        <h1>Welcome 4, ${sessionScope.KhangTLBean.username}</h1>
    </body>
</html>
