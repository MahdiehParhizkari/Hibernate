<%@ page import="com.helman.Entity.Payment" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payment</title>
</head>
<body>
<body>
<form action="PaymentAct" method="post">
    <input type="" >
</form>
<table>
    <th>
        <td>customerNumber</td>
        <td>checkNumber</td>
        <td>paymentDate</td>
        <td>amount</td>
    </th>
    <%
        List<Payment> paymentList = (List<Payment>) request.getAttribute("payment");
        for (Payment payment : paymentList){
    %>
    <tr>
        <td><%=payment.getCustomerNumber()%></td>
        <td><%=payment.getCheckNumber()%></td>
        <td><%=payment.getPaymentDate()%></td>
        <td><%=payment.getAmount()%></td>
    </tr>
    <%}%>
</table>
</body>
</html>
