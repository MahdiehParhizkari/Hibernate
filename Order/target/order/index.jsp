<%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 1/4/21
  Time: 4:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Select</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        body {
            background: #ddf8ff;
            display: flex;
            flex-direction: column;
            flex-grow: 0;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }
        h1 {
            margin: 0 auto 40px;
            font: 40px Helvetica;
            text-transform: uppercase;
        }
        input {
            font: 18px Helvetica, Arial, sans-serif;
            box-sizing: border-box;
            display: block;
            border: none;
            padding: 20px;
            width: 100px;
            margin-bottom: 20px;
            font-size: 18px;
            outline: none;
            transition: all 0.2s ease-in-out;
            cursor: pointer;
        }
        span{
            margin: 0 auto 40px;
            font: 20px Helvetica;
            padding-bottom: 20px;
        }
        select{
            font: 18px Helvetica, Arial, sans-serif;
            box-sizing: border-box;
            display: block;
            border: none;
            padding: 20px;
            width: 350px;
            margin-bottom: 20px;
            font-size: 18px;
            outline: none;
            transition: all 0.2s ease-in-out;
        }
    </style>
</head>
<body>
<div>
    <h1>Enter to order app</h1>
    <span>Select a form:</span>
    <div>
        <select name="entity" id="selectentity" class="form-control">
            <option value="Customer.jsp">ShowCustomers</option>
            <option value="Employee.jsp">ShowEmployees</option>
            <option value="Office.jsp">ShowOffices</option>
            <option value="Order.jsp">ShowOrders</option>
            <option value="Orderdetail.jsp">ShowOrderdetails</option>
            <option value="Payment.jsp">ShowPayments</option>
            <option value="Product.jsp" selected="Product.jsp">ShowProducts</option>
            <option value="Productline.jsp">ShowProductlines</option>
            <option value="User.jsp">ShowUsers</option>
        </select>
    </div>
</div>
<div class="container" align="center">
    <form action="Dispatcher" method="post">
        <input value="ok" type="button" class="btn btn-info" onclick="location.href=document.getElementById('selectentity').value">
        <input type="hidden" name="crud" value="logout">
        <input type="submit" class="btn btn-info" value="Logout">
    </form>
</div>
</body>
</html>