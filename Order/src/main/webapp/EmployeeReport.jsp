<%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 1/25/21
  Time: 3:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
        <br><form action="/EmployeeAct" method="get">
            Enter Employee Number: <input type="number" name="empNumber">
            <input type="submit" value="Report Employee" class="btn btn-info">
            <input type="hidden" name="crud" value="report">
        </form>
        <input type="button" value="Back" class="btn btn-info" onclick="location.href='Employee.jsp';">
    </div>
    </body>
</div>
</html>
