<%-- 
    Document   : chooseItem
    Created on : Mar 19, 2020, 3:16:14 PM
    Author     : Peter
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Repair Item</h1>
        <form action="ConfirmRepairController" method="POST">
            Item ID: <input type="text" readonly="true" name="txtItemID" value="${requestScope.ItemINFO.itemID}" /> <br/>
            Item name: <input type="text" readonly="true" name="txtItemName" value="${requestScope.ItemINFO.itemName}" /> <br/>
            Item description: <input type="text" readonly="true" name="txtDescription" value="${requestScope.ItemINFO.description}"/> <br/>
            Reason: <input type="text" name="txtReason" />
            <font color="red">${requestScope.INVALID_REPAIR.reasonError}</font> <br />
            <input type="submit" name="action" value="Repair" />
        </form>
    </body>
</html>
