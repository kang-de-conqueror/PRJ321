<%-- 
    Document   : index
    Created on : Mar 3, 2020, 4:40:01 PM
    Author     : Peter
--%>

<%@page import="khangtl.dtos.RegistrationErrorObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <form action="MainController" method="Post">
            Username: <input type="text" name="txtUsername"/>
            <%
                RegistrationErrorObj errorObj = (RegistrationErrorObj) request.getAttribute("INVALID");
                if (errorObj != null) {
                    if (errorObj.getUsernameError() != null) {
            %>
            <font color="red">
            <%= errorObj.getUsernameError()%>
            </font>
            <%
                    }
                }
            %>
            <br/>
            Password: <input type="password" name="txtPassword"/>
            <font color="red">
            ${requestScope.INVALID.passwordError}
            </font>
            <br/>
            <input type="submit" name="action" value="Login"/>
        </form>
        <a href="insert.jsp">Register Account</a>
    </body>
</html>
