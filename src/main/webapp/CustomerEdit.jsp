<%@ page import="com.helman.Entity.Customer" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 12/15/20
  Time: 2:00 AM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Edit customer:</h2>
<form action="CustomerAct" method="post">
    <input type="hidden" value="${requestScope.cust.customerNumber}" name="custnum">
    CustomerName: <input value="${requestScope.cust.customerName}" type="text" name="custname"><br>
    ContactLastName: <input value="${requestScope.cust.contactLastName}" type="text" name="conlname"><br>
    ContactFirstName: <input value="${requestScope.cust.contactFirstName}" type="text" name="confname" ><br>
    Phone: <input value="${requestScope.cust.phone}" type="text" name="phone"><br>
    AddressLine1: <input value="${requestScope.cust.addressLine1}" type="text" name="addl1"><br>
    AddressLine2: <input value="${requestScope.cust.addressLine2}" type="text" name="addl2"><br>
    City: <input value="${requestScope.cust.city}" type="text" name="city"><br>
    State: <input value="${requestScope.cust.state}" type="text" name="state"><br>
    PostalCode: <input value="${requestScope.cust.postalCode}" type="text" name="poscode"><br>
    Country: <input value="${requestScope.cust.country}" type="text" name="count"><br>
    SalesRepEmployeeNumber: <input value="${requestScope.cust.salesRepEmployeeNumber}" type="number" name="srempnum"><br>
    CreditLimit: <input value="${requestScope.cust.creditLimit}" type="number" name="credlim"><br><br>
    <input type="hidden" name="crud" value="update">
    <input type="submit" value="Update">
    <input type="button" value="Back" onclick="location.href='Customer.jsp';">
</form>
</body>
</html>
