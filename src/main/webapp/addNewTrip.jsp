<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="css/addNewTrip.css">
    <script src="javascript/addNewTrip.js"></script>
    <title>Add new Trip</title>
</head>
<body onload="addOptionValues()">
<div>
    <h2>Add new trip:</h2>
    <table id="new-trip" style=" border:0; width: 30%; margin-left:2%; display: inline-block;">
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
            <td><input type="checkbox" id="yes" value="yes" onclick="addEventfields()">Yes<br>
                <input type="checkbox" id="no" value="no" onclick="removeEventfields()">No<br></td>
        </tr>
        <tr id="event-name" class="w3-hide">
            <td ><b>Event name:</b></td>
            <td><input name="input" type="text"></td>
        </tr>
        <tr id="event-status" class="w3-hide">
            <td ><b>Event start (time and date):</b></td>
            <td><input name="input" type="text"></td>
        </tr>
        <tr id="participants" class="w3-hide">
            <td ><b>Participants of the event:</b></td>
            <td><input name="input" type="text"></td>
        </tr>
    </table>
</div>
<td><button id="button-save" onclick="saveTrip()">Save</button></td>
</body>
</html>
