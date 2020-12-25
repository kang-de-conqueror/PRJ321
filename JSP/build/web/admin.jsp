<%-- 
    Document   : admin
    Created on : Feb 20, 2020, 2:46:07 PM
    Author     : Peter
--%>

<%@page import="khangtl.dtos.RegistrationDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello Admin!</h1>
        <h2>Search</h2>
        <form action="MainController" method="POST">
            Fullname: <input type="text" name="txtSearch" /> <br/>
            <input type="submit" name="action" value="Search" />
        </form>

        <%
            List<RegistrationDTO> result = (List<RegistrationDTO>) request.getAttribute("INFO");
            if (result != null) {
                if (result.size() > 0) {
        %>
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
                <%
                    int count = 0;
                    for (RegistrationDTO dto : result) {
                        count++;
                %>
                <tr>
                    <td><%= count%></td>
                    <td><%= dto.getUsername()%></td>
                    <td><%= dto.getFullname()%></td>
                    <td><%= dto.getRole()%></td>
                    <td>
                        <a href="MainController?action=Delete&id=<%= dto.getUsername()%>&txtSearch=<%= request.getParameter("txtSearch")%>">Delete</a>
                    </td>
                    <td>
                        <form action="MainController" method="POST">
                            <input type="hidden" name="txtSearch" value="<%= request.getParameter("txtSearch")%>"/>
                            <input type="hidden" name="txtUsername" value="<%= dto.getUsername()%>"/>
                            <input type="submit" name="action" value="Edit"/>
                        </form>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <%
        } else {
        %>
        <font color="red">
        No record found!
        <font/>
        <%
                }
            }
        %>

    </body>
</html>
