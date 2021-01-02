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
</head>
<body>
<h2 style="color: darkred">Add new product!</h2>
<form action="ProductAct" method="post">
    ProductCode: <input type="text" name="procode"><br>
    ProductName: <input type="text" name="proname"><br>
    ProductLine: <input type="text" name="proline"><br>
    ProductScale: <input type="text" name="proscale"><br>
    ProductVendor: <input type="text" name="provendor"><br>
    ProductDescription: <input type="text" name="prodesc"><br>
    QuantityInStock: <input type="number" name="quanstock"><br>
    BuyPrice: <input type="number" step="0.01" name="buyp"><br>
    MSRP: <input type="number" step="0.01" name="msrp"><br><br>
    <input type="submit" value="Add">
    <input type="hidden" value="add" name="crud">
</form>
</body>
</html>
