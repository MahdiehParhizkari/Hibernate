<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.helman.Entity.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Product</title>
</head>
<body>
<input type="button" value="Home" onclick="location.href='index.jsp';">
<input type="button" value="Add" onclick="location.href='ProductAdd.jsp';">
<form action="ProductAct" method="post">
    Product Code: <input type="text" name="procode">
    <input type="submit" value="Show Product">
    <input type="hidden" value="read" name="crud">
</form>

<c:if test="${requestScope.message ne null}">
    <h2 align="center" style="color: darkred"><c:out value="${requestScope.message}"/></h2>
</c:if>

<table border="1px">
    <tr>
        <td>productCode</td>
        <td>productName</td>
        <td>productLine</td>
        <td>productScale</td>
        <td>productVendor</td>
        <td>productDescription</td>
        <td>quantityInStock</td>
        <td>buyPrice</td>
        <td>MSRP</td>
        <td>Delete</td>
        <td>Edit</td>
    </tr>

    <c:if test="${requestScope.product == null}">
        <h2 align="center" style="color: darkred">There is no data.</h2>
    </c:if>
    <c:if test="${requestScope.product != null}">
        <c:forEach var="product" items="${requestScope.product}">
    <tr>
        <td><c:out value="${product.productCode}"/></td>
        <td><c:out value="${product.productName}"/></td>
        <td><c:out value="${product.productLine}"/></td>
        <td><c:out value="${product.productScale}"/></td>
        <td><c:out value="${product.productVendor}"/></td>
        <td><c:out value="${product.productDescription}"/></td>
        <td><c:out value="${product.quantityInStock}"/></td>
        <td><c:out value="${product.buyPrice}"/></td>
        <td><c:out value="${product.MSRP}"/></td>
        <td><a href="/ProductAct?procode=${product.productCode}&crud=delete">Delete</a></td>
        <td><a href="/ProductAct?productcode=${product.productCode}&crud=edit">Edit</a></td>
    </tr>
        </c:forEach>
    </c:if>
</table>
</body>
</html>
