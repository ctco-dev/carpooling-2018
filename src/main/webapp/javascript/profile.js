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
        console.log(JSON.stringify(trips));
        w3DisplayData("trips", trips);
    });
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