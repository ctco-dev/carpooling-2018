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
        var cell1 = row.insertCell(0);
        cell1.id = "id";
        cell1.classList.add("table_id");
        cell1.innerHTML = e.id;

        var cell2 = row.insertCell(1);
        cell2.innerHTML = e.from + " - " + e.to;

        var cell3 = row.insertCell(2);
        cell3.innerHTML = e.driverInfo;

        var cell4 = row.insertCell(3);
        cell4.innerHTML = e.driverPhone;

        var cell5 = row.insertCell(4);
        cell5.id = "places";
        cell5.classList.add("table_places");
        cell5.innerHTML = e.places.toString();

        var cell6 = row.insertCell(5);
        cell6.id = "passengers";
        cell6.classList.add("table_passengers");
        cell6.innerHTML = drawPassangersList(e.passengers);

        var cell7 = row.insertCell(6);
        cell7.innerHTML = e.event;

        var cell8 = row.insertCell(7);
        cell8.classList.add("table_buttons");
        cell8.innerHTML = drawTableButton(e.places, e.isADriver, e.hasJoined);
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