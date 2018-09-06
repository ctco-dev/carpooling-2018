<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main | C.T.Co Car pooling service</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main-style.css">
    <link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico"/>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="http://www.w3schools.com/lib/w3data.js"></script>
    <script src="javascript/main.js"></script>
</head>
<body onload="displayActiveTrips()">
<div id="logout">
    <button type="button" class="btn btn-link" onclick="logout()">Log out</button>
</div>
<div id="go-my-profile">
    <button type="button" class="btn btn-link" onclick="goMyProfile()">My Profile</button>
</div>
<div id="go-add-event">
    <button type="button" class="btn btn-link" onclick="goAddEvent()">Add New Event</button>
</div>
<h2>Active trips</h2>
<div class="container" id="active-trip">
    <table class="table table-bordered" id="trips">
        <thead>
        <tr>
            <!--th>Id</th-->
            <th>Time</th>
            <th>Route</th>
            <th>Driver</th>
            <th>Driver phone</th>
            <th>Places</th>
            <th>Passengers</th>
            <th>Event</th>
            <th></th>
        </tr>
        </thead>
        <tbody></tbody>
    </table>
</div>
<a href="addNewTrip.jsp">
    <button type="button" class="btn btn-primary">Add trip</button>
</a>
<div class="w3-hide">
    <h2>No active trips for your destination. Add your own trip</h2>
    <div class="container" id="passenger-trip">
        <table id="passenger-trips">
            <tr>
                <td>Destination</td>
                <td><input type="text" id="destination"></td>
            </tr>
            <tr>
                <td>Start point</td>
                <td><input type="text" id="start-point"></td>
            </tr>
            <tr>
                <td>Time</td>
                <td><input type="text" id="time"></td>
            </tr>
        </table>
    </div>
    <button type="button" class="btn btn-primary">Save</button>
</div>
</body>
</html>
