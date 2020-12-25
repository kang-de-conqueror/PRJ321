<%-- 
    Document   : error
    Created on : Mar 22, 2020, 7:14:45 PM
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
        <h1>Error Page</h1>
        <h2>
            <font color="red">
            <s:property value="%{#request.ERROR}" />
            </font>
        </h2>
    </body>
</html>

