<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="https://www.w3schools.com/lib/w3.js"></script>
<head>
    <title>Car pooling service</title>
    <meta http-equiv="refresh" content="30" />
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body onload="addRow()">
<h2 style="margin-left: 10%">Active trip</h2>
<div class="container" id="active-trip" onload="scrollBar()" style="border-style:solid; height:55%;  overflow: auto">
    <table class="table table-bordered" id="table-active-trip" >
        <thead>
        <tr>
            <th>No</th>
            <th>Route</th>
            <th>Driver</th>
            <th>Places</th>
            <th>Event</th>
            <th></th>
        </tr>
        <tr  w3-repeat="trip">
            <td>No</td>
            <td>Route</td>
            <td>{{driver}}</td>
            <td>Places</td>
            <td>Event</td>
            <td></td>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script>
    w3.getHttpObject(myFunction);

    function myFunction() {
        w3.displayObject("table table-bordered",getData());
    }
    function getData() {
        fetch("<c:url value='/api/trip/active'/>", {
            "method": "GET",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(function (response) {
            return response.json();
        });
    }

            function scrollBar(){
    var table = document.getElementById("table-active-trip");
    var rows = document.getElementById("table-active-trip").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
    if(rows>13)
    {
        table.add(scrollbar.verticalAlign)
    }
}
</script>
</body>
</html>
