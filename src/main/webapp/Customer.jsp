<%@ page import="com.helman.Dao.Customerdao" %>
<%@ page import="com.helman.Entity.Customer" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer</title>
</head>
<body>
<input type="button" value="Home" onclick="location.href='index.jsp';">
<input type="button" value="Add" onclick="location.href='CustomerAdd.jsp';">

<form action="CustomerAct" method="post">
    Customer Number: <input type="number" name="custnum">
    <input type="submit" value="Show Customer">
    <input type="hidden" name="crud" value="read">
</form>

<% String payam = (String) request.getAttribute("message");
    if (payam != null){%>
<h2 align="center" style="color: darkred"><%=payam%></h2>
<%}%>
<table border="1px" style="color: darkgreen">
    <tr>
        <td>customerNumber</td>
        <td>customerName</td>
        <td>contactLastName</td>
        <td>contactFirstName</td>
        <td>phone</td>
        <td>addressLine1</td>
        <td>addressLine2</td>
        <td>city</td>
        <td>state</td>
        <td>postalCode</td>
        <td>country</td>
        <td>salesRepEmployeeNumber</td>
        <td>creditLimit</td>
        <td>Delete</td>
        <td>Edit</td>
    </tr>
    <%
        List<Customer> customerList = (List<Customer>) request.getAttribute("customers");
            if (customerList == null){%>
                <h2 align="center" style="color: darkred">There is no data.</h2>
        <%} else{
            for (Customer customer : customerList){
                if (customer != null){%>
    <tr>
        <td><%=customer.getCustomerNumber()%></td>
        <td><%=customer.getCustomerName()%></td>
        <td><%=customer.getContactLastName()%></td>
        <td><%=customer.getContactFirstName()%></td>
        <td><%=customer.getPhone()%></td>
        <td><%=customer.getAddressLine1()%></td>
        <td><%=customer.getAddressLine2()%></td>
        <td><%=customer.getCity()%></td>
        <td><%=customer.getState()%></td>
        <td><%=customer.getPostalCode()%></td>
        <td><%=customer.getCountry()%></td>
        <td><%=customer.getSalesRepEmployeeNumber()%></td>
        <td><%=customer.getCreditLimit()%></td>
        <td><a href="/CustomerAct?custnum=<%=customer.getCustomerNumber()%>&crud=delete">Delete</a></td>
        <td><a href="/CustomerAct?custnum=<%=customer.getCustomerNumber()%>&crud=edit" >Edit</a></td>
    </tr>
    <%}}}%>
</table>
</body>
</html>
