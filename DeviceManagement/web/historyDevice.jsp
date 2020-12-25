<%-- 
    Document   : historyDevice
    Created on : Mar 17, 2020, 2:36:03 AM
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
        <title>History Device Page</title>
    </head>
    <body>
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
        <div class="container">
            <div class="card">
                <div class="card-body">
                    <h3>This device are current in:</h3>
                    <p class="m-0">- <b>Current room:</b> ${requestScope.CurrentRoom.currentRoomID}</p>
                    <p class="m-0">- <b>Start time at room:</b> ${requestScope.CurrentRoom.startTime}</p>
                    <c:if test="${requestScope.CurrentRoom.pastRoomID != 0}">
                        <p class="m-0">- <b>Past room:</b> ${requestScope.CurrentRoom.pastRoomID}</p>
                        <c:if test="${requestScope.CurrentRoom.endTime != null}">
                            <p class="m-0">- <b>End time at room:</b> ${requestScope.CurrentRoom.endTime}</p>
                            <p class="m-0 text-danger">- <b>Status:</b> This device is no longer in use</p>
                        </c:if>
                        <c:if test="${requestScope.CurrentRoom.endTime == null}">
                            <p class="m-0 text-success">- <b>Status:</b> This device is in use</p>
                        </c:if>
                        <p class="m-0">- <b>User move:</b> ${requestScope.CurrentRoom.fullname}</p>
                        <p class="m-0">- <b>Move time:</b> ${requestScope.CurrentRoom.moveTime}</p>
                        <p class="m-0">- <b>Move reason:</b> ${requestScope.CurrentRoom.moveReason}</p>
                    </c:if>
                </div>
            </div>
        </div>
        <c:if test="${not empty requestScope.HistoryINFO}">
            <div class="container mt-4">
                <div class="card">
                    <div class="card-body">
                        <h3>Nearest device's history room:</h3>
                        <c:forEach items="${requestScope.HistoryINFO}" var="dto" varStatus="counter">
                            <p class="m-0 mt-2">${counter.count})</p>
                            <p class="m-0">- <b>Current room:</b> ${dto.currentRoomID}</p>
                            <p class="m-0">- <b>Start time at room:</b> ${dto.startTime}</p>
                            <p class="m-0">- <b>End time at room:</b> ${dto.endTime}</p>
                            <c:if test="${dto.pastRoomID != 0}">
                                <p class="m-0">- <b>Past room:</b> ${dto.pastRoomID}</p>
                                <p class="m-0">- <b>User move:</b> ${dto.fullname}</p>
                                <p class="m-0">- <b>Move time:</b> ${dto.moveTime}</p>
                                <p class="m-0">- <b>Move reason:</b> ${dto.moveReason}</p>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </c:if>
    </body>
</html>
