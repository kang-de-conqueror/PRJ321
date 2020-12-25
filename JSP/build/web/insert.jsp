<%-- 
    Document   : insert
    Created on : Feb 20, 2020, 5:09:36 PM
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
        <h1>Create New Account</h1>
        <form action="MainController" method="POST">
            Username: <input type="text" name="txtUsername"/><br/>
            Password: <input type="password" name="txtPassword"/><br/>
            Fullname: <input type="text" name="txtFullname"/><br/>
            Role: <input type="text" name="txtRole"/><br/>
            <input type="submit" name="action" value="Insert"/>
        </form>
    </body>
</html>
