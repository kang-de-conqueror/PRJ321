<%-- 
    Document   : Update
    Created on : Mar 22, 2020, 8:08:59 PM
    Author     : Peter
--%>

<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <s:head />
    </head>
    <body>
        <h1>Update Page</h1>
        <s:form action="ConfirmUpdateAction" method="POST">
            <table border="1">
                <s:textfield name="id" label="EmpID" value="%{id}" readonly="true" />
                <s:password name="password" label="EmpPassword" />
                <s:textfield name="name" label="EmpName" value="%{dto.name}" />
                <s:textfield name="address" label="Address" value="%{dto.address}" />
                <s:submit value="Update" />
            </table>
        </s:form>
    </body>
</html>
