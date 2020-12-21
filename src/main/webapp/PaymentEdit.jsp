<%@ page import="com.helman.Entity.Payment" %><%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 12/19/20
  Time: 4:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" media="all" href="/statics/JalaliJSCalendar-master/skins/calendar-blue.css" title="winter" />
<script type="text/javascript" src="/statics/JalaliJSCalendar-master/jalali.js"></script>
<script type="text/javascript" src="/statics/JalaliJSCalendar-master/calendar.js"></script>
<script type="text/javascript" src="/statics/JalaliJSCalendar-master/calendar-setup.js"></script>
<script type="text/javascript" src="/statics/JalaliJSCalendar-master/lang/calendar-fa.js"></script>
<!-- helper script that uses the calendar -->
<script type="text/javascript">
    var oldLink = null;
    // code to change the active stylesheet
    function setActiveStyleSheet(link, title) {
        var i, a, main;
        for(i=0; (a = document.getElementsByTagName("link")[i]); i++) {
            if(a.getAttribute("rel").indexOf("style") != -1 && a.getAttribute("title")) {
                a.disabled = true;
                if(a.getAttribute("title") == title) a.disabled = false;
            }
        }
        if (oldLink) oldLink.style.fontWeight = 'normal';
        oldLink = link;
        link.style.fontWeight = 'bold';
        return false;
    }
</script>
<style type="text/css">
    .calendar {
        direction: rtl;
    }
    #flat_calendar_1, #flat_calendar_2{
        width: 200px;
    }
    #flat_calendar_3{
        width: 230px;
    }
    .example {
        padding: 10px;
    }
    .display_area {
        background-color: #FFFF88
    }
</style>
<head>
    <title>Edit</title>
</head>
<body>
<h2 style="color: #045ea1">Edit payment!</h2>
<form action="PaymentAct" method="Post">
    <%Payment payment = (Payment) request.getAttribute("pay");%>
    <input type="hidden" value="<%=payment.getCustomerNumber()%>" name="custnum"><br>
    <input type="hidden" value="<%=payment.getCheckNumber()%>" name="checknum"><br>
    PaymentDate:<span id="display_area_2" style="cursor: pointer;" class="display_area">Click Here</span>
        <input id="date_input_8" type="hidden" name="pdate" />
    <script type="text/javascript">
        Calendar.setup({
            inputField     : "date_input_8",   // id of the input field
            displayArea    : "display_area_2",
            ifFormat       : "%Y-%m-%d",       // format of the input field
            dateType	   : 'jalali',
            ifDateType	   : 'gregorian',
            weekNumbers    : false
        });
    </script><br>
    Amount: <input value="<%=payment.getAmount()%>" type="number" name="amount"><br><br>
    <input type="submit" value="Update">
    <input type="hidden"  name="crud" value="update">
</form>
</body>
</html>
