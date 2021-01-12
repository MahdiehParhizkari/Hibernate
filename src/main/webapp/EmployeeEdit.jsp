<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 12/14/20
  Time: 3:41 AM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Edit</title>
    <style>
        body {
            background: #c2c2c2;
        }
        h2 {
            margin: 0 auto 40px;
            color: #fff;
            font: 40px Helvetica;
        }
        td{
            font: 15px Helvetica, Arial, sans-serif;
            padding: 5px 10px;
        }
        .inp{
            height: 30px;
            weight: 80px;
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
<h2>Edit employee:</h2>
<form action="EmployeeAct" method="post">
    <table>
        <tr>
            <td><input type="hidden" value="${requestScope.empobjt.employeeNumber}" name="empnum" class="inp"></td>
        </tr>
        <tr>
            <td>LastName:</td>
            <td><input value="${requestScope.empobjt.lastName}" type="text" name="lname" class="inp"></td>
        </tr>
        <tr>
            <td>FirstName:</td>
            <td><input value="${requestScope.empobjt.firstName}" type="text" name="fname" class="inp"></td>
        </tr>
        <tr>
            <td>Extension:</td>
            <td><input value="${requestScope.empobjt.extension}" type="text" name="exten" class="inp"></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input value="${requestScope.empobjt.email}" type="text" name="email" class="inp"></td>
        </tr>
        <tr>
            <td>OfficeCode:</td>
            <td><input value="${requestScope.empobjt.officeCode}" type="text" name="offcode" class="inp"></td>
        </tr>
        <tr>
            <td>Reportto:</td>
            <td><input value="${requestScope.empobjt.reportsTo}" type="number" name="repto" class="inp"></td>
        </tr>
        <tr>
            <td>JobTitle:</td>
            <td><input value="${requestScope.empobjt.jobTitle}" type="text" name="jobtit" class="inp"></td>
        </tr>
    </table>
    <input type="hidden" name="crud" value="update">
    <input type="submit" value="Update">
    <input type="button" value="Back" onclick="location.href='Employee.jsp';">
</form>
</body>
</html>
