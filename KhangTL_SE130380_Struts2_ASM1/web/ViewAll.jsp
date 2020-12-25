<%-- 
    Document   : ViewAll
    Created on : Mar 22, 2020, 7:16:36 PM
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
        <h1>All Employees</h1>
        <font color="green">
        <s:property value="%{#request.SUCCESS}" />
        </font>
        <s:if test="%{listEmployee != null}">
            <table border="1">
                <thead>
                    <tr>
                        <th>EmployeeID</th>
                        <th>EmployeeName</th>
                        <th>Address</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="listEmployee">
                        <tr>
                            <td><s:property value="id" /></td>
                            <td><s:property value="name" /></td>
                            <td><s:property value="address" /></td>
                            <td>
                                <s:url action="UpdateAction" id="UpdateLink">
                                    <s:param name="id" value="%{id}" />
                                </s:url>
                                <s:a href="%{UpdateLink}">Update</s:a>
                                </td>
                            </tr>
                    </s:iterator>
                </tbody>
            </table>
        </s:if>
    </body>
</html>
