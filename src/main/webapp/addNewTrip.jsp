<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new Trip</title>
</head>
<body onload="addOptionValues()">
<div>
    <h2 style="margin-left: 10%">Add new trip:</h2>
    <table id="new-trip" style=" border:0; width: 30%; margin-left:2%; display: inline-block;">
        <tr>
            <td><b>Departure time:</b></td>
            <td><input name="input" type="text"></td>
        </tr>
        <tr>
            <td><b>Start point:</b></td>
            <td><select class="places"></select></td>
        </tr>
        <tr>
            <td><b>Destination:</b></td>
            <td><select class="places"></select></td>
        </tr>
        <tr>
            <td><b>Empty seats:</b></td>
            <td><input name="input" class="w3-hide" type="text"></td>
        </tr>
        <tr>
            <td><b>Event:</b></td>
            <td></td>
        </tr>
    </table>
</div>
<td><button id="button-save" style="margin: 3%">Save</button></td>
<script>
    function addOptionValues() {
       var select = document.getElementsByClassName("places")[0];
        var select1 = document.getElementsByClassName("places")[1];
     //  var select = document.getElementById("places");
        var i=0;
        fetch("<c:url value="/api/trip/places"/>", {
            "method": "GET",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(function (response) {
            return response.json();

        }).then(function (trips) {
            console.log(JSON.stringify(trips));
            trips.forEach(function (c) {
                select.add( new Option( c ) );
                select1.add( new Option( c ) );
            });
        });
    }

</script>
</body>
</html>
