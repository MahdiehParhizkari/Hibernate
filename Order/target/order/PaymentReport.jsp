<%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 1/26/21
  Time: 1:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
        body {
            background: #ddf8ff;
        }
        .div{
            padding-left: 20px;
        }
        input[type=number] {
            margin: 10px;
            border: 3px solid #ccc;
            box-sizing: border-box;
        }
    </style>
</head>
<div>
    <body>
    <div class="div">
        <br><form action="PaymentAct" method="get">
        Enter date start from: <span id="display_area_1" style="cursor: pointer;" class="display_area">Choose Date</span>
        <input id="date_input_1" type="hidden" name="fromdate"/>
        <script type="text/javascript">
            Calendar.setup({
                inputField     : "date_input_1",   // id of the input field
                displayArea    : "display_area_1",
                ifFormat       : "%Y-%m-%d",       // format of the input field
                dateType	   : 'jalali',
                ifDateType	   : 'gregorian',
                weekNumbers    : false
            });
        </script> to <span id="display_area_2" style="cursor: pointer;" class="display_area">Choose Date</span>
        <input id="date_input_2" type="hidden" name="todate"/>
        <script type="text/javascript">
            Calendar.setup({
                inputField     : "date_input_2",   // id of the input field
                displayArea    : "display_area_2",
                ifFormat       : "%Y-%m-%d",       // format of the input field
                dateType	   : 'jalali',
                ifDateType	   : 'gregorian',
                weekNumbers    : false
            });
        </script>
        <input type="submit" value="Report Payment" class="btn btn-info">
        <input type="hidden" name="crud" value="report">
    </form>
        <input type="button" value="Back" class="btn btn-info" onclick="location.href='Employee.jsp';">
    </div>
    </body>
</div>
</html>