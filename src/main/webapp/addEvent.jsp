<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Event</title>
    <link rel="stylesheet" href="css/addEvent.css">
    <script src="javascript/addEvent.js"></script>
</head>
<body onload="addOptionValues(),showUsers()">
<div id="adding">
    <form id="newEvent" method="post">
        <p><b>Name of Event</b></p>
        <p><textarea name="name" id="name"></textarea></p>
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

<div id="myEventTable">
    <table id="events">
        <tr>
            <th>Event Name</th>
            <th>Event Date</th>
            <th>Event Time</th>
        </tr>
    </table>

</div>

</body>
</html>
