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

function displayTripPassengers(td) {
    fetch('/api/trip/passengers', {
        "method": "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    }).then(function (response) {
        return response.json();
    }).then(function (passengers) {
        console.log(JSON.stringify(passengers));
        w3DisplayData("passengers", passengers);
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

function join(button) {
    var data = {};
    var places = $(button).closest('tr').find('.table_places').text() - 1;
    var tripId = $(button).closest('tr').find('.table_id').text();
    var id = $(button).closest('td').parent().index();
    var x = document.getElementById("trips").rows[id + 1].cells;
    x[4].innerHTML = places;
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