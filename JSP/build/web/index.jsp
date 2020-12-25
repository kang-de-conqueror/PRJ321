<%-- 
    Document   : index
    Created on : Feb 18, 2020, 5:08:11 PM
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
        <h1>Login Page</h1>
        <form action='LoginController' method='POST'>
            Username: <input type='text' name='txtUsername' /></br>
            Password: <input type='password' name='txtPassword' /></br>
            <input type='submit' value='Submit' />
        </form>
        <a href="insert.jsp">Create Account</a>
    </body>
</html>
