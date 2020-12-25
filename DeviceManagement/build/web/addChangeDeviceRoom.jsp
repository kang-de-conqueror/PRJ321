<%-- 
    Document   : changeRoom
    Created on : Mar 18, 2020, 1:22:17 AM
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
        <title>Add/Change Room Page</title>
    </head>
    <body>
        <c:if test="${sessionScope.UserRole == 'admin'}">
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
                                <a class="dropdown-item" href="${searchUserLink}">User</a>
                                <c:url var="searchRoomLink" value="MainController" >
                                    <c:param name="action" value="Search" />
                                    <c:param name="txtRoomSearch" value="${param.txtRoomSearch}" />
                                </c:url>
                                <a class="dropdown-item" href="${searchRoomLink}">Room</a>
                                <c:url var="searchDeviceLink" value="MainController" >
                                    <c:param name="action" value="Search" />
                                    <c:param name="txtDeviceSearch" value="${param.txtDeviceSearch}" />
                                </c:url>
                                <a class="dropdown-item" href="${searchDeviceLink}">Device</a>
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
        </c:if>
        <c:if test="${sessionScope.UserRole == 'staff'}">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                    <div class="navbar-nav">
                        <c:url var="homeLink" value="MainController" >
                            <c:param name="action" value="Home" />
                        </c:url>
                        <a class="nav-item nav-link active text-light bg-primary" href="${homeLink}">Home <span class="sr-only">(current)</span></a>
                        <c:url var="searchDeviceLink" value="MainController" >
                            <c:param name="action" value="Search" />
                            <c:param name="txtDeviceSearch" value="${param.txtDeviceSearch}" />
                        </c:url>
                        <a class="nav-item nav-link active" href="${searchDeviceLink}">Search device</a>
                        <c:url var="logoutLink" value="MainController" >
                            <c:param name="action" value="Logout" />
                        </c:url>
                        <a class="nav-item nav-link text-danger" href="${logoutLink}">Logout</a>
                    </div>
                </div>
            </nav>
        </c:if>
        <c:if test="${requestScope.CurrentRoom == -1}">
            <div class="container w-50">
                <div class="card">
                    <div class="card-body">
                        <h3 class="text-center">Add new room for device</h3>
                        <form action="MainController" method="POST">
                            <div class="form-group row">
                                <label for="txtDeviceID" class="col-sm-2 col-form-label">Device id:</label>
                                <div class="col-sm-10">
                                    <input type="text" readonly="true" value="${requestScope.DeviceID}" name="txtDeviceID" class="form-control" id="txtDeviceID" />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="txtNewRoom" class="col-sm-2 col-form-label">New room:</label>
                                <div class="col-sm-10">
                                    <input type="text" name="txtNewRoom" class="form-control" id="txtNewRoom" />
                                </div>
                            </div>
                            <c:if test="${requestScope.INVALID_NEW_ROOM.newRoomError != null}">
                                <font color="red">${requestScope.INVALID_NEW_ROOM.newRoomError}</font> <br />
                            </c:if>
                            <input type="hidden" name="txtDeviceSearch" value="${param.txtDeviceSearch}" />
                            <div class="w-100 d-flex justify-content-center">
                                <input type="submit" name="action" value="Confirm New Room" class="btn btn-success"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${requestScope.CurrentRoom != -1}">
            <div class="container w-50">
                <div class="card">
                    <div class="card-body">
                        <h3 class="text-center">Update new room for device</h3>
                        <form action="MainController" method="POST">
                            <div class="form-group row">
                                <label for="txtDeviceID" class="col-sm-2 col-form-label">Device id:</label>
                                <div class="col-sm-10">
                                    <input type="text" readonly="true" value="${requestScope.DeviceID}" name="txtDeviceID" class="form-control" id="txtDeviceID" />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="txtCurrentRoom" class="col-sm-2 col-form-label">Current room:</label>
                                <div class="col-sm-10">
                                    <input type="text" readonly="true" value="${requestScope.CurrentRoom}" name="txtCurrentRoom" class="form-control" id="txtCurrentRoom" />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="txtNewRoom" class="col-sm-2 col-form-label">New room:</label>
                                <div class="col-sm-10">
                                    <input type="text" name="txtNewRoom" class="form-control" id="txtNewRoom" />
                                </div>
                            </div>
                            <c:if test="${requestScope.INVALID_CHANGE_ROOM.newRoomError != null}">
                                <font color="red">${requestScope.INVALID_CHANGE_ROOM.newRoomError}</font>
                            </c:if>
                            <div class="form-group row">
                                <label for="txtReason" class="col-sm-2 col-form-label">Reason:</label>
                                <div class="col-sm-10">
                                    <input type="text" name="txtReason" class="form-control" id="txtReason" />
                                </div>
                            </div>
                            <c:if test="${requestScope.INVALID_CHANGE_ROOM.reasonError != null}">
                                <font color="red">${requestScope.INVALID_CHANGE_ROOM.reasonError}</font>
                            </c:if>
                            <input type="hidden" name="txtDeviceSearch" value="${param.txtDeviceSearch}" />
                            <div class="w-100 d-flex justify-content-center">
                                <input type="submit" name="action" value="Confirm Change Room" class="btn btn-success"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </c:if>
    </body>
</html>
