<%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 12/27/20
  Time: 7:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add</title>
    <style>
        body {
            background:#c2c2c2;
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
<h2>Add new product:</h2>
<form action="ProductAct" method="post">
    <table>
        <tr>
            <td>ProductCode:</td>
            <td><input type="text" name="procode" class="inp"></td>
        </tr>
        <tr>
            <td>ProductName:</td>
            <td><input type="text" name="proname" class="inp"></td>
        </tr>
        <tr>
            <td>ProductLine:</td>
            <td><input type="text" name="proline" class="inp"></td>
        </tr>
        <tr>
            <td>ProductScale:</td>
            <td><input type="text" name="proscale" class="inp"></td>
        </tr>
        <tr>
            <td>ProductVendor:</td>
            <td><input type="text" name="provendor" class="inp"></td>
        </tr>
        <tr>
            <td>ProductDescription:</td>
            <td><input type="text" name="prodesc" class="inp"></td>
        </tr>
        <tr>
            <td>QuantityInStock:</td>
            <td><input type="number" name="quanstock" class="inp"></td>
        </tr>
        <tr>
            <td>BuyPrice:</td>
            <td><input type="number" step="0.01" name="buyp" class="inp"></td>
        </tr>
        <tr>
            <td>MSRP:</td>
            <td><input type="number" step="0.01" name="msrp" class="inp"></td>
        </tr>
    </table>
    <input type="submit" value="Add">
    <input type="hidden" value="add" name="crud">
    <input type="button" value="Back" onclick="location.href='Product.jsp';">
</form>
</body>
</html>
