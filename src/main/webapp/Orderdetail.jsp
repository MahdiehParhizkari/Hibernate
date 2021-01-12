<%@ page import="com.helman.Entity.Orderdetail" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Orderdetail</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        body {
            background: #ddf8ff;
        }
        table{
            border: 3px solid #ccc;
            box-sizing: border-box;
        }
        input[type=number] {
            margin: 10px;
            border: 3px solid #ccc;
            box-sizing: border-box;
        }
        input[type=text]{
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
        h2 {
            margin: 0 auto 40px;
            font: 40px Helvetica;
            alignment: center;
        }
    </style>
</head>
<body>
<br><div class="container">
    <form action="OrderdetailAct" method="post">
        <div>
            <span class="span">Order Number: <input type="number" name="ordnum" class="input"></span>
            <span class="span">Product Code: <input type="text" name="procode" class="input"></span>
            <input type="submit" class="btn btn-info" value="Show Orderdetail" id="submit">
            <input type="hidden" name="crud" value="read">
            <input type="button" value="Home" id="home" class="btn btn-info" onclick="location.href='index.jsp';">
            <input type="button" value="Add" id="add" class="btn btn-info" onclick="location.href='OrderdetailAdd.jsp';">
        </div>
    </form>
</div>
<c:if test="${requestScope.message ne null}">
    <h2><c:out value="${requestScope.message}"/></h2>
</c:if>
<div class="container">
    <table border="1px">
        <tr>
            <td>OrderNumber</td>
            <td>ProductCode</td>
            <td>QuantityOrdered</td>
            <td>PriceEach</td>
            <td>OrderLineNumber</td>
            <td>Delete</td>
            <td>Edit</td>
        </tr>
        <c:if test="${requestScope.orderdetail eq null}">
            <h2>There is no data.</h2>
        </c:if>
        <c:if test="${requestScope.orderdetail ne null}">
            <c:forEach var="orderdetail" items="${requestScope.orderdetail}">
                <tr>
                    <td><c:out value="${orderdetail.orderNumber}"/></td>
                    <td><c:out value="${orderdetail.productCode}"/></td>
                    <td><c:out value="${orderdetail.quantityOrdered}"/></td>
                    <td><c:out value="${orderdetail.priceEach}"/></td>
                    <td><c:out value="${orderdetail.orderLineNumber}"/></td>
                    <td><a href="/OrderdetailAct?onum=<c:out value="${orderdetail.orderNumber}"/>%>&pcode=<c:out value="${orderdetail.productCode}"/>&crud=delete">Delete</a></td>
                    <td><a href="/OrderdetailAct?onum=<c:out value="${orderdetail.orderNumber}"/>&pcode=<c:out value="${orderdetail.productCode}"/>&crud=edit">Edit</a></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</div>
</body>
</html>