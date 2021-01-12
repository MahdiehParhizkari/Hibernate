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
    <input type="submit" value="Add" class="btn btn-info">
    <input type="button" value="Back" class="btn btn-info" onclick="location.href='Employee.jsp';">

</form>
</body>
</html>
