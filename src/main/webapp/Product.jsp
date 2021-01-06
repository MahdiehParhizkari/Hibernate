<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.helman.Entity.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Product</title>
    <style>
        body {
            background: #35dc9b;
        }
        input[type=button]{
            font: 18px Helvetica, Arial, sans-serif;
            box-sizing: content-box;
            border: none;
            padding: 8px;
            width: 70px;
            margin-bottom: 10px;
            font-size: 15px;
            cursor: pointer;
        }
        input[type=text] {
            font: 15px Helvetica, Arial, sans-serif;
            background-color: #f0f4f6;
            border-style: none;
            width: 130px;
            height: 33px;
        }
        #submit{
            font: 15px Helvetica, Arial, sans-serif;
            box-sizing: content-box;
            border: none;
            padding: 8px;
            width: 130px;
            font-size: 15px;
            cursor: pointer;
        }
        .span{
            font-size: 15px;
        }
        h2 {
            margin: 0 auto 40px;
            color: #fff;
            font: 40px Helvetica;
        }
    </style>
</head>
<body>
<input type="button" value="Home" onclick="location.href='index.jsp';">
<input type="button" value="Add" onclick="location.href='ProductAdd.jsp';">
<form action="ProductAct" method="post">
    <span class="span">Product Code: <input type="text" name="procode"></span>
    <span class="span"><input type="submit" value="Show Product" id="submit"></span>
    <input type="hidden" value="read" name="crud">
</form>

<c:if test="${requestScope.message ne null}">
    <h2><c:out value="${requestScope.message}"/></h2>
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
        <h2>There is no data.</h2>
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
