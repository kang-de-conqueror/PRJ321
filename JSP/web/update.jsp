<%-- 
    Document   : update
    Created on : Feb 20, 2020, 4:30:12 PM
    Author     : Peter
--%>

<%@page import="khangtl.dtos.RegistrationDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Update Page</h1>
        <%
            RegistrationDTO dto = (RegistrationDTO) request.getAttribute("DTO");
        %>
        <form action="MainController" method="POST">
            Username: <input type="text" name="txtUsername"
                             value="<%= dto.getUsername()%>"
                             readonly="true"/><br/>
            Fullname: <input type="text" name="txtFullname"
                             value="<%= dto.getFullname()%>"/><br/>
            Role <input type="text" name="txtRole"
                        value="<%= dto.getRole()%>"/><br/>
            <input type="hidden" name="txtSearch" value="<% request.getParameter("txtSearch");%>"/>
            <input type="submit" name="action" value="Update" />
        </form>
    </body>
</html>
