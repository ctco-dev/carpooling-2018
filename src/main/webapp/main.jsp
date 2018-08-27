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
<h2>Active trips</h2>
<div class="container" id="active-trip">
    <table class="table table-bordered" id="trips">
        <thead>
        <tr>
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
            <td>{{from}}-{{to}}</td>
            <td>{{driverInfo}}</td>
            <td>{{driverPhone}}</td>
            <td class="table_places" id="places">{{places}}</td>
            <td>{{event}}</td>
            <td>
                <button type="button" class="btn btn-warning" onclick="reduceFreePlaces()">Join</button>
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
    function reduceFreePlaces() {
        var places;
        $('#trips tbody button.btn.btn-warning').on('click', function () {
            console.log($(this));
            places = $(this).closest('tr').find('.table_places').text();
            console.log("Places = " + places);
            updatePlaces(places - 1);
        });
    }
    function updatePlaces(places) {
        $('td').click(function () {
            console.log($(this));
            var row_index = $(this).parent().index();
            var col_index = $(this).index();
            console.log(row_index);
            console.log(col_index);
            var x = document.getElementById("trips").rows[row_index + 1].cells;
            x[3].innerHTML = places;
        });
    }
</script>
</body>
</html>
