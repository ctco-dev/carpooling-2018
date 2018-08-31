function editField() {
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