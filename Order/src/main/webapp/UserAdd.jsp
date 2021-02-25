<%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 12/23/20
  Time: 6:05 PM
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
<H2>Add new user:</H2>
<form action="UserAct" method="post">
    <table>
        <tr>
            <td>Id:</td>
            <td><input type="number" name="id" class="inp"></td>
        </tr>
        <tr>
            <td>Username:</td>
            <td><input type="text" name="un" class="inp"></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="text" name="pw" class="inp"></td>
        </tr>
        <tr>
            <td>Employeefk:</td>
            <td><input type="number" name="empfk" class="inp"></td>
        </tr>
    </table>
    <input type="submit" value="Add" class="btn btn-info">
    <input type="hidden" value="add" name="crud">
    <input type="button" value="Back" class="btn btn-info" onclick="location.href='User.jsp';">
</form>
</body>
</html>
