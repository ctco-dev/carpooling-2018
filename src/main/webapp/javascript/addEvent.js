var selectedUsers = [];

function buildEventDto() {
    var eventName = document.getElementById("name");
    var eventDate = document.getElementById("datepicker");
    var eventTime = document.getElementById("timepicker");
    var eventPlace = document.getElementById("place");
    var user = document.getElementById("participants");

    var dto = {
        "eventDate": eventDate.value,
        "eventName": eventName.value,
        "eventPlace": eventPlace.options[eventPlace.selectedIndex].value,
        "eventTime": eventTime.value,
        "usernames": selectedUsers
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
    }).then(function (eventsList) {
        console.log(eventsList);
        drawTable(eventsList, "events");
        window.setTimeout(function () {
            showMyEvents();
        }, 1000);
    });
}


function drawTable(eventsList, tabId) {
    var table = document.getElementById(tabId);
    var tbody = table.getElementsByTagName('tbody')[0];
    if (tbody) table.removeChild(tbody);

    tbody = document.createElement('tbody');
    eventsList.forEach(function (e) {
        var row = tbody.insertRow();

        var cellInd = 0;
        var cell_Id = row.insertCell(cellInd);
        cell_Id.id = "id";
        cell_Id.classList.add("table_id");
        cell_Id.innerHTML = e.eventId;

        cellInd++;
        var cell_name = row.insertCell(cellInd);
        cell_name.innerHTML = e.eventName;

        cellInd++;
        var cell_date = row.insertCell(cellInd);
        cell_date.innerHTML = e.eventDate;

        cellInd++;
        var cell_time = row.insertCell(cellInd);
        cell_time.innerHTML = e.eventTime;

        cellInd++;
        var cell_place = row.insertCell(cellInd);
        cell_place.innerHTML = e.eventPlace;

        cellInd++;
        var cell_deleteBtn = row.insertCell(cellInd);
        cell_deleteBtn.innerHTML = addDeleteBtn(e.iamCreator);
        cell_deleteBtn.classList.add("delete_button");
    });
    table.appendChild(tbody);
}

function addDeleteBtn(showButton) {
    if (showButton === false) return "";

    return "<button id=\"delete-button\" type=\"button\" class=\"btn btn-primary\"\n" +
        " onclick=\"deleteEvent( $(this).closest('tr').find('.table_id').text() )\">\n" +
        "Delete</button>";
}

function deleteEvent(eventId) {
    fetch('/api/trip/deleteEvent/' + eventId, {
        "method": "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    }).then(function (response) {
        showMyEvents();
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
        if (response.status === 204) {
            alert("Please, fill all fields correctly!");
        } else if (response.status === 206) {
            alert("Please, verify event date and time. Date set by you is expired!");
        } else if (response.status === 201) {
            document.getElementById("name").value = '';
            document.getElementById("datepicker").value = '';
            document.getElementById("timepicker").value = '';
            DeleteAllUsers();
            showMyEvents();
        } else alert("Something is wrong!");
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
        users.forEach(function (u) {
            select.add(new Option(u.name + " " + u.surname));
        });
    });
}


var remove = function () {
    var index = selectedUsers.indexOf(this.parentNode.lastChild.innerHTML);
    if (index > -1) {
        selectedUsers.splice(index, 1);
    }
    this.parentNode.remove();
}

function removeUser() {
    var lis = document.querySelectorAll('li');
    var button = document.querySelectorAll('span[name=deleteBtn]');
    for (var i = 0, len = lis.length; i < len; i++) {
        button[i].addEventListener('click', remove, false);
    }
}

function AddUser() {
    var obj = document.getElementById("participants");
    if (selectedUsers.indexOf(obj.options[obj.selectedIndex].text) >= 0) return;
    selectedUsers.push(obj.options[obj.selectedIndex].text);
    document.getElementById("userList").innerHTML +=
        '<li name="user" ><span class="btn btn-primary" name="deleteBtn" >x</span>  &nbsp;' +
        '<span>' + obj.options[obj.selectedIndex].text + '</span></li>';
    removeUser();
}

function DeleteAllUsers() {
    selectedUsers = [];
    document.getElementById("userList").innerHTML = "";
}