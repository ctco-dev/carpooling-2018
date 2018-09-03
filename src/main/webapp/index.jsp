<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<title>C.T.Co Car pooling service</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="css/index.css">
<link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" />
<body>
<div class="bgimg w3-display-container w3-animate-opacity w3-text-white">
    <div class="w3-display-middle">
        <h1 class="w3-jumbo w3-animate-top">Car Pooling Service  </h1>
        <hr class="w3-border-grey" style="margin:auto;width:40%">
        <p class="w3-large w3-center">C.T.Co</p>
    </div>
    <div class="w3-display-bottomleft w3-padding-large">
        <a href="<c:url value='/login.jsp'/>">Log in</a>
    </div>
</div>
</body>
</html>