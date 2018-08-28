<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Car pooling service</title>
    <meta http-equiv="refresh" content="30"/>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="http://www.w3schools.com/lib/w3data.js"></script>
    <script src="javascript/main.js"></script>
</head>
<body onload="displayActiveTrips()">
<div id="logout">
    <button type="button" onclick="logout()">Log out</button>
</div>
<div id="go-my-profile">
    <button type="button" onclick="goMyProfile()">My Profile</button>
</div>
<h2>Active trips</h2>
<div class="container" id="active-trip">
    <table class="table table-bordered" id="trips">
        <thead>
        <tr>
            <th>Id</th>
            <th>Route</th>
            <th>Driver</th>
            <th>Driver phone</th>
            <th>Places</th>
            <th>Event</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <tr w3-repeat="trips">
            <td class="table_id" id="id">{{id}}</td>
            <td>{{from}}-{{to}}</td>
            <td>{{driverInfo}}</td>
            <td>{{driverPhone}}</td>
            <td class="table_places" id="places">{{places}}</td>
            <td>{{event}}</td>
            <td>
                <button type="button" class="btn btn-warning" onclick="join(this)">Join</button>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<button>Add trip</button>
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
<button>Save</button>
<script>
    var places;
    function join(button) {
        var data = {};
        places = $(button).closest('tr').find('.table_places').text() - 1;
        var tripId = $(button).closest('tr').find('.table_id').text();
        var id = $(button).closest('td').parent().index();
        var x = document.getElementById("trips").rows[id + 1].cells;
        x[3].innerHTML = places;
        data[tripId] = places;
        console.log("===> JSON.stringify(data): " + JSON.stringify(data));
        fetch("<c:url value='/api/trip'/>/" + tripId, {
            "method": "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        }).then(function () {
            console.log("DONE");
        });
    }
</script>
</body>
</html>
