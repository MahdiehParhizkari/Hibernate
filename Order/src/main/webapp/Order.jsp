<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="hely" uri="http://helman.com" %>
<html>
<head>
    <title>Order</title>
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
        <form action="orderAct" method="post" class="form-inline">
            <div>
            Order Number: <input type="number" name="ordnum">
            <input type="submit" class="btn btn-info" value="Show Order" id="submit">
            <input type="hidden" value="read" name="crud">
            <input type="button" value="Home" id="home" class="btn btn-info" onclick="location.href='index.jsp';">
            <input type="button" value="Add" id="add" class="btn btn-info" onclick="location.href='OrderAdd.jsp';">
            </div>
        </form>
    </div>
    <c:if test="${requestScope.message ne null}">
        <h2>${requestScope.message}</h2>
    </c:if>
    <div class="container">
        <table class="table table-striped table-bordered">
            <thead class="thead-dark">
            <tr>
                <td>OrderNumber</td>
                <td>OrderDate</td>
                <td>RequiredDate</td>
                <td>ShippedDate</td>
                <td>Status</td>
                <td>Comments</td>
                <td>CustomerNumber</td>
                <td>Delete</td>
                <td>Edit</td>
            </tr>
            </thead>
            <c:if test="${requestScope.Order eq null}">
                <h2>There is no data.</h2>
            </c:if>
            <c:if test="${requestScope.Order ne null}">
                <c:forEach var="order" items="${requestScope.Order}">
                    <tr>
                        <td>${order.orderNumber}</td>
                        <td>${hely:shamsiStr(order.orderDate)}</td>
                        <td>${hely:shamsiStr(order.requiredDate)}</td>
                        <td>${hely:shamsiStr(order.shippedDate)}</td>
                        <td>${order.status}</td>
                        <td>${order.comments}</td>
                        <td>${order.customerNumber}</td>
                        <td><a href="/orderAct?onum=${order.orderNumber}&crud=delete">Delete</a></td>
                        <td><a href="/orderAct?onum=${order.orderNumber}&crud=edit">Edit</a></td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
    </div>
    </body>
</div>
</html>
