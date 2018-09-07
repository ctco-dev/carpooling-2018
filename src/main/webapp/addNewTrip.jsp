<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="css/addNewTrip.css">
    <link rel="stylesheet" href="css/common.css">
    <link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" />
    <script src="javascript/addNewTrip.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="http://www.w3schools.com/lib/w3data.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-timepicker/1.10.0/jquery.timepicker.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-timepicker/1.10.0/jquery.timepicker.css"
    <script src="javascript/common.js"></script>
    <title>Add new Trip</title>
</head>
<body onload="addOptionValues()">
<div id="button-go-to-main-page">
    <button type="button" class="btn btn-link" onclick="goToMainPage()">Go back</button>
</div>
<div>
    <h2>Add new trip:</h2>
    <table id="new-trip">
        <tr>
            <td><b>Departure time:</b></td>
            <td><input id="departure-time" name="input" type="text"></td>
        </tr>
        <tr>
            <td><b>Start point:</b></td>
            <td><select id="departure" class="places"></select></td>
        </tr>
        <tr>
            <td><b>Destination:</b></td>
            <td><select id="destination" class="places"></select></td>
        </tr>
        <tr>
            <td><b>Empty seats:</b></td>
            <td><input id="places" name="input" type="text"></td>
        </tr>
        <tr>
            <td><b>Event:</b></td>
            <td><input type="radio" id="yes" name="isEvent" onchange="addRemoveEvent()" n> Yes<br>
                <input type="radio" id="no" name="isEvent" onchange="addRemoveEvent()">No<br></td>
        </tr>
        <tr id="event-name" class="w3-hide" >
            <td ><b>Event name:</b></td>
            <td><select onchange="showEventInfo()" name="input" id="name" class="event-name" type="text"></select></td>
        </tr>
        <tr id="event-time" class="w3-hide">
            <td ><b>Event start date and time:</b></td>
            <td><input name="input" id="time" type="text"></td>
        </tr>
    </table>
</div>
<td><button id="button-save" onclick="tripdto()">Save</button></td>

<script>
    $(function () {
        $("#departure-time").timepicker({timeFormat: 'H:i', show2400: true, step: 15});
    });
</script>
</body>
</html>
