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
    CustomerName: <input value="<%=customer.getCustomerName()%>" name="custname"><br>
    ContactLastName: <input value="<%=customer.getContactLastName()%>" name="conlname"><br>
    ContactFirstName: <input value="<%=customer.getContactFirstName()%>" name="confname" ><br>
    Phone: <input value="<%=customer.getPhone()%>" name="phone"><br>
    AddressLine1: <input value="<%=customer.getAddressLine1()%>" name="addl1"><br>
    AddressLine2: <input value="<%=customer.getAddressLine2()%>" name="addl2"><br>
    City: <input value="<%=customer.getCity()%>" name="city"><br>
    State: <input value="<%=customer.getState()%>" name="state"><br>
    PostalCode: <input value="<%=customer.getPostalCode()%>" name="c"><br>
    Country: <input value="<%=customer.getCountry()%>" name="count"><br>
    SalesRepEmployeeNumber: <input value="<%=customer.getSalesRepEmployeeNumber()%>" name="srempnum"><br>
    CreditLimit: <input value="<%=customer.getCreditLimit()%>" name="credlim"><br><br>
    <input type="submit" value="Update">
    <input type="hidden" name="crud" value="update">
</form>
</body>
</html>
