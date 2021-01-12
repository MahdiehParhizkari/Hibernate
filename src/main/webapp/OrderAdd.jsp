<%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 12/20/20
  Time: 5:12 AM
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
     body {
         background: #c2c2c2;
     }
    h2 {
        margin: 0 auto 40px;
        color: #fff;
        font: 40px Helvetica;
    }
    td{
        font: 15px Helvetica, Arial, sans-serif;
        padding: 5px 10px;
    }
    .inp{
        height: 30px;
        weight: 80px;
    }
    input[type=submit] {
        font: 18px Helvetica, Arial, sans-serif;
        box-sizing: border-box;
        border: none;
        height: 40px;
        width: 100px;
        font-size: 15px;
        cursor: pointer;
    }
    input[type=button] {
        font: 18px Helvetica, Arial, sans-serif;
        box-sizing: border-box;
        border: none;
        height: 40px;
        width: 100px;
        font-size: 15px;
        cursor: pointer;
    }
</style>
<head>
    <title>Add</title>
</head>
<body>
<h2>Add new Order:</h2>
<form action="orderAct" method="post">
    <table>
        <tr>
            <td>OrderNumber:</td>
            <td><input type="number" name="onum" class="inp"></td>
        </tr>
        <tr>
            <td>OrderDate:</td>
            <td>
                <span id="display_area_2" style="cursor: pointer;" class="display_area">Click Here</span>
                <input id="date_input_8" type="hidden" name="odate"  class="inp"/>
                <script type="text/javascript">
                    Calendar.setup({
                        inputField     : "date_input_8",   // id of the input field
                        displayArea    : "display_area_2",
                        ifFormat       : "%Y-%m-%d",       // format of the input field
                        dateType	   : 'jalali',
                        ifDateType	   : 'gregorian',
                        weekNumbers    : false
                    });
                </script>
            </td>
        </tr>
        <tr>
            <td>RequiredDate:</td>
            <td>
                <span id="display_area_1" style="cursor: pointer;" class="display_area">Click Here</span>
                <input id="date_input_7" type="hidden" name="rdate"  class="inp"/>
                <script type="text/javascript">
                    Calendar.setup({
                        inputField     : "date_input_7",   // id of the input field
                        displayArea    : "display_area_1",
                        ifFormat       : "%Y-%m-%d",       // format of the input field
                        dateType	   : 'jalali',
                        ifDateType	   : 'gregorian',
                        weekNumbers    : false
                    });
                </script>
            </td>
        </tr>
        <tr>
            <td>ShippedDate:</td>
            <td><span id="display_area_3" style="cursor: pointer;" class="display_area">Click Here</span>
                <input id="date_input_9" type="hidden" name="sdate"  class="inp"/>
                <script type="text/javascript">
                    Calendar.setup({
                        inputField     : "date_input_9",   // id of the input field
                        displayArea    : "display_area_3",
                        ifFormat       : "%Y-%m-%d",       // format of the input field
                        dateType	   : 'jalali',
                        ifDateType	   : 'gregorian',
                        weekNumbers    : false
                    });
                </script></td>
        </tr>
        <tr>
            <td>Status:</td>
            <td><input type="text" name="status" class="inp"></td>
        </tr>
        <tr>
            <td>Comments:</td>
            <td><input type="text" name="com" class="inp"></td>
        </tr>
        <tr>
            <td>CustomerNumber:</td>
            <td><input type="number" name="custnum" class="inp"></td>
        </tr>
    </table>
    <input type="hidden" name="crud" value="add">
    <input type="submit" value="Add">
    <input type="button" value="Back" onclick="location.href='Order.jsp';">
</form>
</body>
</html>
