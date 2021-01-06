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
    <title>Test</title>
    <style>
        body {
            background: #35dc9b;
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
        }
        input {
            font: 18px Helvetica, Arial, sans-serif;
            box-sizing: border-box;
            display: block;
            border: none;
            padding: 20px;
            width: 250px;
            margin-bottom: 20px;
            font-size: 18px;
            outline: none;
            transition: all 0.2s ease-in-out;
        }
        input[type="submit"] {
            transition: all 0.2s ease-in-out;
            font: 20px Helvetica, Arial, sans-serif;
            border: none;
            background: #1aaf75;
            color: #fff;
            padding: 16px 30px;
            cursor: pointer;
        }
        #un, #ps {
            font: 20px Helvetica, Arial, sans-serif;
            padding: 16px 40px;
        }
        #psw{
            font: 20px Helvetica, Arial, sans-serif;
            padding-right: 10px;
        }
        #sp{
            padding-bottom: 20px;
            padding-right: 10px;
        }
        #chkbox{
            cursor: pointer;
        }
    </style>
</head>
<body>
<h1>Enter to order app</h1>
<form action="UserAct" method="post">
    <table id="table">
        <tr>
            <td id="un">UserName: </td>
            <td><input id="uname" type="text" placeholder="UserName" name="username"></td>
        </tr>
        <tr>
            <td id="ps">Password: </td>
            <td><input type="password" placeholder="Password" name="password" id="psw"></td>
        </tr>
        <tr>
            <td><input type="checkbox" onclick="myFunction()" id="chkbox"></td>
            <td id="sp">Show Password</td>
        </tr>
        <tr>
            <td><input type="hidden" name="crud" value="into"></td>
            <td><input type="submit" value="Login"></td>
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
