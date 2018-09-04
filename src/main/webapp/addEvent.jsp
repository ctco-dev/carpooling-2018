<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Event</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-timepicker/1.10.0/jquery.timepicker.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-timepicker/1.10.0/jquery.timepicker.css" crossorigin="anonymous">
    <link rel="stylesheet" href="css/addEvent.css">
    <script src="javascript/addEvent.js"></script>
</head>
<body onload="addOptionValues(),showUsers(),showMyEvents()">

<p id="tableHeader">My Events</p>
<div id="myEventTable" class="container">
    <table id="events">
        <thead>
        <tr>
            <th>Event Name</th>
            <th>Event Date</th>
            <th>Event Time</th>
            <th>Event Place</th>
        </tr>
        </thead>
    </table>

</div>

<p id="formHeader">Add New Event</p>
<div id="newEvent">
    <form  method="post">
        <p><b>Name of Event</b></p>
        <p><textarea name="name" id="name" rows="3" cols="20"></textarea></p>
        <p><b>Date</b></p>
        <p><input type="text" id="datepicker"></p>
        <p><b>Time</b></p>
        <p><input type="text" id="timepicker"></p>
        <p><b>Place</b></p>
        <p><select id="place" class="place"></select></p>
        <p><b>Participants</b></p>
        <p><select id="participants" class="users"></select></p>
    </form>
    <button id="addEvent" onclick="buildEventDto()">Add</button>
</div>

<script>
    $( function() {
        $( "#datepicker" ).datepicker({ dateFormat: 'dd.mm.yy' });
        $("#timepicker").timepicker({ timeFormat: 'H:i', show2400: true, step: 15 });
    } );
</script>
</body>
</html>
