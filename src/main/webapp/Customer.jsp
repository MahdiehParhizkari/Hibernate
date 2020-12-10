<%@ page import="com.helman.Dao.Customerdao" %>
<%@ page import="com.helman.Entity.Customer" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <th>
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
    </th>
    <%
        List<Customer> customerList = (List<Customer>) request.getAttribute("customers");
        for (Customer customer : customerList){
    %>
    <tr>
        <td><%=customer.getCustomerNumber()%></td>
        <td><%=customer.getCustomerNumber()%></td>
        <td><%=customer.getContactLastName()%></td>
        <td><%=customer.getCustomerNumber()%></td>
        <td><%=customer.getPhone()%></td>
        <td><%=customer.getAddressLine1()%></td>
        <td><%=customer.getAddressLine2()%></td>
        <td><%=customer.getCity()%></td>
        <td><%=customer.getState()%></td>
        <td><%=customer.getPostalCode()%></td>
        <td><%=customer.getCountry()%></td>
        <td><%=customer.getSalesRepEmployeeNumber()%></td>
        <td><%=customer.getContactLastName()%></td>
    </tr>
    <%}%>

</table>

</body>
</html>
