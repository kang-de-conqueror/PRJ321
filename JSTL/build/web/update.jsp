<%-- 
    Document   : update
    Created on : Mar 5, 2020, 4:15:06 PM
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
        <h1>Update Page</h1>
        <form action="MainController" method="POST">
            Username: <input type="text" name="txtUsername" readonly="true" value="${requestScope.DTO.username}" /> <br/>
            Fullname: <input type="text" name="txtFullname" value="${requestScope.DTO.fullname}" /> <br />
            <font color="red">
            ${requestScope.INVALID.fullnameError}
            </font>
            <br />
            Role: <input type="text" name="txtRole" value="${requestScope.DTO.role}" />
            <font color="red">
            ${requestScope.INVALID.roleError}
            </font>
            <br />
            <input type="hidden" name="txtSearch" value="${param.txtSearch}" />
            <input type="submit" name="action" value="Update" />
        </form>
    </body>
</html>
