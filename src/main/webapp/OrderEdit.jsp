<%@ page import="com.helman.Entity.Order" %><%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 12/20/20
  Time: 7:32 AM
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
<h2 style="color: #045ea1">Edit order!</h2>
<form action="orderAct" method="post">
    <%Order ord = (Order) request.getAttribute("orderobj");%>
    <input type="hidden" value="<%=ord.getOrderNumber()%>" name="ordernum"><br>
    OrderDate: <span id="display_area_2" style="cursor: pointer;" class="display_area">Click Here</span>
    <input id="date_input_8" type="hidden" name="odate" />
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
    RequiredDate: <span id="display_area_1" style="cursor: pointer;" class="display_area">Click Here</span>
    <input id="date_input_7" type="hidden" name="rdate" />
    <script type="text/javascript">
        Calendar.setup({
            inputField     : "date_input_7",   // id of the input field
            displayArea    : "display_area_1",
            ifFormat       : "%Y-%m-%d",       // format of the input field
            dateType	   : 'jalali',
            ifDateType	   : 'gregorian',
            weekNumbers    : false
        });
    </script><br>
    ShippedDate: <span id="display_area_3" style="cursor: pointer;" class="display_area">Click Here</span>
    <input id="date_input_9" type="hidden" name="sdate" />
    <script type="text/javascript">
        Calendar.setup({
            inputField     : "date_input_9",   // id of the input field
            displayArea    : "display_area_3",
            ifFormat       : "%Y-%m-%d",       // format of the input field
            dateType	   : 'jalali',
            ifDateType	   : 'gregorian',
            weekNumbers    : false
        });
    </script><br>
    Status: <input value="<%=ord.getStatus()%>" type="text" name="status"><br>
    Comments: <input value="<%=ord.getComments()%>" type="text" name="com"><br>
    CustomerNumber: <input value="<%=ord.getCustomerNumber()%>" type="number" name="cusnum"><br><br>
    <input type="submit" value="Udate">
    <input type="hidden" value="update" name="crud">
</form>

</body>
</html>
