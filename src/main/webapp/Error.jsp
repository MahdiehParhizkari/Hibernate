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
    <style>
        body {
            background: #c2c2c2;
            display: flex;
            flex-direction: column;
            flex-grow: 0;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }
        h1 {
            margin: 0 auto 40px;
            color: #fff;
            font: 40px Helvetica;
            text-transform: uppercase;
            letter-spacing: 3px;
        }
        input {
            transition: all 0.2s ease-in-out;
            font: 20px Helvetica, Arial, sans-serif;
            border: none;
            background: #1aaf75;
            color: #fff;
            padding: 16px 30px;
        }
        input:hover{
            font-size: 150%;
            cursor: pointer;
        }
    </style>
</head>
<body>
<h1>Username or password is wrong.</h1>
<input type="button" value="Back" onclick="location.href='Login.jsp';">
</body>
</html>
