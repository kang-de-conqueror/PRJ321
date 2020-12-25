<%-- 
    Document   : LoginController
    Created on : Feb 27, 2020, 4:34:58 PM
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
        <jsp:useBean id="KhangTLBean" class="khangtl.models.RegistrationBean" scope="session" />
        <jsp:setProperty name="KhangTLBean" property="username" value="${param.username}" />
        
        <jsp:setProperty name="KhangTLBean" property="*" />
        <%
            String role = KhangTLBean.checkLogin();
            if (role.equals("admin")) {
                response.sendRedirect("admin.jsp");
            }
        %>
    </body>
</html>
