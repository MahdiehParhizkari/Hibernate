<%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 12/14/20
  Time: 2:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add</title>
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
        input[type=submit] {
            font: 18px Helvetica, Arial, sans-serif;
            box-sizing: border-box;
            border: none;
            height: 40px;
            width: 100px;
            font-size: 15px;
            cursor: pointer;
        }
        input[type=button] {
            font: 18px Helvetica, Arial, sans-serif;
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
<h2>Add new Employee:</h2>
<form action="EmployeeAct" method="post">
    <table>
        <tr>
            <td>EmployeeNumber:</td>
            <td><input type="number" name="empnum" class="inp"></td>
        </tr>
        <tr>
            <td>LastName:</td>
            <td><input type="text" name="lname" class="inp"></td>
        </tr>
        <tr>
            <td>FirstName:</td>
            <td><input type="text" name="fname" class="inp"></td>
        </tr>
        <tr>
            <td>Extension:</td>
            <td><input type="text" name="exten" class="inp"></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type="text" name="email" class="inp"></td>
        </tr>
        <tr>
            <td>OfficeCode:</td>
            <td><input type="text" name="offcode" class="inp"></td>
        </tr>
        <tr>
            <td>Reportto:</td>
            <td><input type="number" name="repto" class="inp"></td>
        </tr>
        <tr>
            <td>JobTitle:</td>
            <td><input type="text" name="jobtit" class="inp"></td>
        </tr>
    </table>
    <input type="hidden" value="create" name="crud">
    <input type="submit" value="Add">
    <input type="button" value="Back" onclick="location.href='Employee.jsp';">

</form>
</body>
</html>
