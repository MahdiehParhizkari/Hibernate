<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.helman.Entity.Orderdetail" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 12/21/20
  Time: 11:04 AM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Edit</title>
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
        h2 {
            font: 40px Helvetica;
            margin-left: 10px;
        }
        td{
            font: 15px Helvetica, Arial, sans-serif;
            padding: 5px 10px;
        }
        .inp{
            height: 30px;
            weight: 80px;
            border: 3px solid #ccc;
            box-sizing: border-box;
        }
        input[type=submit]{
            font: 15px Helvetica, Arial, sans-serif;
            box-sizing: border-box;
            border: none;
            height: 40px;
            width: 100px;
            font-size: 15px;
            cursor: pointer;
        }
        input[type=button]{
            font: 15px Helvetica, Arial, sans-serif;
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
<h2>Edit orderdetail:</h2>
<form action="OrderdetailAct" method="post">
    <%Orderdetail orderd = (Orderdetail) request.getAttribute("orderdetail");%>
    <table>
        <tr>
            <td><input type="hidden" value="<c:out value="${requestScope.orderdetail.orderNumber}"/>" name="ordnum"></td>
            <td><input type="hidden" value="<c:out value="${requestScope.orderdetail.productCode}"/>" name="procode"></td>
        </tr>
        <tr>
            <td>QuantityOrdered:</td>
            <td><input type="number" value="<c:out value="${requestScope.orderdetail.quantityOrdered}"/>" name="quanord" class="inp"></td>
        </tr>
        <tr>
            <td>PriceEach:</td>
            <td><input type="number" value="<c:out value="${requestScope.orderdetail.priceEach}"/>" name="peach" class="inp"></td>
        </tr>
        <tr>
            <td>OrderLineNumber:</td>
            <td><input type="number" value="<c:out value="${requestScope.orderdetail.orderLineNumber}"/>" name="ordlnum" class="inp"></td>
        </tr>
    </table>
    <input type="hidden" value="update" name="crud">
    <input type="submit" value="Update" class="btn btn-info">
    <input type="button" value="Back" class="btn btn-info" onclick="location.href='Orderdetail.jsp';">
</form>
</body>
</html>
