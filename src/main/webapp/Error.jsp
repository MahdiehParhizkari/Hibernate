<%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 12/26/20
  Time: 1:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
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
            letter-spacing: 3px;
        }
        input {
        }
        input:hover{
            font-size: 150%;
            cursor: pointer;
        }
    </style>
</head>
<body>
<h1>Username or password is wrong.</h1>
<input type="button" value="Back" class="btn btn-info" onclick="location.href='Login.jsp';">
</body>
</html>
