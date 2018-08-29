<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
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
<td><button id="button-save" onclick="saveTrip()" style="margin: 3%">Save</button></td>
<script>
    function addOptionValues() {
       var select = document.getElementsByClassName("places")[0];
        var select1 = document.getElementsByClassName("places")[1];
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
            trips.forEach(function (c) {
                select.add( new Option( c ) );
                select1.add( new Option( c ) );
            });
        });
    }
    function addEventfields(){
        var yesChkBox= document.getElementById("yes");
        var noChkBox=document.getElementById("no");
        if(yesChkBox)
        {
            document.getElementById('event-name').classList.remove("w3-hide");
            document.getElementById('event-status').classList.remove("w3-hide");
            document.getElementById('participants').classList.remove("w3-hide");
        }
        if(noChkBox)
        {
            noChkBox.checked=false;
        }
    }
    function removeEventfields() {
        var yesChkBox= document.getElementById("yes");
        var noChkBox=document.getElementById("no");
        if(noChkBox)
        {
            document.getElementById('event-name').classList.add("w3-hide");
            document.getElementById('event-status').classList.add("w3-hide");
            document.getElementById('participants').classList.add("w3-hide");
        }
        if(yesChkBox)
        {
            yesChkBox.checked=false;
        }
    }


    function saveTrip() {
        var departure =document.getElementById("departure")
        var departureTime =document.getElementById("departure-time")
        var destination=document.getElementById("destination")
        var places=document.getElementById("places")
        var dto = {
            "departure": departure.options[departure.selectedIndex].value,
            "destination":destination.options[destination.selectedIndex].value,
            "places":places.value,
            "departureTime": departureTime.value,
            "isEvent":"false",
            "tripStatus":"ACTIVE"
        };
        console.log("sending data");
        console.log(JSON.stringify(dto))
        fetch("<c:url value='/api/trip/create'/>", {
            "method": "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dto)
        }).then(function (response)
        {
            location.href = "<c:url value='/main.jsp'/>";
        })
    }
</script>
</body>
</html>
