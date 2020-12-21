<%@ page import="com.helman.Entity.Customer" %><%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 12/15/20
  Time: 2:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Edit customer:</h2>
<form action="CustomerAct" method="post">
    <%Customer customer = (Customer) request.getAttribute("cust");%>
    <input type="hidden" value="<%=customer.getCustomerNumber()%>" name="custnum">
    CustomerName: <input value="<%=customer.getCustomerName()%>" type="number" name="custname"><br>
    ContactLastName: <input value="<%=customer.getContactLastName()%>" type="text" name="conlname"><br>
    ContactFirstName: <input value="<%=customer.getContactFirstName()%>" type="text" name="confname" ><br>
    Phone: <input value="<%=customer.getPhone()%>" type="text" name="phone"><br>
    AddressLine1: <input value="<%=customer.getAddressLine1()%>" type="text" name="addl1"><br>
    AddressLine2: <input value="<%=customer.getAddressLine2()%>" type="text" name="addl2"><br>
    City: <input value="<%=customer.getCity()%>" type="text" name="city"><br>
    State: <input value="<%=customer.getState()%>" type="text" name="state"><br>
    PostalCode: <input value="<%=customer.getPostalCode()%>" type="text" name="c"><br>
    Country: <input value="<%=customer.getCountry()%>" type="text" name="count"><br>
    SalesRepEmployeeNumber: <input value="<%=customer.getSalesRepEmployeeNumber()%>" type="number" name="srempnum"><br>
    CreditLimit: <input value="<%=customer.getCreditLimit()%>" type="number" name="credlim"><br><br>
    <input type="submit" value="Update">
    <input type="hidden" name="crud" value="update">
</form>
</body>
</html>
