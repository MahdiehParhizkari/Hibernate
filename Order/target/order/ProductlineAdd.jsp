<%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 12/17/20
  Time: 3:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add</title>
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
        input[type=submit] {
            font: 18px Helvetica, Arial, sans-serif;
            box-sizing: border-box;
            border: none;
            height: 40px;
            width: 100px;
            font-size: 15px;
            cursor: pointer;
        }
        input[type=button] {
            font: 18px Helvetica, Arial, sans-serif;
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
<h2>Add new Productline:</h2>
<form action="ProductlineAct" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td>ProductLine:</td>
            <td><input type="text" name="prol"class="inp"></td>
        </tr>
        <tr>
            <td>TextDescription:</td>
            <td><input type="text" name="tdesc"class="inp"></td>
        </tr>
        <tr>
            <td>HtmlDescription:</td>
            <td><input type="text" name="hdesc"class="inp"></td>
        </tr>
        <tr>
            <td>Image:</td>
            <td><input type="file" name="img" size="50"class="inp"></td>
        </tr>
    </table>
    <input type="hidden" name="crud" value="create">
    <input type="submit" value="Add" class="btn btn-info">
    <input type="button" value="Back" class="btn btn-info" onclick="location.href='Productline.jsp';">
</form>
</body>
</html>
