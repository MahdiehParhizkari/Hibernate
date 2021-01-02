<%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 12/23/20
  Time: 10:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2 style="color: #442222">Welcome to order!</h2>
<form action="UserAct" method="post">
    Username: <input type="text" name="username"><br>
    Password: <input type="password" name="password" id="passwd"><br>
    <input type="checkbox" onclick="myFunction()" id="chkbox">Show password<br><br>
    <input type="submit" value="Login">
    <input type="hidden" value="into" name="crud">
</form>
<script>
    function myFunction(){
        var passwordInput = document.getElementById("passwd");
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
