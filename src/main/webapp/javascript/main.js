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
        .then(function () {
            location.href = "/";
        });
}
function goMyProfile() {
    location.href = "/profile.jsp";
}
function join(button, tripId, rowId, places) {
    var data = {};
    if (places > 0) {
        data = {"places": places};
        fetch('/api/trip/' + tripId, {
            "method": "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        }).then(function (response) {
                if (places > 0) {
                    if (response.status === 400) {
                        button.disabled = true;
                        alert("You have already joined this trip");
                    } else {
                        places = places - 1;
                        var rowCells = document.getElementById("trips").rows[rowId + 1].cells;
                        rowCells[4].innerHTML = places;
                        alert("You joined the trip");
                    }
                }
            }
        );
    } else {
        button.disabled = true;
        alert("There are no free places for this trip");
    }
}
function showPassengers(tripId) {
    var listDiv = document.getElementById("passenger_list");
    while (listDiv.firstChild) {
        listDiv.removeChild(listDiv.firstChild);
    }
    var ol = document.createElement('OL');
    var passenger_list = [];
    var i = 0;
    fetch('/api/trip/' + tripId + '/passengers', {
        "method": "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    }).then(function (response) {
        return response.json();
    }).then(function (passengers) {
        console.log(JSON.stringify(passengers));
        passengers.forEach(function (p) {
            passenger_list[i] = p.name + " " + p.surname;
            i++;
        });
    }).then(function () {
        for (var j = 0; j < passenger_list.length; j++) {
            var li = document.createElement('LI');
            li.appendChild(document.createTextNode(passenger_list[j]));
            ol.appendChild(li);
        }
        listDiv.appendChild(ol);
    });
}