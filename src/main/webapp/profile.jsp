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
                    </tr>
                </table>
            </div>
</body>
</html>
