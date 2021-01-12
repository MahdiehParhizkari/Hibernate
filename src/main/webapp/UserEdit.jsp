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
    <input type="submit" value="Update">
    <input type="hidden" value="update" name="crud">
    <input type="button" value="Back" onclick="location.href='User.jsp';">

</form>
</body>
</html>
