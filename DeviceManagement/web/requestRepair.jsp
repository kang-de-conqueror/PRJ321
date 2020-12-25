<%-- 
    Document   : requestRepair
    Created on : Mar 17, 2020, 10:00:32 PM
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
        <title>Repair Request Page</title>
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
                    <h3 class="text-center">Repair request form</h3>
                    <form action="MainController" method="POST">
                        <div class="form-group row">
                            <label for="txtDeviceID" class="col-sm-2 col-form-label">Device id:</label>
                            <div class="col-sm-10">
                                <input type="text" readonly="true" value="${requestScope.DeviceDTO.deviceID}" name="txtDeviceID" class="form-control" id="txtDeviceID" />
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="txtDeviceName" class="col-sm-2 col-form-label">Name:</label>
                            <div class="col-sm-10">
                                <input type="text" readonly="true" value="${requestScope.DeviceDTO.deviceName}" name="txtDeviceName" class="form-control" id="txtDeviceName" />
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="txtRequestDescription" class="col-sm-2 col-form-label">Description:</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" rows="4" cols="50" name="txtRequestDescription" id="txtRequestDescription"></textarea>
                            </div>
                        </div>
                        <font color="red">${requestScope.INVALID_REQUEST_REPAIR}</font>
                        <input type="hidden" name="txtDeviceSearch" value="${param.txtDeviceSearch}" />
                        <div class="w-100 d-flex justify-content-center">
                            <input type="submit" name="action" value="Confirm Repair" class="btn btn-success"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
