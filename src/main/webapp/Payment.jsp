<%@ page import="com.helman.Entity.Payment" %>
<%@ page import="java.util.List" %>
<%@ page import="com.helman.General.GregorianDate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payment</title>
</head>
<body>
<input type="button" value="Home" onclick="location.href='index.jsp';">
<input type="button" value="Add" onclick="location.href='PaymentAdd.jsp';">
<form action="PaymentAct" method="post">
    Customer Number: <input type="number" name="custnum">
    Check Number: <input type="text" name="checknum">
    <input type="submit" value="Show Payment">
    <input type="hidden" value="read" name="crud">
</form>
    <%String payam = (String) request.getAttribute("message");
    if (payam != null) {%>
        <h2 align="center" style="color: darkred"><%=payam%></h2>
<%}%>
<table border="1px">
    <tr>
        <td>CustomerNumber</td>
        <td>CheckNumber</td>
        <td>PaymentDate</td>
        <td>Amount</td>
        <td>Delete</td>
        <td>Edit</td>
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
        <td><%=GregorianDate.shamsiStr(GregorianDate.miladi2shamsi(payment.getPaymentDate()))%></td>
        <td><%=payment.getAmount()%></td>
        <td><a href="/PaymentAct?custnum=<%=payment.getCustomerNumber()%>&checknum=<%=payment.getCheckNumber()%>&crud=delete">Delete</a></td>
        <td><a href="/PaymentAct?custnum=<%=payment.getCustomerNumber()%>&checknum=<%=payment.getCheckNumber()%>&crud=edit">Edit</a></td>
    </tr>
    <%}}}%>
</table>
</body>
</html>
