<%@ page import="com.helman.Entity.Office" %><%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 12/16/20
  Time: 6:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<h2 style="color: darkgreen">Edit the office: </h2>
<form action="OfficeAct" method="post">
    <%Office office = (Office) request.getAttribute("offobjct");%>
    <input type="hidden" value="<%=office.getOfficeCode()%>" name="offcode">
    City: <input value="<%=office.getCity()%>" type="text" name="city"><br>
    Phone: <input value="<%=office.getPhone()%>" type="text" name="phone"><br>
    AddressLine1: <input value="<%=office.getAddressLine1()%>" type="text" name="addl1"><br>
    AddressLine2: <input value="<%=office.getAddressLine2()%>" type="text" name="addl2"><br>
    State: <input value="<%=office.getState()%>" type="text" name="state"><br>
    Country: <input value="<%=office.getCountry()%>" type="text" name="coun"><br>
    PostalCode: <input value="<%=office.getPostalCode()%>" type="text" name="pcode"><br>
    Territory: <input value="<%=office.getTerritory()%>" type="text" name="ter"><br><br>
    <input type="submit" value="Update">
    <input type="hidden" value="update" name="crud">
</form>
</body>
</html>
