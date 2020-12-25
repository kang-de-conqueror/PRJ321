<%-- 
    Document   : index
    Created on : Mar 12, 2020, 3:14:46 PM
    Author     : Peter
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <s:form action="LoginAction" method="POST">
            <s:textfield name="username" label="Username" />
            <s:password name="password" label="Password" />
            <s:submit value="Login" />
        </s:form>
        <a href="insert.jsp">Insert Account</a>
        <br />
        <a href="insertAnnotation.jsp">Insert Account with Annotation</a>
    </body>
</html>
