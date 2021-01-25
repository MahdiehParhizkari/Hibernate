<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Customer</title>
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
        <form action="CustomerAct" method="post" class="form-inline">
            <div>
                Customer Number: <input type="number" name="custnum" >
                <input type="submit" class="btn btn-info" value="Show Customer" id="submit">
                <input type="hidden" name="crud" value="read">
                <input type="button" value="Home" class="btn btn-info" id="home" onclick="location.href='index.jsp';">
                <input type="button" value="Add" class="btn btn-info" id="add" onclick="location.href='CustomerAdd.jsp';">
            </div>
        </form>
        <form action="CustomerAct" method="get" class="form-inline">
            <div>
                <input type="submit" class="btn btn-info" value="Report">
                <input type="hidden" name="crud" value="report">
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
                <td>customerNumber</td>
                <td>customerName</td>
                <td>contactLastName</td>
                <td>contactFirstName</td>
                <td>phone</td>
                <td>addressLine1</td>
                <td>addressLine2</td>
                <td>city</td>
                <td>state</td>
                <td>postalCode</td>
                <td>country</td>
                <td>salesRepEmployeeNumber</td>
                <td>creditLimit</td>
                <td>Delete</td>
                <td>Edit</td>
            </tr>
            </thead>
            <c:if test="${requestScope.customers eq null}">
                <h2>There is no data.</h2>
            </c:if>
            <c:if test="${requestScope.customers ne null}">
                <c:forEach var="customer" items="${requestScope.customers}">
                    <!--for(Customer customer :customers)-->
                    <tr>
                        <td>${customer.customerNumber}</td>
                        <td>${customer.customerName}</td>
                        <td>${customer.contactLastName}</td>
                        <td>${customer.contactFirstName}</td>
                        <td>${customer.phone}</td>
                        <td>${customer.addressLine1}</td>
                        <td>${customer.addressLine2}</td>
                        <td>${customer.city}</td>
                        <td>${customer.state}</td>
                        <td>${customer.postalCode}</td>
                        <td>${customer.country}</td>
                        <td>${customer.salesRepEmployeeNumber}</td>
                        <td>${customer.creditLimit}</td>
                        <td><a href="/CustomerAct?custnum=${customer.customerNumber}&crud=delete">Delete</a></td>
                        <td><a href="/CustomerAct?custnum=${customer.customerNumber}&crud=edit" >Edit</a></td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
    </div>
    </body>
</div>
</html>