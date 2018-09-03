function addNewEvent() {
var eventName=document.getElementById("name");
var eventDate=document.getElementById("datepicker");
var eventTime=document.getElementById("timepicker");

    var dto = {
        "name": eventName.value,
        "place": place.options[place.selectedIndex].value,
        "date":eventDate.value,
        "time": eventTime.value,
        "user":user.value
    };
    saveTrip(dto);

}