<%--
  Created by IntelliJ IDEA.
  User: gatis.frishfelds01
  Date: 8/23/2018
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Title</title>
</head>
<body>
    <div style="height: 10%; border-bottom: solid #1C91D8 3pt">
        <h1>My profile</h1>
    </div>
            <div style="width:65%;height: 90%; border-right:solid #1C91D8 3pt; float: left;">
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
                            <td><button>...</button></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div>
                <table id="passenger-trips" style="border:0; width: 30%; margin-top:2%; padding-left: 2%">
                    <tr>
                        <td><b>User name:</b></td>
                        <td>test<td><input id="input-user-name" type="text" ></td>
                    </tr>
                    <tr>
                        <td><b>Name Surname:</b></td>
                        <td>test<td><input id="input-name-surname" type="text"></td>
                    </tr>
                </table>
                <td><button id="button-user-name" onclick="editField()">Edit</button></td>
            </div>
<script>
    function editField() {
        document.getElementById("input-user-info").classList.add("w3-hide");
    }
</script>
</body>
</html>
