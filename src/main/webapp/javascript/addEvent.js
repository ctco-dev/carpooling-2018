function buildEventDto() {
    var eventName = document.getElementById("name");
    var eventDate = document.getElementById("datepicker");
    var eventTime = document.getElementById("timepicker");
    var eventPlace = document.getElementById("place");
    var user = document.getElementById("participants")

    var dto = {
        "eventName": eventName.value,
        "eventPlace": eventPlace.options[eventPlace.selectedIndex].value,
        "date": eventDate.value,
        "time": eventTime.value,
        "usernames": user.options[user.selectedIndex].value
    };
    addNewEvent(dto);

}

function showMyEvents() {
    fetch('/api/trip/events', {
        "method": "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    }).then(function (response) {
        return response.json();
    }).then(function (events) {
        events.forEach(function (e) {
            var row = table.insertRow();
            var cell1 = row.insertCell(0);
            var cell2 = row.insertCell(1);
            var cell3 = row.insertCell(2);
            var cell4 = row.insertCell(3);
            cell1.innerHTML = e.eventName;
            //cell2.innerHTML=e.eventDateT;
            cell4.innerHTML = e.eventPlace;
        });
    });
}

function addNewEvent(data) {
    fetch('/api/trip/createEvent', {
        "method": "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(function (response) {
        console.log(data);
        showMyEvents();
    })
}

function addOptionValues() {
    var select = document.getElementsByClassName("place")[0];
    fetch('/api/trip/places', {
        "method": "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    }).then(function (response) {
        return response.json();
    }).then(function (trips) {
        trips.forEach(function (c) {
            select.add(new Option(c));
        });
    });
}

function showUsers() {
    var select = document.getElementsByClassName("users")[0];
    fetch('/api/trip/getUsers', {
        "method": "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    }).then(function (response) {
        return response.json();
    }).then(function (users) {
        console.log(users);
        users.forEach(function (u) {
            select.add(new Option(u.name + " " + u.surname));
        });
    });
}


