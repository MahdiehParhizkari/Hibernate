<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.helman.Entity.Office" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 12/16/20
  Time: 6:31 AM
  To change this template use File | Settings | File Templates.
--%>
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
<h2>Edit the office: </h2>
<form action="OfficeAct" method="post">
    <%Office office = (Office) request.getAttribute("offobjct");%>
    <table>
        <tr>
            <td><input type="hidden" value="<c:out value="${requestScope.offobjct.officeCode}"/>" name="offcode"></td>
        </tr>
        <tr>
            <td>City:</td>
            <td><input value="<c:out value="${requestScope.offobjct.city}"/>" type="text" name="city"></td>
        </tr>
        <tr>
            <td>Phone:</td>
            <td><input value="<c:out value="${requestScope.offobjct.phone}"/>" type="text" name="phone"></td>
        </tr>
        <tr>
            <td>AddressLine1:</td>
            <td><input value="<c:out value="${requestScope.offobjct.addressLine1}"/>" type="text" name="addl1"></td>
        </tr>
        <tr>
            <td>AddressLine2:</td>
            <td><input value="<c:out value="${requestScope.offobjct.addressLine2}"/>" type="text" name="addl2"></td>
        </tr>
        <tr>
            <td>State:</td>
            <td><input value="<c:out value="${requestScope.offobjct.state}"/>" type="text" name="state"></td>
        </tr>
        <tr>
            <td>Country:</td>
            <td><input value="<c:out value="${requestScope.offobjct.country}"/>" type="text" name="coun"></td>
        </tr>
        <tr>
            <td>PostalCode:</td>
            <td><input value="<c:out value="${requestScope.offobjct.postalCode}"/>" type="text" name="pcode"></td>
        </tr>
        <tr>
            <td>Territory:</td>
            <td><input value="<c:out value="${requestScope.offobjct.territory}"/>" type="text" name="ter"></td>
        </tr>
    </table>
    <input type="hidden" value="update" name="crud">
    <input type="submit" value="Update">
    <input type="button" value="Back" onclick="location.href='Office.jsp';">

</form>
</body>
</html>
