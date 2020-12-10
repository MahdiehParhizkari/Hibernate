<%@ page import="com.helman.Entity.Office" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <th>
    <td>officeCode</td>
    <td>city</td>
    <td>phone</td>
    <td>addressLine1</td>
    <td>addressLine2</td>
    <td>state</td>
    <td>country</td>
    <td>postalCode</td>
    <td>territory</td>
    </th>
    <%
        List<Office> officeList = (List<Office>) request.getAttribute("offices");
        for (Office office : officeList){
    %>
    <tr>
        <td><%=office.getOfficeCode()%></td>
        <td><%=office.getCity()%></td>
        <td><%=office.getPhone()%></td>
        <td><%=office.getAddressLine1()%></td>
        <td><%=office.getAddressLine2()%></td>
        <td><%=office.getState()%></td>
        <td><%=office.getCountry()%></td>
        <td><%=office.getPostalCode()%></td>
        <td><%=office.getTerritory()%></td>
    </tr>
    <%}%>
</table>
</body>
</html>
