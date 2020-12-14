<%@ page import="com.helman.Entity.Office" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Office</title>
</head>
<body>
<form action="OfficeAct" method="Post">
    <input type="button" value="Home" onclick="location.href='index.jsp';">
    <input type="submit" value="Show Office">
    Office Code: <input type="number" name="offcode">
</form>
<table border="1px">
    <tr>
        <td>officeCode</td>
        <td>city</td>
        <td>phone</td>
        <td>addressLine1</td>
        <td>addressLine2</td>
        <td>state</td>
        <td>country</td>
        <td>postalCode</td>
        <td>territory</td>
    </tr>
    <%
        List<Office> officeList = (List<Office>) request.getAttribute("Offices");
        if (officeList == null){
            %>
            <h2 align="center" style="color: #8b0000">There is no data.</h2>
    <%} else{
        for (Office office : officeList){
            if (office != null){
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
    <%}}}%>
</table>
</body>
</html>
