<%-- 
    Document   : insert
    Created on : Mar 5, 2020, 4:45:00 PM
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
        <h1>Insert Page</h1>
        <form action="MainController" method="POST">
            Username: <input type="text" name="txtUsername" value="${param.txtUsername}" />
            <font color="red">
            ${requestScope.INVALID.usernameError}
            </font>
            <br/>
            Password: <input type="password" name="txtPassword" />
            <font color="red">
            ${requestScope.INVALID.passwordError}
            </font>
            <br/>
            Confirm: <input type="password" name="txtConfirm" />
            <font color="red">
            ${requestScope.INVALID.confirmError}
            </font>
            <br/>
            Fullname: <input type="text" name="txtFullname" value="${param.txtFullname}" />
            <font color="red">
            ${requestScope.INVALID.fullnameError}
            </font>
            <br />
            Role: <input type="text" name="txtRole" value="${param.txtRole}" />
            <font color="red">
            ${requestScope.INVALID.roleError}
            </font>
            <br />
            <input type="submit" name="action" value="Insert" />
        </form>
    </body>
</html>
