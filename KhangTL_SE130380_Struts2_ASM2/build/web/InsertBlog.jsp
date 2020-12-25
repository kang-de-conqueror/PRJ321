<%-- 
    Document   : InsertBlog
    Created on : Mar 22, 2020, 10:20:53 PM
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
        <h1>
            <font color="blue">Add New BlogEntry</font>
        </h1>
        <s:form action="InsertAction" method="POST" name="form">
            <table border="1">
                <s:textfield name="id" label="BlogID" />
                <s:textfield name="title" label="Title" />
                <s:textfield name="body" label="Body" />
                <s:textfield name="datePublish" label="Date Publish" />
                <s:submit value="Insert" name="buttonName" align="left" />
                <s:submit value="Reset" name="buttonName" align="left"/>
            </table>
        </s:form>
    </body>
</html>
