<%-- 
    Document   : index
    Created on : Mar 19, 2020, 2:13:06 PM
    Author     : Peter
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>All Items</h1>
        <%
            if (request.getAttribute("ERROR") != null) {
        %>
        <font color="red">${requestScope.ERROR}</font>
        <%
            }
        %>
        <%
            if (request.getAttribute("SUCCESS") != null) {
        %>
        <font color="red">${requestScope.SUCCESS}</font>
        <%
            }
        %>
        <c:if test="${requestScope.ItemINFO != null}">
            <c:if test="${not empty requestScope.ItemINFO}" var="ItemList">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Item name</th>
                            <th>Item quantity</th>
                            <th>Repair</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.ItemINFO}" var="dto" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${dto.itemName}</td>
                                <td>${dto.quantity}</td>
                                <td>

                                    <c:url var="repairLink" value="RepairController" >
                                        <c:param name="ItemID" value="${dto.itemID}" />
                                    </c:url>
                                    <a href="${repairLink}">Repair</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>

            <c:if test="${!ItemList}">
                No record found
            </c:if>
        </c:if>
    </body>
</html>
