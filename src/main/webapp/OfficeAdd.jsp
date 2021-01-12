<%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 12/16/20
  Time: 5:37 AM
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
<h2>Add an office:</h2>
<form action="OfficeAct" method="post">
    <table>
        <tr>
            <td>OfficeCode:</td>
            <td><input type="text" name="offcode" class="inp"></td>
        </tr>
        <tr>
            <td>City:</td>
            <td><input type="text" name="city" class="inp"></td>
        </tr>
        <tr>
            <td>Phone:</td>
            <td><input type="text" name="phone" class="inp"></td>
        </tr>
        <tr>
            <td>AddressLine1:</td>
            <td><input type="text" name="addl1" class="inp"></td>
        </tr>
        <tr>
            <td>AddressLine2:</td>
            <td><input type="text" name="addl2" class="inp"></td>
        </tr>
        <tr>
            <td>State:</td>
            <td><input type="text" name="state" class="inp"></td>
        </tr>
        <tr>
            <td>Country:</td>
            <td><input type="text" name="coun" class="inp"></td>
        </tr>
        <tr>
            <td>PostalCode:</td>
            <td><input type="text" name="pcode" class="inp"></td>
        </tr>
        <tr>
            <td>Territory:</td>
            <td><input type="text" name="ter" class="inp"></td>
        </tr>
    </table>
    <input type="hidden" value="create" name="crud">
    <input type="submit" value="Add">
    <input type="button" value="Back" onclick="location.href='Office.jsp';">

</form>

</body>
</html>
