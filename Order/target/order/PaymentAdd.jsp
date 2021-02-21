<%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 12/19/20
  Time: 2:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
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
<style>
    body {
        background: #ddf8ff;
    }
    h2 {
        font: 40px Helvetica;
        margin-left: 10px;
    }
    td{
        font: 15px Helvetica, Arial, sans-serif;
        padding: 5px 10px;
    }
    .inp{
        height: 30px;
        weight: 80px;
        border: 3px solid #ccc;
        box-sizing: border-box;
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
<h2>Add a new payment:</h2>
<form action="PaymentAct" method="post">
    <table>
        <tr>
            <td>CustomerNumber:</td>
            <td><input type="number" name="custnum" class="inp"></td>
        </tr>
        <tr>
            <td>CheckNumber:</td>
            <td><input type="text" name="checknum" class="inp"></td>
        </tr>
        <tr>
            <td>PaymentDate:</td>
            <td>
                <span id="display_area_2" style="cursor: pointer;" class="display_area">Click Here</span>
                <input id="date_input_8" type="hidden" name="pdate" class="inp">
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
            <td>Amount:</td>
            <td><input type="text" name="amount" class="inp"></td>
        </tr>
    </table>
    <input type="submit" name="crud" value="Add" class="btn btn-info">
    <input type="hidden" value="add">
    <input type="button" value="Back" class="btn btn-info" onclick="location.href='Payment.jsp';">
</form>

</body>
</html>
