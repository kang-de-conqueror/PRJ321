<%-- 
    Document   : index
    Created on : Feb 25, 2020, 4:46:51 PM
    Author     : Peter
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Book Store</h1>
        <form action="MainController" method="POST">
            Please choose your book:
            <select name="listBook">
                <option value="PRJ311-Java Desktop-100">Java Desktop</option>
                <option value="PRJ321-Java Web-200">Java Web</option>
                <option value="PRM391-Mobile-300">Mobile</option>
                <option value="PRX301-XML-400">XML</option>
                <option value="Caps-Capstone-500">Capstone</option>
            </select>
            <input type="submit" name="action" value="Add to Cart"/>
        </form>
        <a href="view.jsp">View your cart</a>
    </body>
</html>
