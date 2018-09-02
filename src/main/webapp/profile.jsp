<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/profile.css">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="http://www.w3schools.com/lib/w3data.js"></script>
    <script src="javascript/profile.js"></script>
    <script src="javascript/common.js"></script>
    <title>My profile | C.T.Co Car pooling service</title>
</head>
<body onload="displayMyActiveTrips(); displayUserData()">
<div id="button-go-to-main-page">
    <button type="button" class="btn btn-primary" onclick="goToMainPage()">Go back</button>
</div>
<div id="my-profile-h1">
    <h1>My profile</h1>
</div>
<div id="active-trips-main">
    <h2>My active trips</h2>
    <div class="container" id="active-trip">
        <table class="table table-bordered" id="trips">
            <thead>
            <tr>
                <th>Route</th>
                <th>Places</th>
                <th>Event</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <tr w3-repeat="trips">
                <td>{{from}}-{{to}}</td>
                <td>{{places}}</td>
                <td>{{event}}</td>
                <td class="dropdown">
                    <button onclick="myFunction()" class="dropbtn">...</button>
                    <div id="myDropdown" class="dropdown-content">
                        <a href="#edit">edit</a>
                        <a href="#delete">delete</a>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<div id="profile-info-h2">
    <h2>Profile info:</h2>
    <table id="profile-info">
        <tr>
            <td><b>Name:</b></td>
            <td><p id="p_name" class="text"></p>
            <td><input id="name" name="input" class="w3-hide" type="text"></td>
        </tr>
        <tr>
            <td><b>Surname:</b></td>
            <td><p id="p_surname" class="text"><p>
            <td><input id="surname" name="input" class="w3-hide" type="text"></td>
        </tr>
        <tr>
            <td><b>Phone number:</b></td>
            <td><p id="p_phone" class="text"><p>
            <td><input id="phoneNumber" name="input" class="w3-hide" type="text"></td>
        </tr>
        <tr>
            <td><b>Car model:</b></td>
            <td><p id="p_carmodel" class="text"><p>
            <td><input id="carModel" name="input" class="w3-hide" type="text"></td>
        </tr>
        <tr>
            <td><b>Car color:</b></td>
            <td><p id="p_carcolor" class="text"><p>
            <td><input id="carColor" name="input" class="w3-hide" type="text"></td>
        </tr>
        <tr>
            <td><b>Car number:</b></td>
            <td><p id="p_carnumber" class="text"><p>
            <td><input id="carNumber" name="input" class="w3-hide" type="text"></td>
        </tr>
    </table>
    <td>
        <button id="button-edit" class="btn btn-primary" onclick="editFields()">Edit</button>
    </td>
    <td>
        <button id="button-save" class="btn btn-primary" onclick="submitUserDto()">Save</button>
    </td>
</div>
</body>
</html>