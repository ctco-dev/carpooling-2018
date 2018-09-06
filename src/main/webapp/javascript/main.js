function displayActiveTrips() {
    fetch('/api/trip/active', {
        "method": "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    }).then(function (response) {
        return response.json();
    }).then(function (tripsList) {
        console.log(JSON.stringify(tripsList));
        drawTable(tripsList, "trips");
        window.setTimeout(function () {displayActiveTrips(); }, 1000);
    });
}

function drawTable(tripsList, tabId) {
    var table = document.getElementById(tabId);
    var tbody = table.getElementsByTagName('tbody')[0];
    if (tbody) table.removeChild(tbody);

    tbody = document.createElement('tbody');
    tripsList.trips.forEach(function (e) {
        var row = tbody.insertRow();

        var cellInd=0;
        var cell_Id = row.insertCell(cellInd);
        cell_Id.id = "id";
        cell_Id.classList.add("table_id");
        cell_Id.innerHTML = e.id;

        cellInd++;
        var cell_time = row.insertCell(cellInd);
        cell_time.innerHTML = e.time;

        cellInd++;
        var cell_route = row.insertCell(cellInd);
        cell_route.innerHTML = e.from + " - " + e.to;

        cellInd++;
        var cell_driver = row.insertCell(cellInd);
        cell_driver.innerHTML = e.driverInfo;

        cellInd++;
        var cell_phone = row.insertCell(cellInd);
        cell_phone.innerHTML = e.driverPhone;

        cellInd++;
        var cell_freePlaces = row.insertCell(cellInd);
        cell_freePlaces.id = "places";
        cell_freePlaces.classList.add("table_places");
        cell_freePlaces.innerHTML = e.places.toString();

        cellInd++;
        var cell_passengers = row.insertCell(cellInd);
        cell_passengers.id = "passengers";
        cell_passengers.classList.add("table_passengers");
        cell_passengers.innerHTML = drawPassangersList(e.passengers);

        cellInd++;
        var cell_event = row.insertCell(cellInd);
        cell_event.innerHTML = e.event;

        cellInd++;
        var cell_btnForPassenger = row.insertCell(cellInd);
        cell_btnForPassenger.classList.add("table_buttons");
        cell_btnForPassenger.innerHTML = drawTableButton(e.places, e.isADriver, e.hasJoined);
    });
    table.appendChild(tbody);
}

function drawTableButton(freePlaces, isADriver, hasJoined) {
    if (isADriver === true) return "";

    if ((hasJoined === false) && (freePlaces == 0)) return "";

    if (hasJoined === true) return "<button id=\"leave-button\" type=\"button\" class=\"btn btn-primary\"\n" +
        " onclick=\"leave( $(this).closest('tr').find('.table_id').text() )\">\n" +
        "Leave</button>";

    return "<button id=\"join-button\" type=\"button\" class=\"btn btn-primary\"\n" +
        " onclick=\"join($(this).closest('tr').find('.table_id').text(), " +
                        "$(this).closest('tr').find('.table_places').text())\">\n" +
        "&nbsp;Join&nbsp;</button>"
}


function drawPassangersList(passList) {
    var res = ""
    for (var i = 0; i < passList.length; i++) {
        res += '<li>' + passList[i] + '</li>';
    }
    return "<ol>" + res + "</ol>";
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
        .then(function () {
            location.href = "/";
        });
}

function goMyProfile() {
    location.href = "/profile.jsp";
}

function join(tripId, places) {
    if (places <= 0) {
        alert("There are no free places for this trip");
        return;
    }

    fetch('/api/trip/join/' + tripId, {
        "method": "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    }).then(function (response) {
        if (places > 0) {
            if (response.status === 400) {
                alert("You have already joined this trip");
            } else {
                displayActiveTrips();
            }
        }
    });
}

function leave(tripId) {
    fetch('/api/trip/leave/' + tripId, {
        "method": "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    }).then(function (response) {
        if (response.status === 400) {
            alert("You have not joined this trip");
        } else {
            displayActiveTrips();
        }

    });
}

function goAddEvent() {
    location.href = "/addEvent.jsp";
}