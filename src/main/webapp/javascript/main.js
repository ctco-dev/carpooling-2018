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
function join(button, tripId, rowId, places) {
    var data = {};
    places = places - 1;
    var rowCells = document.getElementById("trips").rows[rowId + 1].cells;
    rowCells[4].innerHTML = places;
    data[tripId] = places;
    console.log("===> JSON.stringify(data): " + JSON.stringify(data));
    fetch('/api/trip/' + tripId, {
        "method": "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(function () {
        if (places <= 0) {
            button.disabled = true;
        }
        console.log("DONE");
    });
}