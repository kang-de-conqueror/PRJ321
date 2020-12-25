<%-- 
    Document   : finishRepair
    Created on : Mar 28, 2020, 11:28:57 PM
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
        <title>Finish Repair Page</title>
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
                    <h3 class="text-center">Finish Repair Form</h3>
                    <h5 class="text-center">RepairID: ${requestScope.RepairID} - Device: ${requestScope.DeviceName}</h5>
                    <form action="MainController" method="POST">
                        <div class="form-group row">
                            <label for="txtDescription" class="col-sm-2 col-form-label">- <b>Description:</b></label>
                            <div class="col-sm-10">
                                <input type="text" name="txtDescription" class="form-control" id="txtDescription" />
                            </div>
                        </div>
                        <c:if test="${requestScope.INVALID_DESCRIPTION != null}">
                            <font color="red">${requestScope.INVALID_DESCRIPTION}</font>
                        </c:if>
                        <div class="form-group row">
                            <label for="txtResult" class="col-sm-2 col-form-label">- <b>Result:</b></label>
                            <div class="col-sm-10">
                                <input type="text" name="txtResult" class="form-control" id="txtResult" />
                            </div>
                        </div>
                        <c:if test="${requestScope.INVALID_RESULT != null}">
                            <font color="red">${requestScope.INVALID_RESULT}</font>
                        </c:if>
                        <input type="hidden" name="txtDeviceSearch" value="${param.txtDeviceSearch}" />
                        <input type="hidden" name="RepairID" value="${requestScope.RepairID}" />
                        <input type="hidden" name="DeviceName" value="${requestScope.DeviceName}" />
                        <div class="w-100 d-flex justify-content-centert">
                            <input type="submit" name="action" value="Confirm Finish Repair" class="btn btn-success"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
