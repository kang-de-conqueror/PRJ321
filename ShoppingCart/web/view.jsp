<%-- 
    Document   : view
    Created on : Feb 27, 2020, 2:29:19 PM
    Author     : Peter
--%>

<%@page import="khangtl.dtos.BookDTO"%>
<%@page import="khangtl.dtos.CartDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            CartDTO shoppingCart = (CartDTO) session.getAttribute("cart");
            if (shoppingCart != null) {
        %>
        <h1><%= shoppingCart.getCustomerName()%>'s cart</h1>
        <form method="POST" action="MainController">
            
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Book name</th>
                    <th>Book price</th>
                    <th>Quantity</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (BookDTO dto : shoppingCart.getCart().values()) {
                        count++;
                %>
                <tr>
                    <td><%= count%></td>
                    <td><%= dto.getName()%></td>
                    <td><%= dto.getPrice()%></td>
                    <td>
                        <input type="text" name="txtQuantity" value="<%= dto.getQuantity()%>" />
                        <input type="hidden" name="txtId" value="<%= dto.getId() %>" />
                    </td>
                    <td>
                        <input type="checkbox" name="checkRemove" value="<%= dto.getId()%>" />
                    </td>
                </tr>
                <%
                    }
                %>
                <tr>
                    <td></td>
                    <td>
                        <a href="index.jsp">Continue Shopping</a>
                    </td>
                    <td>
                        Total = <%= shoppingCart.getTotal() %>
                    </td>
                    <td></td>
                    <td>
                        <input type="submit" name="action" value="Delete" />
                        <input type="submit" name="action" value="Update" />
                    </td>
                </tr>
            </tbody>
        </table>
    </form>
        <% } else { %>
        <h2>Your cart is empty.</h2 >
        <%
            }
        %>
    </body>
</html>
