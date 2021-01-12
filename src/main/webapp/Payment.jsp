<%@ page import="com.helman.Entity.Payment" %>
<%@ page import="java.util.List" %>
<%@ page import="com.helman.General.GregorianDate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payment</title>
    <style>
        body {
            background: #c2c2c2;
        }
        input[type=button]{
            font: 18px Helvetica, Arial, sans-serif;
            box-sizing: content-box;
            border: none;
            padding: 8px;
            width: 70px;
            margin-bottom: 10px;
            font-size: 15px;
            cursor: pointer;
        }
        .input {
            font: 15px Helvetica, Arial, sans-serif;
            background-color: #f0f4f6;
            border-style: none;
            width: 130px;
            height: 33px;
        }
        #submit{
            font: 15px Helvetica, Arial, sans-serif;
            box-sizing: content-box;
            border: none;
            padding: 8px;
            width: 130px;
            font-size: 15px;
            cursor: pointer;
        }
        .span{
            font-size: 15px;
        }
        h2 {
            margin: 0 auto 40px;
            color: #fff;
            font: 40px Helvetica;
        }
    </style>
</head>
<body>
<input type="button" value="Home" onclick="location.href='index.jsp';">
<input type="button" value="Add" onclick="location.href='PaymentAdd.jsp';">
<form action="PaymentAct" method="post">
    <span class="span">Customer Number: <input type="number" name="custnum" class="input"></span>
    <span class="span">Check Number: <input type="text" name="checknum" class="input"></span>
    <input type="submit" value="Show Payment" id="submit">
    <input type="hidden" value="read" name="crud">
</form>
    <%String payam = (String) request.getAttribute("message");
    if (payam != null) {%>
        <h2><%=payam%></h2>
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
        <h2>There is no data.</h2>
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
