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
</head>
<body>
<h2>Add new orderdetail: </h2>
<form action="/OrderdetailAct" method="post">
    OrderNumber: <input type="number" name="ordnum"><br>
    ProductCode: <input type="text" name="procode"><br>
    QuantityOrdered: <input type="number" name="qord"><br>
    PriceEach: <input type="number" name="pe"><br>
    OrderLineNumber: <input type="number" name="oln"><br><br>
    <input type="submit" value="Add">
    <input type="hidden" name="crud" value="add">
</form>
</body>
</html>
