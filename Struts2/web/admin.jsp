<%-- 
    Document   : admin
    Created on : Mar 12, 2020, 3:40:48 PM
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
        <h1>Welcome, <s:property value="%{#session.USER}"/></h1>
        <h2>Search</h2>
        <s:form action="SearchAction" method="POST">
            <s:textfield name="searchValue" label="Fullname" />
            <s:submit value="Search" />
        </s:form>
        <s:if test="%{listAccount != null}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Username</th>
                        <th>Fullname</th>
                        <th>Role</th>
                        <th>Delete</th>
                        <th>Edit</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="listAccount" status="counter">
                        <tr>
                            <td><s:property value="%{#counter.count}" /></td>
                            <td><s:property value="username" /></td>
                            <td><s:property value="fullname" /></td>
                            <td><s:property value="Role" /></td>
                            <td>
                                <s:url action="DeleteAction" id="DeleteLink">
                                    <s:param name="lastSearchValue" value="%{searchValue}" />
                                    <s:param name="id" value="%{username}" />
                                </s:url>
                                <s:a href="%{DeleteLink}">Delete</s:a>
                                </td>
                                <td>
                                <s:form action="EditAction" method="POST">
                                    <s:hidden name="lastSearchValue" value="%{searchValue}" />
                                    <s:hidden name="id" value="%{username}" />
                                    <s:submit value="Edit" />
                                </s:form>
                            </td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
        </s:if>
    </body>
</html>
