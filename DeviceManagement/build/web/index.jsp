<%-- 
    Document   : index
    Created on : Mar 13, 2020, 3:52:22 AM
    Author     : Peter
--%>

<%@page import="khangtl.dtos.UsersErrorObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <div class="container w-30 d-flex flex-column align-items-center">
            <img src="https://upload.wikimedia.org/wikipedia/vi/8/80/FPT_New_Logo.png" width="250" height="120" class="mt-5 mb-3"/>
            <div class="border border-primary p-4 mt-3 mb-3 rounded">
                <h2>Device Management Program</h2>
            </div>
            <h4>Login Page</h4>
            <div class="card mt-3">
                <div class="card-body">
                    <form action="MainController" method="POST">
                        <div class="form-group">
                            <label for="txtUsername" class="text-primary">
                                Username:
                            </label>
                            <div>
                                <input class="form-control" type="text" name="txtUsername" placeholder="Username..." />
                                <font color="red">
                                ${requestScope.INVALID.usernameError}    
                                </font>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="txtPassword" class="text-primary"> 
                                Password:
                            </label>
                            <div>
                                <input class="form-control" type="password" name="txtPassword" placeholder="Password..." /> 
                                <font color="red">
                                ${requestScope.INVALID.passwordError}    
                                </font>
                                <br/>
                            </div>
                        </div>
                        <div class="w-100 d-flex justify-content-center">
                            <input type="submit" name="action" value="Login" class="btn btn-primary"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
