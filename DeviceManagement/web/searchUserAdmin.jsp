<%-- 
    Document   : searchUserAdmin
    Created on : Mar 25, 2020, 10:37:09 PM
    Author     : Peter
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://fonts.googleapis.com/css?family=Roboto:300,400" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search User Admin Page</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                    <c:url var="homeLink" value="MainController" >
                        <c:param name="action" value="Home" />
                    </c:url>
                    <a class="nav-item nav-link active text-light bg-primary" href="${homeLink}">Home <span class="sr-only">(current)</span></a>
                    <div class="dropdown">
                        <a class="btn dropdown-toggle" type="button" id="dropdownMenuButton1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Search
                        </a>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                            <c:url var="searchUserLink" value="MainController" >
                                <c:param name="action" value="Search" />
                                <c:param name="txtUserSearch" value="${param.txtUserSearch}" />
                            </c:url>
                            <a class="dropdown-item"  href="${searchUserLink}">User</a>
                            <c:url var="searchRoomLink" value="MainController" >
                                <c:param name="action" value="Search" />
                                <c:param name="txtRoomSearch" value="${param.txtRoomSearch}" />
                            </c:url>
                            <a class="dropdown-item"  href="${searchRoomLink}">Room</a>
                            <c:url var="searchDeviceLink" value="MainController" >
                                <c:param name="action" value="Search" />
                                <c:param name="txtDeviceSearch" value="${param.txtDeviceSearch}" />
                            </c:url>
                            <a class="dropdown-item"  href="${searchDeviceLink}">Device</a>
                        </div>
                    </div>
                    <div class="dropdown">
                        <a class="btn dropdown-toggle" type="button" id="dropdownMenuButton2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Add new
                        </a>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton2">
                            <a class="dropdown-item" href="insertUser.jsp">User</a>
                            <a class="dropdown-item" href="insertRoom.jsp">Room</a>
                            <a class="dropdown-item" href="insertDevice.jsp">Device</a>
                        </div>
                    </div>
                    <a class="nav-item nav-link active" href="statistical.jsp">Statistical</a>
                    <c:url var="logoutLink" value="MainController" >
                        <c:param name="action" value="Logout" />
                    </c:url>
                    <a class="nav-item nav-link text-danger" href="${logoutLink}">Logout</a>
                </div>
            </div>
        </nav>
        <!--
        Search user feature
        -->
        <nav class="navbar navbar-light bg-light">
            <form action="MainController" method="POST" class="form-inline">
                <input class="form-control mr-sm-2" type="text" name="txtUserSearch" placeholder="Search device..." />
                <input type="hidden" name="txtUserSearch" value="${param.txtUserSearch}" />
                <input type="submit" name="action" value="Search" class="btn btn-outline-success my-2 my-sm-0"/>
            </form>
        </nav>
        <c:if test="${requestScope.ERROR != null}">
            <font color="red">${requestScope.ERROR}</font>
        </c:if>
        <c:if test="${requestScope.SUCCESS != null}">
            <font color="green">${requestScope.SUCCESS}</font>
        </c:if>
        <c:if test="${requestScope.UserINFO != null}">
            <c:if test="${not empty requestScope.UserINFO}" var="UserList">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">No</th>
                            <th scope="col">Username</th>
                            <th scope="col">Fullname</th>
                            <th scope="col">Role</th>
                            <th scope="col">Delete</th>
                            <th scope="col">Edit</th>
                            <th scope="col">Add Or Change room</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.UserINFO}" var="dto" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${dto.username}</td>
                                <td>${dto.fullname}</td>
                                <td>${dto.role}</td>
                                <td>
                                    <c:url var="deleteLink" value="MainController" >
                                        <c:param name="action" value="Delete" />
                                        <c:param name="UserID" value="${dto.username}" />
                                        <c:param name="txtUserSearch" value="${param.txtUserSearch}" />
                                    </c:url>
                                    <a href="${deleteLink}">Delete</a>
                                </td>
                                <td>
                                    <form action="MainController" method="POST">
                                        <input type="hidden" name="UserID" value="${dto.username}" />
                                        <input type="hidden" name="txtUserSearch" value="${param.txtUserSearch}" />
                                        <input type="submit" name="action" value="Edit" class="btn btn-info"/>
                                    </form>
                                </td>
                                <td>
                                    <form action="MainController" method="POST">
                                        <input type="hidden" name="UserID" value="${dto.username}" />
                                        <input type="hidden" name="txtUserSearch" value="${param.txtUserSearch}" />
                                        <input type="submit" name="action" value="Add/Change User Room" class="btn btn-warning" />
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${!UserList}">
                No record found
            </c:if>
        </c:if>
    </body>
</html>
