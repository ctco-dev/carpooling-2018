function eventDto(){
    var event_name=document.getElementById("name");
    var event_time=document.getElementById("time") ;
    var dto = {
        "event_name":event_name.value,
        "event-time":event_time.value
    };
    saveEvent(dto);
}
function tripdto() {
    var yesChkBox= document.getElementById("yes");
    var departure =document.getElementById("departure");
    var departureTime =document.getElementById("departure-time");
    var destination=document.getElementById("destination");
    var places=document.getElementById("places");
    var isEvent;
    var event_id;
    if(yesChkBox.checked)
    {
        var isEvent="true";
    }
    else
    {
        var isEvent="false";
    }
    var dto = {
        "departure": departure.options[departure.selectedIndex].value,
        "destination":destination.options[destination.selectedIndex].value,
        "places":places.value,
        "departureTime": departureTime.value,
        "isEvent":isEvent,
        "tripStatus":"ACTIVE",
        "event_id":event_id.value
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
            select.add( new Option( c ) );
            select1.add( new Option( c ) );
        });
    });
}
function addRemoveEvent(){
    var yesChkBox= document.getElementById("yes");
    var noChkBox=document.getElementById("no");
    if(yesChkBox.checked)
    {
        noChkBox.checked=false;
        document.getElementById('event-name').classList.remove("w3-hide");
        document.getElementById('event-time').classList.remove("w3-hide");
        document.getElementById('participants').classList.remove("w3-hide");

    }
    if(noChkBox.checked)
    {
        yesChkBox.checked=false;
        document.getElementById('event-name').classList.add("w3-hide");
        document.getElementById('event-time').classList.add("w3-hide");
        document.getElementById('participants').classList.add("w3-hide");
    }
}
function saveTrip(values) {
    console.log("sending data");
    console.log(JSON.stringify(values))
    fetch('/api/trip/createTrip', {
        "method": "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(values)
    }).then(function (response)
    {
        location.href = "/main.jsp";
    })
}
function saveEvent(values) {
    console.log("sending data");
    console.log(JSON.stringify(values))
    fetch('/api/trip/createEvent', {
        "method": "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(values)
    }).then(function (response)
    {
        location.href = "/main.jsp";
    })
}