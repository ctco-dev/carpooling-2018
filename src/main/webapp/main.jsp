<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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
            <th>Driver info</th>
            <th>Places</th>
            <th>Event</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script>
function addRow () {
    var table = document.getElementById("table-active-trip");

    fetch("<c:url value='/api/trip/active'/>", {
        "method": "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    }).then(function (response) {
        return response.json();
    }).then(function (trip) {
        console.log(JSON.stringify(trip));
        var row = table.insertRow(i);
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        var cell3 = row.insertCell(2);
        var cell4 = row.insertCell(3);
        var cell5 = row.insertCell(4);
        var cell6 = row.insertCell(5);
        cell1.innerHTML = "NEW CELL1";
        cell2.innerHTML = "NEW CELL2";
        cell3.innerHTML = ;
        cell4.innerHTML = "NEW CELL4";
        cell5.innerHTML = "NEW CELL5";
        cell6.innerHTML = "NEW CELL6";
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
