<%-- 
    Document   : insert
    Created on : Mar 17, 2020, 4:08:28 PM
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
        <h1>Insert Account</h1>
        <s:if test="%{exception.message.indexOf('duplicate') > - 1}">
            <font color="red">
            <s:property value="username" /> is existed!
            </font>
        </s:if>
        <s:form action="InsertAction" method="POST">
            <s:textfield name="username" label="Username" />
            <s:password name="password" label="Password" />
            <s:password name="confirm" label="Confirm" />
            <s:textfield name="emailID" label="Email" />
            <s:textfield name="phoneNumber" label="Phone Number (ex: 090 977 8046)" />
            <s:submit value="Insert" />
        </s:form>
    </body>
</html>
