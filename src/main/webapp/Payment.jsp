<%@ page import="com.helman.Entity.Payment" %>
<%@ page import="java.util.List" %>
<%@ page import="com.helman.General.GregorianDate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payment</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        body {
            background: #ddf8ff;
        }
        table{
            border: 3px solid #ccc;
            box-sizing: border-box;
        }
        input[type=number] {
            margin: 10px;
            border: 3px solid #ccc;
            box-sizing: border-box;
        }
        input[type=text] {
            margin: 10px;
            border: 3px solid #ccc;
            box-sizing: border-box;
        }
        #home{
            position: absolute;
            right: 0;
            margin: 5px;
            cursor: pointer;
        }
        #add{
            position: absolute;
            right: 90px;
            margin: 5px;
            cursor: pointer;
        }
        h2 {
            margin: 0 auto 40px;
            font: 40px Helvetica;
            alignment: center;
        }
    </style>
</head>
<body>
<div class="container">
    <form action="PaymentAct" method="post">
        <div>
            <span class="span">Customer Number: <input type="number" name="custnum" class="input"></span>
            <span class="span">Check Number: <input type="text" name="checknum" class="input"></span>
            <input type="submit" value="Show Payment" class="btn btn-info" id="submit">
            <input type="hidden" value="read" name="crud">
            <input type="button" value="Home" id="home" class="btn btn-info" onclick="location.href='index.jsp';">
            <input type="button" value="Add" id="add" class="btn btn-info" onclick="location.href='PaymentAdd.jsp';">
        </div>
    </form>
</div>
    <%String payam = (String) request.getAttribute("message");
    if (payam != null) {%>
        <h2><%=payam%></h2>
<%}%>
<div class="container">
    <table>
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
</div>
</body>
</html>
