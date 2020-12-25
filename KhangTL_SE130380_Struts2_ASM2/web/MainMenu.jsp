<%-- 
    Document   : index
    Created on : Mar 22, 2020, 10:13:03 PM
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
        <h1>Main Menu</h1>
        <font color="green">
        <s:property value="%{#request.SUCCESS}" />
        </font>
        <br />
        <br />
        <s:a href="InsertBlog.jsp"><b>Add New BlogEntry</b></s:a>
            <br />
            <br />
        <s:a href="DeleteBlog.jsp"><b>Delete BlogEntry</b></s:a>
    </body>
</html>
