<%@ page import="com.helman.Entity.Payment" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payment</title>
</head>
<body>
<form action="PaymentAct" method="post">
    <input type="button" value="HOme" onclick="location.href='index.jsp';">
    <input type="submit" value="Show Payment">
    Customer Number: <input type="number" name="custnum">
    Check Number: <input type="text" name="checknum">
</form>
<table border="1px">
    <tr>
        <td>customerNumber</td>
        <td>checkNumber</td>
        <td>paymentDate</td>
        <td>amount</td>
    </tr>
    <%
        List<Payment> paymentList = (List<Payment>) request.getAttribute("payment");
        if (paymentList == null || paymentList.isEmpty()){
            %>
        <h2 align="center" style="color: darkred">There is no data.</h2>
            <%
        }else{
        for (Payment payment : paymentList){
            if (payment != null){
    %>
    <tr>
        <td><%=payment.getCustomerNumber()%></td>
        <td><%=payment.getCheckNumber()%></td>
        <td><%=payment.getPaymentDate()%></td>
        <td><%=payment.getAmount()%></td>
    </tr>
    <%}}}%>
</table>
</body>
</html>
