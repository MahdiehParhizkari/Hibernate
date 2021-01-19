<%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 1/9/21
  Time: 3:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
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
            margin-top: 100px;
        }
        h2 {
            font: 50px Helvetica;
            margin-left: 10px;
            margin-bottom: 10px;
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
    </style>
</head>
<body>
<h2>Please Login</h2>
<form action="Dispatcher" method="post">
    <table id="table">
        <tr>
            <td id="un">UserName: </td>
            <td><input id="uname" type="text" placeholder="UserName" name="username" class="inp"></td>
        </tr>
        <tr>
            <td id="ps">Password: </td>
            <td><input type="password" placeholder="Password" name="password" id="psw" class="inp"></td>
        </tr>
        <tr>
            <td><input type="checkbox" onclick="myFunction()" id="chkbox" class="inp"></td>
            <td id="sp">Show Password</td>
        </tr>
        <tr>
            <td><input type="hidden" name="crud" value="into"></td>
            <td><input type="submit" value="Login" class="btn btn-info"></td>
        </tr>
    </table>
</form>
<script>
    /*$(function() {
        $('input').on('change', function() {
        var input = $(this);
        if (input.val().length) {input.addClass('populated');}
        else{input.removeClass('populated');}
        });
        setTimeout(function() {
        $('#uname').trigger('focus');
        }, 500);
    });*/
    function myFunction(){
        var passwordInput = document.getElementById("psw");
        var checkBox = document.getElementById("chkbox").checked;
        if (checkBox==true) {
            passwordInput.type = "text";
        }else{
            passwordInput.type="password";
        }
    }
</script>
</body>
</html>