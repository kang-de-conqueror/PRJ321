<%-- 
    Document   : statistical
    Created on : Mar 26, 2020, 2:52:49 AM
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
        <title>Statistical Page</title>
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
        <div class="container mt-4">
            <div class="card">
                <div class="card-body">
                    <h3>Search device by date:</h3>
                    <form action="MainController" method="POST">
                        <div class="form-group row">
                            <label for="txtFrom" class="col-sm-2 col-form-label">From:</label>
                            <div class="col-sm-10">
                                <input type="text" name="txtFrom" class="form-control" id="txtFrom" placeholder="From date...">
                            </div>
                        </div>
                        <font color='red'>${requestScope.INVALID_FROM}</font>
                        <div class="form-group row">
                            <label for="txtTo" class="col-sm-2 col-form-label">To:</label>
                            <div class="col-sm-10">
                                <input type="text" name="txtTo" class="form-control" id="txtTo" placeholder="To date...">
                            </div>
                        </div>
                        <font color='red'>${requestScope.INVALID_TO}</font>
                        <font color='red'>${requestScope.INVALID_DATE}</font> <br /> 
                        <input type="submit" name="action" value="Search by date" class="btn btn-outline-success my-2 my-sm-0"/>
                    </form> 
                </div>
            </div>
        </div>
        <c:if test="${requestScope.DeviceDateINFO != null}">
            <c:if test="${not empty requestScope.DeviceDateINFO}" var="DeviceList">
                <table class="table">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Device Name</th>
                            <th>Device Description</th>
                            <th>Device Type</th>
                            <th>Device State</th>
                            <th>Device Image</th>
                            <th>Buy Date</th>
                            <th>Guarantee Duration</th>
                            <th>Delete</th>
                            <th>Edit</th>
                            <th>Add Or Change Room</th>
                            <th>Repair History</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.DeviceDateINFO}" var="dto" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${dto.deviceName}</td>
                                <td>${dto.deviceDescription}</td>
                                <td>${dto.deviceType}</td>
                                <td>${dto.deviceState}</td>
                                <td><img src="<c:url value='${dto.deviceImage}'></c:url>" width="150" height="150" alt="thietbi"/></td>
                                <td>${dto.buyDate}</td>
                                <td>${dto.guaranteeDuration}</td>
                                <td>
                                    <c:url var="deleteLink" value="MainController" >
                                        <c:param name="action" value="Delete" />
                                        <c:param name="DeviceID" value="${dto.deviceID}" />
                                        <c:param name="txtDeviceSearch" value="${param.txtDeviceSearch}" />
                                    </c:url>
                                    <a href="${deleteLink}">Delete</a>
                                </td>
                                <td>
                                    <form action="MainController" method="POST">
                                        <input type="hidden" name="DeviceID" value="${dto.deviceID}" />
                                        <input type="submit" name="action" value="Edit" class="btn btn-info"/>
                                    </form>
                                </td>
                                <td>
                                    <form action="MainController" method="POST">
                                        <input type="hidden" name="DeviceID" value="${dto.deviceID}" />
                                        <input type="submit" name="action" value="Add/Change Device Room" class="btn btn-warning"/>
                                    </form>
                                </td>
                                <td>
                                    <form action="MainController" method="POST">
                                        <input type="hidden" name="txtDeviceSearch" value="${param.txtDeviceSearch}" />
                                        <input type="hidden" name="DeviceID" value="${dto.deviceID}" />
                                        <input type="submit" name="action" value="View History Repair" class="btn btn-light"/>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${!DeviceList}">
                No record found
            </c:if>
        </c:if>
        <div class="container mt-4">
            <div class="card">
                <div class="card-body">
                    <h3>Search device by repair quantity:</h3>
                    <form action="MainController" method="POST">
                        <div class="form-group row">
                            <label for="txtRepairQuantity" class="col-sm-2 col-form-label">Repair quantity:</label>
                            <div class="col-sm-10">
                                <input type="text" name="txtRepairQuantity" class="form-control" id="txtRepairQuantity" placeholder="Quantity..." />
                            </div>
                        </div>
                        <font color="red">${requestScope.INVALID_REPAIR_QUANTITY}</font> <br />
                        <input type="submit" name="action" value="Search by repair's quantity" class="btn btn-outline-success my-2 my-sm-0" />
                    </form>
                </div>
            </div>
        </div>
        <c:if test="${requestScope.DeviceQuantityINFO != null}">
            <c:if test="${not empty requestScope.DeviceQuantityINFO}" var="DeviceList">
                <table class="table">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Device Name</th>
                            <th>Device Description</th>
                            <th>Device Type</th>
                            <th>Device State</th>
                            <th>Device Image</th>
                            <th>Buy Date</th>
                            <th>Guarantee Duration</th>
                            <th>Delete</th>
                            <th>Edit</th>
                            <th>Add Or Change Room</th>
                            <th>Repair History</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.DeviceQuantityINFO}" var="dto" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${dto.deviceName}</td>
                                <td>${dto.deviceDescription}</td>
                                <td>${dto.deviceType}</td>
                                <td>${dto.deviceState}</td>
                                <td><img src="<c:url value='${dto.deviceImage}'></c:url>" width="150" height="150" alt="thietbi"/></td>
                                <td>${dto.buyDate}</td>
                                <td>${dto.guaranteeDuration}</td>
                                <td>
                                    <c:url var="deleteLink" value="MainController" >
                                        <c:param name="action" value="Delete" />
                                        <c:param name="DeviceID" value="${dto.deviceID}" />
                                        <c:param name="txtDeviceSearch" value="${param.txtDeviceSearch}" />
                                    </c:url>
                                    <a href="${deleteLink}">Delete</a>
                                </td>
                                <td>
                                    <form action="MainController" method="POST">
                                        <input type="hidden" name="DeviceID" value="${dto.deviceID}" />
                                        <input type="submit" name="action" value="Edit" class="btn btn-info"/>
                                    </form>
                                </td>
                                <td>
                                    <form action="MainController" method="POST">
                                        <input type="hidden" name="DeviceID" value="${dto.deviceID}" />
                                        <input type="submit" name="action" value="Add/Change Device Room" class="btn btn-warning" />
                                    </form>
                                </td>
                                <td>
                                    <form action="MainController" method="POST">
                                        <input type="hidden" name="txtDeviceSearch" value="${param.txtDeviceSearch}" />
                                        <input type="hidden" name="DeviceID" value="${dto.deviceID}" />
                                        <input type="submit" name="action" value="View History Repair" class="btn btn-light"/>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${!DeviceList}">
                No record found
            </c:if>
        </c:if>
    </body>
</html>
