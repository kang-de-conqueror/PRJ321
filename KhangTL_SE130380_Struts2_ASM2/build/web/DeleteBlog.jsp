<%-- 
    Document   : DeleteBlog
    Created on : Mar 22, 2020, 10:21:04 PM
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
    <h1>
        <font color="blue">Delete BlogEntry</font>
    </h1>
    <h3>Input BlogId and click "Delete" button to delete the BlogEntry</h3>
    <s:form action="DeleteAction" method="POST">
        <s:textfield type="number" name="id" label="BlogId" />
        <s:submit value="Delete" />
    </s:form>
</body>
</html>
