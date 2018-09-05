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
    });
}

function drawTable(tripsList, tabId){
    var table = document.getElementById(tabId);
    var tbody = table.getElementsByTagName('tbody')[0];
    if (tbody) table.removeChild(tbody);

    tbody = document.createElement('tbody');
    tripsList.trips.forEach(function (e) {
        var row = tbody.insertRow();
        var cell1 = row.insertCell(0);
        cell1.id="id";
        cell1.classList.add("table_id");
        cell1.innerHTML = e.id;

        var cell2 = row.insertCell(1);
        cell2.innerHTML = e.from+" - "+e.to;

        var cell3 = row.insertCell(2);
        cell3.innerHTML = e.driverInfo;

        var cell4 = row.insertCell(3);
        cell4.innerHTML = e.driverPhone;

        var cell5 = row.insertCell(4);
        cell5.id="places";
        cell5.classList.add("table_places");
        cell5.innerHTML =e.places.toString();

        var cell6 = row.insertCell(5);
        cell6.id="passengers";
        cell6.classList.add("table_passengers");
        cell6.innerHTML =drawPassangersList(e.passengers);

        var cell7 = row.insertCell(6);
        cell7.innerHTML = e.event;

        var cell8 = row.insertCell(7);
        cell8.id="deleteButton";
        cell8.innerHTML ="<button id=\"join-button\" type=\"button\" class=\"btn btn-primary\"\n" +
            " onclick=\"join(this, $(this).closest('tr').find('.table_id').text(), $(this).closest('td').parent().index(), $(this).closest('tr').find('.table_places').text())\">\n" +
            "Join</button>"
    });
    table.appendChild(tbody);
}

function drawPassangersList( passList ){
    var res =""
    for (var i=0; i<passList.length; i++){
        res+='<li>'+passList[i]+'</li>';
    }
    return "<ol>"+res+"</ol>";
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
    if (places <= 0) {
        button.disabled = true;
        alert("There are no free places for this trip");
        return;
    }

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
                displayActiveTrips();
            }
        }
    });

}


function goAddEvent(){
    location.href = "/addEvent.jsp";
}