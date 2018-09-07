<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Event</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script> $dateJQ = jQuery.noConflict(true); </script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-timepicker/1.10.0/jquery.timepicker.js"></script>
    <script src="javascript/addEvent.js"></script>
    <script src="javascript/common.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-timepicker/1.10.0/jquery.timepicker.css"
          crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/addEvent.css">
</head>
<body onload="addOptionValues();showUsers();showMyEvents();">
<div id="button-go-to-main-page">
    <button type="button" class="btn btn-primary" onclick="goToMainPage()">Go back</button>
</div>
<table id="mainTable">
    <tr>
        <th class="mainTableHeader"><p id="tableHeader">My Events</p></th>
        <th class="mainTableHeader"><p id="formHeader">Add New Event</p></th>
    </tr>
    <tr>
        <td name="myEventTable" >
            <div id="myEventTable" >
                <table id="events">
                    <thead>
                    <tr>
                        <th>Event Name</th>
                        <th>Event Date</th>
                        <th>Event Time</th>
                        <th>Event Place</th>
                        <th> &nbsp; </th>
                    </tr>
                    </thead>
                    <tbody></tbody>
                </table>
            </div>
        </td>

        <td width="400px">
            <div id="newEvent">
                <form method="post">
                    <p><b>Name of Event</b></p>
                    <p><textarea name="name" id="name" rows="3" cols="20"></textarea></p>
                    <p><b>Date</b></p>
                    <p><input type="text" id="datepicker"></p>
                    <p><b>Time</b></p>
                    <p><input type="text" id="timepicker"></p>
                    <p><b>Place</b></p>
                    <p><select id="place" class="place"></select></p>
                    <p><b>Participants</b></p>
                    <p><select id="participants" class="users"></select>
                        <input type="button" onclick="AddUser();" value="+"></p>
                    <p><ol id="userList" ></ol></p>
                </form>
                <button id="addEvent" class="btn btn-primary" onclick="buildEventDto()">Add</button>
            </div>
        </td>
    </tr>
</table>
<script>
    $(function () {
        $dateJQ("#datepicker").datepicker({dateFormat: 'dd.mm.yy'});
        $("#timepicker").timepicker({timeFormat: 'H:i', show2400: true, step: 15});
    });
</script>
</body>
</html>
