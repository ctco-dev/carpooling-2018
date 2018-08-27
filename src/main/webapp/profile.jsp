<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="http://www.w3schools.com/lib/w3data.js"></script>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>My profile</title>
    <style>
        #profile-info td {
            padding: 2%;
        }
        .dropbtn {
            background-color: #3498DB;
            color: white;
            font-size: 16px;
            border: none;
            cursor: pointer;
        }
        .dropbtn:hover, .dropbtn:focus {
            background-color: #2980B9;
        }
        .dropdown {
            position: relative;
        }
        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f1f1f1;
            min-width: 70px;
            overflow: auto;
            box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
            z-index: 1;
        }
        .dropdown-content a {
            color: black;
            padding: 5px 10px;
            text-decoration: none;
            display: block;
        }
        .dropdown a:hover {
            background-color: #ddd;
        }
        .show {
            display: block;
        }
    </style>
</head>
<body onload="displayMyActiveTrips()">
<div style="height: 10%; border-bottom: solid #1C91D8 3pt">
    <h1>My profile</h1>
</div>
<div style="width:65%;height: 90%; border-right:solid #1C91D8 3pt; float: left;">
    <h2 style="margin-left: 10%">My active trips</h2>
    <div class="container" id="active-trip"
         style="border-style:solid; height:55%;  overflow: auto; margin-left: 5%; width: 90%">
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
<div>
    <h2 style="margin-left: 10%">Profile info:</h2>
    <table id="profile-info" style="border:0; width: 30%; margin-top:2%; margin-left:2%; display: inline-block;">
        <tr>
            <td><b>User name:</b></td>
            <td><p class="text">test
                <p></p>
            <td><input name="input" class="w3-hide" type="text"></td>
        </tr>
        <tr>
            <td><b>Name Surname:</b></td>
            <td><p class="text">test1
                <p>
            <td><input name="input" class="w3-hide" type="text"></td>
        </tr>
        <tr>
            <td><b>Phone number:</b></td>
            <td><p class="text">test2
                <p>
            <td><input name="input" class="w3-hide" type="text"></td>
        </tr>
        <tr>
            <td><b>Car info (Color, model):</b></td>
            <td><p class="text">test3
                <p>
            <td><input name="input" class="w3-hide" type="text"></td>
        </tr>
        <tr>
            <td><b>Car number:</b></td>
            <td><p class="text">test4
                <p>
            <td><input name="input" class="w3-hide" type="text"></td>
        </tr>
        <tr>
            <td><b>Password:</b></td>
            <td><p class="text">test5
                <p>
            <td><input name="input" class="w3-hide" type="text"></td>
        </tr>
    </table>
    <td>
        <button id="button-edit" style="margin-left: 2%" onclick="editField()">Edit</button>
    </td>
    <td>
        <button id="button-save" style="margin-left: 1%">Save</button>
    </td>
</div>
<script>
    function editField() {
        var inputs = document.querySelectorAll('input[name=input]');
        var text = document.querySelectorAll('p.text');
        var innerHTML = [];
        var i = 0;
        text.forEach(function (txt) {
            txt.classList.add("w3-hide");
            innerHTML.push(txt.innerHTML);
        });
        inputs.forEach(function (inpt) {
            inpt.classList.remove("w3-hide");
            inpt.value = innerHTML[i];
            i++;
        });
    }
    function myFunction() {
        document.getElementById("myDropdown").classList.toggle("show");
    }
    window.onclick = function (event) {
        if (!event.target.matches('.dropbtn')) {

            var dropdowns = document.getElementsByClassName("dropdown-content");
            var i;
            for (i = 0; i < dropdowns.length; i++) {
                var openDropdown = dropdowns[i];
                if (openDropdown.classList.contains('show')) {
                    openDropdown.classList.remove('show');
                }
            }
        }
    }
    function displayMyActiveTrips() {
        fetch("<c:url value="/api/trip/active"/>", {
            "method": "GET",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(function (response) {
            return response.json();

        }).then(function (trips) {
            console.log(JSON.stringify(trips));
            w3DisplayData("trips", trips);
        });
    }
</script>
</body>
</html>