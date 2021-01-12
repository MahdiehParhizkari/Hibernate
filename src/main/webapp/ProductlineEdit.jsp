<%@ page import="com.helman.Entity.Productline" %>
<%@ page import="java.util.Base64" %><%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 12/17/20
  Time: 4:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
    <style>
        body {
            background: #c2c2c2;
        }
        h2 {
            margin: 0 auto 40px;
            color: #fff;
            font: 40px Helvetica;
        }
        td{
            font: 15px Helvetica, Arial, sans-serif;
            padding: 5px 10px;
        }
        .inp{
            height: 30px;
            weight: 80px;
        }
        input[type=submit]{
            font: 15px Helvetica, Arial, sans-serif;
            box-sizing: border-box;
            border: none;
            height: 40px;
            width: 100px;
            font-size: 15px;
            cursor: pointer;
        }
        input[type=button]{
            font: 15px Helvetica, Arial, sans-serif;
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
<h2>Edit productline:</h2>
<form action="ProductlineAct" method="post" enctype="multipart/form-data">
    <%Productline productline =(Productline) request.getAttribute("prolobj");%>
    <table>
        <tr>
            <td><input type="hidden" value="<%=productline.getProductLine()%>" name="pline" class="inp"></td>
        </tr>
        <tr>
            <td>TextDescription:</td>
            <td><input value="<%=productline.getTextDescription()%>" type="text"  name="tdesc" class="inp"></td>
        </tr>
        <tr>
            <td>HtmlDescription:</td>
            <td><input value="<%=productline.getHtmlDescription()%>" type="text" name="hdesc" class="inp"></td>
        </tr>
        <tr>
            <td>Image:</td>
            <td><img src="data:image/jpg+jpeg+png+gif;base64,<%=Base64.getEncoder().encodeToString(productline.getImage())%>" width="200" height="200" class="inp"></td>
        </tr>
        <tr>
            <td>Change Image:</td>
            <td><input type="file" name="img" size="50" class="inp"></td>
        </tr>
    </table>
    <input type="submit" value="Update">
    <input type="hidden" name="crud" value="update">
    <input type="button" value="Back" onclick="location.href='Productline.jsp';">

</form>
</body>
</html>
