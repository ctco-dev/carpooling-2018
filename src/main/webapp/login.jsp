<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<title>W3.CSS</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script src="http://www.w3schools.com/lib/w3data.js"></script>
<script src="javascript/login.js"></script>
<body>
<header class="w3-container w3-teal">
    <h1>Login or Register</h1>
</header>
<div class="w3-container w3-half w3-margin-top">
    <form class="w3-container w3-card-4">
        <div id="error-panel" class="w3-panel w3-red w3-hide">
            <h3>Error!</h3>
            <p>{{message}}</p>
        </div>
        <p>
            <input id="register-cb" class="w3-check" type="checkbox" onchange="switchRegistration()">
            <label for="register-cb">Registration</label>
        </p>
        <p>
            <input id="username-txt" class="w3-input" type="text" style="width:90%" required>
            <label for="username-txt">Name</label>
        </p>
        <p>
            <input id="password1-txt" class="w3-input" type="password" style="width:90%" required>
            <label for="password1-txt">Password</label>
        </p>
        <p id="password2-grp" class="w3-hide">
            <input id="password2-txt" class="w3-input" type="password" style="width:90%" required>
            <label for="password2-txt">Repeat Password</label>
        </p>
        <p id="name-grp" class="w3-hide">
            <input id="name-txt" class="w3-input" type="text" style="width:90%" required>
            <label for="name-txt">Enter your name</label>
        </p>
        <p id="surname-grp" class="w3-hide">
            <input id="surname-txt" class="w3-input" type="text" style="width:90%" required>
            <label for="surname-txt">Enter your surname</label>
        </p>
        <p id="phoneNumber-grp" class="w3-hide">
            <input id="phoneNumber-txt" class="w3-input" type="text" style="width:90%" required>
            <label for="phoneNumber-txt">Enter your phone number</label>
        </p>
        <p>
            <button id="login-btn" type="button" class="w3-button w3-section w3-teal w3-ripple" onclick="login()">Log
                in
            </button>
            <button id="register-btn" type="button" class="w3-button w3-section w3-teal w3-ripple w3-hide"
                    onclick="register()">Register
            </button>
        </p>
    </form>
</div>
</body>
</html>
