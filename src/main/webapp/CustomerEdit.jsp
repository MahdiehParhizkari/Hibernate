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
    <title>Edit</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        body {
            background: #ddf8ff;
        }
        h2 {
            font: 40px Helvetica;
            margin-left: 10px;
        }
        td{
            font: 15px Helvetica, Arial, sans-serif;
            padding: 5px 10px;
        }
        .inp{
            height: 30px;
            weight: 80px;
            border: 3px solid #ccc;
            box-sizing: border-box;
        }
        input[type=submit]{
            font: 15px Helvetica, Arial, sans-serif;
            box-sizing: border-box;
            border: none;
            height: 40px;
            width: 100px;
            font-size: 15px;
            cursor: pointer;
        }
        input[type=button]{
            font: 15px Helvetica, Arial, sans-serif;
            box-sizing: border-box;
            border: none;
            height: 40px;
            width: 100px;
            font-size: 15px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<h2>Edit customer:</h2>
<form action="CustomerAct" method="post">
    <table>
        <tr>
            <td><input type="hidden" value="${requestScope.cust.customerNumber}" name="custnum" class="inp"></td>
        </tr>
        <tr>
            <td>CustomerName:</td>
            <td><input value="${requestScope.cust.customerName}" type="text" name="custname" class="inp"></td>
        </tr>
        <tr>
            <td>ContactLastName:</td>
            <td><input value="${requestScope.cust.contactLastName}" type="text" name="conlname" class="inp"></td>
        </tr>
        <tr>
            <td>ContactFirstName:</td>
            <td><input value="${requestScope.cust.contactFirstName}" type="text" name="confname" class="inp" ></td>
        </tr>
        <tr>
            <td>Phone:</td>
            <td><input value="${requestScope.cust.phone}" type="text" name="phone" class="inp"></td>
        </tr>
        <tr>
            <td>AddressLine1:</td>
            <td><input value="${requestScope.cust.addressLine1}" type="text" name="addl1" class="inp"></td>
        </tr>
        <tr>
            <td>AddressLine2:</td>
            <td><input value="${requestScope.cust.addressLine2}" type="text" name="addl2" class="inp"></td>
        </tr>
        <tr>
            <td>City:</td>
            <td><input value="${requestScope.cust.city}" type="text" name="city" class="inp"></td>
        </tr>
        <tr>
            <td>State:</td>
            <td><input value="${requestScope.cust.state}" type="text" name="state" class="inp"></td>
        </tr>
        <tr>
            <td>PostalCode:</td>
            <td><input value="${requestScope.cust.postalCode}" type="text" name="poscode" class="inp"></td>
        </tr>
        <tr>
            <td>Country:</td>
            <td><input value="${requestScope.cust.country}" type="text" name="count" class="inp"></td>
        </tr>
        <tr>
            <td>SalesRepEmployeeNumber:</td>
            <td><input value="${requestScope.cust.salesRepEmployeeNumber}" type="number" name="srempnum" class="inp"></td>
        </tr>
        <tr>
            <td>CreditLimit:</td>
            <td><input value="${requestScope.cust.creditLimit}" type="number" name="credlim" class="inp"></td>
        </tr>
    </table>
    <input type="hidden" name="crud" value="update">
    <input type="submit" value="Update" class="btn btn-info">
    <input type="button" value="Back" class="btn btn-info" onclick="location.href='Customer.jsp';">
</form>
</body>
</html>
