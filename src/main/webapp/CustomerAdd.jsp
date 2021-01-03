<%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 12/15/20
  Time: 12:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add</title>
</head>
<body>
<h2>Add new Customer.</h2>
<form action="CustomerAct" method="post">
    CustomerNumber: <input type="number" name="custnum"><br>
    CustomerName: <input type="text" name="custname"><br>
    ContactLastName: <input type="text" name="conlname"><br>
    ContactFirstName: <input type="text" name="confname" ><br>
    Phone: <input type="text" name="phone"><br>
    AddressLine1: <input type="text" name="addl1"><br>
    AddressLine2: <input type="text" name="addl2"><br>
    City: <input type="text" name="city"><br>
    State: <input type="text" name="state"><br>
    PostalCode: <input type="text" name="c"><br>
    Country: <input type="text" name="count"><br>
    SalesRepEmployeeNumber: <input type="number" name="srempnum"><br>
    CreditLimit: <input type="number" name="credlim"><br><br>
    <input type="hidden" name="crud" value="create">
    <input type="submit" value="Add">
    <input type="button" value="Back" onclick="location.href='Customer.jsp';">
</form>

</body>
</html>
