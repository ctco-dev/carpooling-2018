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
<body onload="displayActiveTrips()">
<h2 style="margin-left: 10%">Active trips</h2>
<div class="container" id="active-trip" onload="scrollBar()" style="border-style:solid; height:55%;  overflow: auto">
    <table class="table table-bordered" id="trips">
        <thead>
        <tr>
            <th>No</th>
            <th>Route</th>
            <th>Driver</th>
            <th>Places</th>
            <th>Event</th>
            <th></th>
        </tr>
        <tr w3-repeat="trips">
            <td>No</td>
            <td>{{from}}-{{to}}</td>
            <td>{{driver}}</td>
            <td>{{places}}</td>
            <td>{{event}}</td>
            <td></td>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script>
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
