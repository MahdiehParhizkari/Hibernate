<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.helman.Entity.Office" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 12/16/20
  Time: 6:31 AM
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
<h2>Edit the office: </h2>
<form action="OfficeAct" method="post">
    <%Office office = (Office) request.getAttribute("offobjct");%>
    <table>
        <tr>
            <td><input type="hidden" value="<c:out value="${requestScope.offobjct.officeCode}"/>" name="offcode"></td>
        </tr>
        <tr>
            <td>City:</td>
            <td><input value="<c:out value="${requestScope.offobjct.city}"/>" type="text" name="city" class="inp"></td>
        </tr>
        <tr>
            <td>Phone:</td>
            <td><input value="<c:out value="${requestScope.offobjct.phone}"/>" type="text" name="phone" class="inp"></td>
        </tr>
        <tr>
            <td>AddressLine1:</td>
            <td><input value="<c:out value="${requestScope.offobjct.addressLine1}"/>" type="text" name="addl1" class="inp"></td>
        </tr>
        <tr>
            <td>AddressLine2:</td>
            <td><input value="<c:out value="${requestScope.offobjct.addressLine2}"/>" type="text" name="addl2" class="inp"></td>
        </tr>
        <tr>
            <td>State:</td>
            <td><input value="<c:out value="${requestScope.offobjct.state}"/>" type="text" name="state" class="inp"></td>
        </tr>
        <tr>
            <td>Country:</td>
            <td><input value="<c:out value="${requestScope.offobjct.country}"/>" type="text" name="coun" class="inp"></td>
        </tr>
        <tr>
            <td>PostalCode:</td>
            <td><input value="<c:out value="${requestScope.offobjct.postalCode}"/>" type="text" name="pcode" class="inp"></td>
        </tr>
        <tr>
            <td>Territory:</td>
            <td><input value="<c:out value="${requestScope.offobjct.territory}"/>" type="text" name="ter" class="inp"></td>
        </tr>
    </table>
    <input type="hidden" value="update" name="crud">
    <input type="submit" value="Update" class="btn btn-info">
    <input type="button" value="Back" class="btn btn-info" onclick="location.href='Office.jsp';">

</form>
</body>
</html>
