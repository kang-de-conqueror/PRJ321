<%-- 
    Document   : user
    Created on : Mar 14, 2020, 12:23:26 PM
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
        <title>Staff Page</title>
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
            <h2 class="mt-4">Welcome staff, ${sessionScope.FULLNAME}</h2>
            <c:if test="${requestScope.ERROR != null}">
                <font color="red">${requestScope.ERROR}</font>
            </c:if>
            <c:if test="${requestScope.SUCCESS != null}">
                <font color="green">${requestScope.SUCCESS}</font>
            </c:if>
            <div class="card">
                <div class="card-body">
                    <h2 class="mt-4">Notifications:</h2>
                    <c:if test="${not empty requestScope.RepairRequest}" var="RequestList">
                        <c:forEach items="${requestScope.RepairRequest}" var="dto" varStatus="counter">
                            <p class="text-primary m-0">${counter.count}) ${dto.userRequest} request staff to repair "${dto.deviceName}" at: ${dto.requestTime}
                                <c:if test="${dto.canceled == true}">
                                    (Canceled)
                                </c:if>
                                <c:if test="${dto.canceled == false}">
                                    <c:if test="${dto.endTime != null}">
                                        (Done)
                                    </c:if>
                                    <c:if test="${dto.endTime == null}">
                                        <c:if test="${dto.startTime == null}">
                                            (Not handle yet)
                                        </c:if>
                                        <c:if test="${dto.startTime != null}">
                                            (In progress)
                                        </c:if>
                                    </c:if>
                                </c:if>
                            </p>
                        </c:forEach>
                    </c:if>
                    <c:if test="${!RequestList}">
                        No record found
                    </c:if>
                </div>
            </div>
        </div>
    </body>
</html>
