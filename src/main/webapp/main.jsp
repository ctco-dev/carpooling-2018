<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Car pooling service</title>
    <meta http-equiv="refresh" content="30"/>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="http://www.w3schools.com/lib/w3data.js"></script>
</head>
<body onload="displayActiveTrips(),rowCounter()">
<h2 style="margin-left: 10%">Active trips</h2>
    <div class="container" id="active-trip" style="border-style:solid; height:55%;  overflow: auto; margin-left: 5%; width: 90%">
    <table class="table table-bordered" id="trips">
        <thead>
        <tr>
            <th>Route</th>
            <th>Driver</th>
            <th>Places</th>
            <th>Event</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <tr w3-repeat="trips">
            <td>{{from}}-{{to}}</td>
            <td>{{driver}}</td>
            <td>{{places}}</td>
            <td>{{event}}</td>
            <td><button>Join</button></td>
        </tr>
        </tbody>
    </table>
        <button>Add trip</button>
</div>
<h2 style="margin-left: 10%">No active trips for your destination. Add your own trip</h2>
<div class="container" id="passenger-trip" style="border-style:solid; margin-left: 5%; width: 90%">
    <table id="passenger-trips" style="border:0; width:50%">
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
<button style="margin-left: 5%; margin-top: 1%">Save</button>
<script>
    var j=0;
    function displayActiveTrips() {
        fetch("<c:url value="/api/trip/active"/>", {
            "method": "GET",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(function (response) {
            return response.json();

        }).then(function (trips) {
            console.log(JSON.stringify(trips));
            w3DisplayData("trips", trips);
        });
    }
    function scrollBar() {
        var table = document.getElementById("table-active-trip");
        var rows = document.getElementById("table-active-trip").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
        if (rows > 13) {
            table.add(scrollbar.verticalAlign)
        }
    }
</script>
</body>
</html>
