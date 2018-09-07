var eventList = [];
function tripdto() {
    var dto = {}
    var yesChkBox = document.getElementById("yes");
    var departure = document.getElementById("departure");
    var departureTime = document.getElementById("departure-time");
    var destination = document.getElementById("destination");
    var places = document.getElementById("places");
    var eventName = document.getElementById("name");
    if (yesChkBox.checked) {
        var isEvent = true;
        dto["isEvent"] = isEvent;
        dto["eventName"] = eventName.options[eventName.selectedIndex].value;
        dto["eventId"]  = eventList[eventName.selectedIndex].eventId;
    }
    else {
        var isEvent = false;
        dto["isEvent"] = isEvent;
        dto["eventName"] = "";
        dto["eventId"] = null;

    }
    dto["to"] = destination.options[destination.selectedIndex].value;
    dto["from"] = departure.options[departure.selectedIndex].value;
    dto["places"] = places.value;
    dto["time"] = departureTime.value;
    dto["tripStatus"] = "ACTIVE";
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
        showEvents();
    }
    if (noChkBox.checked) {
        yesChkBox.checked = false;
        document.getElementById('event-name').classList.add("w3-hide");
        document.getElementById('event-time').classList.add("w3-hide");
    }
}

function saveTrip(values) {
    fetch('/api/trip/createTrip', {
        "method": "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(values)
    }).then(function (response) {
        if (response.status == 400) {
            alert("Event not found")
        } else if (response.status === 201) {
           location.href = "/main.jsp";
        }
    })
}


function showEvents() {
    var select = document.getElementsByClassName("event-name")[0];
    fetch('/api/trip/eventList', {
        "method": "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    }).then(function (response) {
        return response.json();
    }).then(function (events) {
        eventList = events;
        eventList.forEach(function (e) {
            select.add(new Option(e.eventName));
        });
        showEventInfo();
    });
}

function showEventInfo() {
    var selectedValue = document.getElementById("name");
    var event = eventList[selectedValue.selectedIndex].eventId;
    fetch('/api/trip/getEvent/' + event, {
        "method": "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    }).then(function (response) {
        return response.json();
    }).then(function (newEvent) {
        document.getElementById("time").value = newEvent.eventDate + " " + newEvent.eventTime;
    });

}