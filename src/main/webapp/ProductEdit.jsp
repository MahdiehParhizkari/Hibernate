<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.helman.Entity.Product" %><%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 12/27/20
  Time: 3:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<h2 style="color: darkred">Edit product!</h2>
<form action="ProductAct" method="post">
    <input type="hidden" value="${requestScope.productobj.productCode}" name="procode"><br>
    ProductName: <input type="text" value="${requestScope.productobj.productName}" name="proname"><br>
    ProductLine: <input type="text" value="${requestScope.productobj.productLine}" name="proline"><br>
    ProductScale: <input type="text" value="${requestScope.productobj.productScale}" name="proscale"><br>
    ProductVendor: <input type="text" value="${requestScope.productobj.productVendor}" name="provendor"><br>
    ProductDescription: <input type="text" value="${requestScope.productobj.productDescription}" name="prodesc"><br>
    QuantityInStock: <input type="number" value="${requestScope.productobj.quantityInStock}" name="qins"><br>
    BuyPrice: <input type="number" value="${requestScope.productobj.buyPrice}" name="buyprice"><br>
    MSRP: <input type="number" value="${requestScope.productobj.MSRP}" name="msrp"><br><br>
    <input type="submit" value="Update">
    <input type="hidden" value="update" name="crud">
</form>
</body>
</html>
