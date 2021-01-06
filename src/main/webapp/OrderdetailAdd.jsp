<%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 12/21/20
  Time: 11:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add</title>
    <style>
        body {
            background: #35dc9b;
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
<h2 >Add new orderdetail:</h2>
<form action="/OrderdetailAct" method="post">
    <table>
        <tr>
            <td>OrderNumber:</td>
            <td><input type="number" name="ordnum" class="inp"></td>
        </tr>
        <tr>
            <td>ProductCode:</td>
            <td><input type="text" name="procode" class="inp"></td>
        </tr>
        <tr>
            <td>QuantityOrdered:</td>
            <td><input type="number" name="qord" class="inp"></td>
        </tr>
        <tr>
            <td>PriceEach:</td>
            <td><input type="number" name="pe" class="inp"></td>
        </tr>
        <tr>
            <td>OrderLineNumber:</td>
            <td><input type="number" name="oln" class="inp"></td>
        </tr>
    </table>
    <input type="hidden" name="crud" value="add">
    <input type="submit" value="Add">
    <input type="button" value="Back" onclick="location.href='Orderdetail.jsp';">
</form>
</body>
</html>
