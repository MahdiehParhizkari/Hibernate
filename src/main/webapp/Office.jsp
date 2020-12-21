<%@ page import="com.helman.Entity.Office" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Office</title>
</head>
<body>
<input type="button" value="Home" onclick="location.href='index.jsp';">
<input type="button" value="Add" onclick="location.href='OfficeAdd.jsp';">
<form action="OfficeAct" method="Post">
    Office Code: <input type="number" name="offcode">
    <input type="submit" value="Show Office">
</form>
    <%String payam = (String) request.getAttribute("message");
    if(payam !=null){%>
    <h2 align="center" style="color: #8b0000"><%=payam%></h2>
    <%}%>
<table border="1px">
    <tr>
        <td>OfficeCode</td>
        <td>City</td>
        <td>Phone</td>
        <td>AddressLine1</td>
        <td>AddressLine2</td>
        <td>State</td>
        <td>Country</td>
        <td>PostalCode</td>
        <td>Territory</td>
        <td>Delete</td>
        <td>Edit</td>
    </tr>
        <%List<Office> officeList = (List<Office>) request.getAttribute("Offices");
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
        <td><a href="/OfficeAct?offcode=<%=office.getOfficeCode()%>&crud=delete">Delete</a></td>
        <td><a href="/OfficeAct?offcode=<%=office.getOfficeCode()%>&crud=edit">Edit</a></td>
    </tr>
    <%}}}%>
</table>
</body>
</html>
