function displayActiveTrips() {
    fetch('/api/trip/active', {
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
function scrollBar() {
    var table = document.getElementById("table-active-trip");
    var rows = document.getElementById("table-active-trip").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
    if (rows > 13) {
        table.add(scrollbar.verticalAlign)
    }
}
function logout() {
    fetch('/api/auth/logout', {"method": "POST"})
        .then(function (response) {
            location.href = "/";
        });
}
function goMyProfile() {
    location.href = "/profile.jsp";
}