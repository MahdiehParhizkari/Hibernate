<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Product</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        .all{
            position: absolute;
            left:0px;
            width: 100%;
            background-repeat: no-repeat;
            background-size: contain;
        }
        body {
            background: #ddf8ff;
        }
        input[type=number] {
            margin: 10px;
            border: 3px solid #ccc;
            box-sizing: border-box;
        }
        #home{
            position: absolute;
            right: 0;
            margin: 5px;
            cursor: pointer;
        }
        #add{
            position: absolute;
            right: 90px;
            margin: 5px;
            cursor: pointer;
        }
        .table{
            border: 3px solid #ccc;
            box-sizing: border-box;
        }
    </style>
</head>
<div class="all">
    <body>
    <br><div class="container">
        <form action="ProductAct" method="post" class="form-inline">
            <div>
                Product Code: <input type="text" name="procode">
                <input type="submit" value="Show Product" id="submit" class="btn btn-info">
                <input type="hidden" value="read" name="crud">
                <input type="button" value="Home" id="home" class="btn btn-info" onclick="location.href='index.jsp';">
                <input type="button" value="Add" id="add" class="btn btn-info" onclick="location.href='ProductAdd.jsp';">
            </div>
        </form>
    </div>

    <c:if test="${requestScope.message ne null}">
        <h2><c:out value="${requestScope.message}"/></h2>
    </c:if>
    <div class="container">
        <table class="table table-striped table-bordered">
            <thead class="thead-dark">
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
            </thead>
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
    </div>
    </body>
</div>
</html>
