<%@ page import="com.helman.Entity.User" %><%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 12/23/20
  Time: 7:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<h2>Edit User:</h2>
<form action="UserAct" method="post">
    <%User user = (User) request.getAttribute("us");%>
    <table>
        <tr>
            <td><input type="hidden" value="<%=user.getId()%>" name="id"></td>
        </tr>
        <tr>
            <td>Username:</td>
            <td><input type="text" value="<%=user.getUsername()%>" name="un" class="inp"></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="text" value="<%=user.getPassword()%>" name="pw" class="inp"></td>
        </tr>
        <tr>
            <td>Employeefk:</td>
            <td><input type="number" value="<%=user.getEmployeefk()%>" name="empfk" class="inp"></td>
        </tr>
    </table>
    <input type="submit" value="Update" class="btn btn-info">
    <input type="hidden" value="update" name="crud">
    <input type="button" value="Back" class="btn btn-info" onclick="location.href='User.jsp';">

</form>
</body>
</html>
