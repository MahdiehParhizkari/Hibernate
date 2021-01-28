<%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 1/26/21
  Time: 4:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orderdetail Report</title>
    <title>Employee Report</title>
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
        .div{
            padding-left: 20px;
        }
        input[type=number] {
            margin: 10px;
            border: 3px solid #ccc;
            box-sizing: border-box;
        }
    </style>
</head>
<div>
    <body>
    <div class="div">
        <br><form action="/OrderdetailAct" method="get">
        Enter OrderNumber: <input type="number" name="ordernumber">
        <input type="submit" value="Report Orderdetail" class="btn btn-info">
        <input type="hidden" name="crud" value="report">
        </form>
        <form action="/OrderdetailAct" method="get">
            Enter OrderNumber: <input type="number" name="ordernumber">
            <input type="submit" value="Factor" class="btn btn-info">
            <input type="hidden" name="crud" value="factor">
            <input type="button" value="Back" class="btn btn-info" onclick="location.href='Orderdetail.jsp';">
        </form>
    </div>
    </body>
</div>
</html>
