function editFields() {
    document.getElementById("button-save").disabled = false;
    var inputs = document.querySelectorAll('input[name=input]');
    var text = document.querySelectorAll('p.text');
    var innerHTML = [];
    var i = 0;
    text.forEach(function (txt) {
        txt.classList.add("w3-hide");
        innerHTML.push(txt.innerHTML);
    });
    inputs.forEach(function (inpt) {
        inpt.classList.remove("w3-hide");
        inpt.value = innerHTML[i];
        i++;
    });
}
function saveFields() {
    var inputs = document.querySelectorAll('input[name=input]');
    var text = document.querySelectorAll('p.text');
    var innerHTML = [];
    var i = 0;
    text.forEach(function (txt) {
        txt.classList.remove("w3-hide");
        innerHTML.push(txt.innerHTML);
    });
    inputs.forEach(function (inpt) {
        inpt.classList.add("w3-hide");
        inpt.value = innerHTML[i];
        i++;
    });
}
function myFunction() {
    document.getElementById("myDropdown").classList.toggle("show");
}
window.onclick = function (event) {
    if (!event.target.matches('.dropbtn')) {
        var dropdowns = document.getElementsByClassName("dropdown-content");
        var i;
        for (i = 0; i < dropdowns.length; i++) {
            var openDropdown = dropdowns[i];
            if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
            }
        }
    }
}
function displayMyActiveTrips() {
    fetch('/api/trip/driver', {
        "method": "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    }).then(function (response) {
        return response.json();

    }).then(function (trips) {
        drawTable(trips, "trips");
    });
}


function drawTable(tripsList, tabId) {
    var table = document.getElementById(tabId);
    var tbody = table.getElementsByTagName('tbody')[0];
    if (tbody) table.removeChild(tbody);

    tbody = document.createElement('tbody');
    tripsList.trips.forEach(function (t) {
        var row = tbody.insertRow();

        var cellInd = 0;
        var cell_Id = row.insertCell(cellInd);
        cell_Id.id = "id";
        cell_Id.classList.add("table_id");
        cell_Id.innerHTML = t.id;

        cellInd++;
        var cell_rote = row.insertCell(cellInd);
        cell_rote.innerHTML = t.from+" - "+t.to;

        cellInd++;
        var cell_freePlaces = row.insertCell(cellInd);
        cell_freePlaces.innerHTML = t.places;

        cellInd++;
        var cell_event = row.insertCell(cellInd);
        cell_event.innerHTML = t.event;

        cellInd++;
        var cell_deleteBtn = row.insertCell(cellInd);
        cell_deleteBtn.innerHTML = addDeleteBtn();
        cell_deleteBtn.classList.add("delete_button");
    });
    table.appendChild(tbody);
}

function addDeleteBtn() {
    return "<button id=\"button-delete-trip\" type=\"button\" class=\"btn btn-primary\"\n" +
        " onclick=\"deleteTrip( $(this).closest('tr').find('.table_id').text() )\">\n" +
        "Delete</button>";
}

function submitUserDto() {
    var name = document.getElementById("name");
    var surname = document.getElementById("surname");
    var phone = document.getElementById("phoneNumber");
    var car_model = document.getElementById("carModel");
    var car_color = document.getElementById("carColor");
    var car_number = document.getElementById("carNumber");
    var dto = {
        "name": name.value,
        "surname": surname.value,
        "phoneNumber": phone.value,
        "carModel": car_model.value,
        "carColor": car_color.value,
        "carNumber": car_number.value
    };
    saveFields();
    submitUserData(dto);
}
function submitUserData(userDto) {
    fetch('/api/user/update', {
        "method": "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userDto)
    }).then(function () {
        displayUserData();
    })
}
function displayUserData() {
    document.getElementById("button-save").disabled = true;
    fetch('/api/user/current', {
        "method": "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    }).then(function (response) {
        return response.json();
    }).then(function (userDto) {
        document.getElementById("p_name").innerText = userDto.name;
        document.getElementById("p_surname").innerText = userDto.surname;
        document.getElementById("p_phone").innerText = userDto.phoneNumber;
        document.getElementById("p_carmodel").innerText = userDto.carModel;
        document.getElementById("p_carcolor").innerText = userDto.carColor;
        document.getElementById("p_carnumber").innerText = userDto.carNumber;
    })
}
function deleteTrip(tripID) {
           fetch('api/trip/deleteTrip/' + tripID, {
            "method": "GET",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
         }).then(function (response) {
        displayMyActiveTrips();
        })
}