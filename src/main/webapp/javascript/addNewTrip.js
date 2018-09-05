function tripdto() {
    var yesChkBox = document.getElementById("yes");
    var departure = document.getElementById("departure");
    var departureTime = document.getElementById("departure-time");
    var destination = document.getElementById("destination");
    var places = document.getElementById("places");
    if (yesChkBox.checked) {
        var isEvent = "true";
        var eventName=document.getElementById("name");
        // var eventDateTime=document.getElementById("time");
    }
    else {
        var isEvent = "false";
    }
    var dto = {
        "to": departure.options[departure.selectedIndex].value,
        "from": destination.options[destination.selectedIndex].value,
        "places": places.value,
        "time": departureTime.value,
        "isEvent": isEvent,
        "eventName": eventName.options[eventName.selectedIndex].value,
        "tripStatus": "ACTIVE"
    };
    saveTrip(dto);
}
function addOptionValues() {
    var select = document.getElementsByClassName("places")[0];
    var select1 = document.getElementsByClassName("places")[1];
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
            select1.add(new Option(c));
        });
    });
}
function addRemoveEvent() {
    var yesChkBox = document.getElementById("yes");
    var noChkBox = document.getElementById("no");
    if (yesChkBox.checked) {
        noChkBox.checked = false;
        document.getElementById('event-name').classList.remove("w3-hide");
        document.getElementById('event-time').classList.remove("w3-hide");
        document.getElementById('participants').classList.remove("w3-hide");
        showEvents();

    }
    if (noChkBox.checked) {
        yesChkBox.checked = false;
        document.getElementById('event-name').classList.add("w3-hide");
        document.getElementById('event-time').classList.add("w3-hide");
        document.getElementById('participants').classList.add("w3-hide");
    }
}
function saveTrip(values) {
    console.log("sending data");
    console.log(JSON.stringify(values));
    fetch('/api/trip/createTrip', {
        "method": "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(values)
    }).then(function (response) {
        location.href = "/main.jsp";
    })
}

function showEvents() {
    var select = document.getElementsByClassName("event-name")[0];
    fetch('/api/trip/events', {
        "method": "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    }).then(function (response) {
        return response.json();
    }).then(function (events) {
        console.log(events);
        events.forEach(function (e) {
            select.add(new Option(e.eventName));
        });
    });
}

function showEventInfo() {
    var selectedValue=document.getElementById("name");
    var event=selectedValue.options[selectedValue.selectedIndex].value;
    fetch('/api/trip/getEvent/'+event, {
        "method": "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    }).then(function (response) {
        return response.json();
    }).then(function (event) {
        document.getElementById("time").value=event.eventDate+" "+event.eventTime;
    });

}