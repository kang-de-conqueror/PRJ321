<%-- 
    Document   : handleRepair
    Created on : Mar 18, 2020, 10:20:29 PM
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
        <title>Handle Repair Page</title>
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
        <div class="container w-50">
            <div class="card">
                <div class="card-body">
                    <h3 class="text-center">Repair request of ${requestScope.DeviceName}</h3>
                    <c:forEach items="${requestScope.RepairINFO}" var="dto" varStatus="counter">
                        <div class="card p-4">
                            <p class="m-0">${counter.count})</p>
                            <p class="m-0">- <b>Repair ID:</b> ${dto.repairID}</p>
                            <p class="m-0">- <b>Request description:</b> ${dto.requestDescription}</p>
                            <p class="m-0">- <b>User request:</b> ${dto.userRequest}</p>
                            <p class="m-0">- <b>Request time:</b> ${dto.requestTime}</p>
                            <c:if test="${dto.endTime != null}">
                                <p class="text-success m-0">- <b>Status:</b> This request has solved!</p>
                                <p class="m-0">- <b>Repair description:</b> ${dto.repairDescription}</p>
                                <p class="m-0">- <b>Repair result</b> ${dto.repairResult}</p>
                            </c:if>
                            <c:if test="${dto.endTime == null}">
                                <c:if test="${dto.startTime == null}">
                                    <div class="d-flex flex-row justify-content-between mt-2">
                                        <form action="MainController" method="POST">
                                            <input type="hidden" name="RepairID" value="${dto.repairID}" />
                                            <input type="hidden" name="txtDeviceSearch" value="${param.txtDeviceSearch}" />
                                            <input type="submit" name="action" value="Start Repair" class="btn btn-warning"/>
                                        </form>
                                        <form action="MainController" method="POST">
                                            <input type="hidden" name="RepairID" value="${dto.repairID}" />
                                            <input type="hidden" name="txtDeviceSearch" value="${param.txtDeviceSearch}" />
                                            <input type="submit" name="action" value="Cancel Request" class="btn btn-danger"/>
                                        </form>
                                    </div>
                                </c:if>
                                <c:if test="${dto.startTime != null}">
                                    <form action="MainController" method="POST">
                                        <input type="hidden" name="DeviceName" value="${requestScope.DeviceName}" />
                                        <input type="hidden" name="txtDeviceSearch" value="${param.txtDeviceSearch}" />
                                        <input type="hidden" name="RepairID" value="${dto.repairID}" />
                                        Click button when you finish repair this device: <input type="submit" name="action" value="Finish Repair" class="btn btn-success"/>
                                    </form>
                                </c:if>
                            </c:if>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </body>
</html>
