package lv.ctco.javaschool.app.entity.dto;


import lv.ctco.javaschool.app.entity.domain.Place;

import java.util.List;

public class EventDto {
    private String eventName;
    private String eventDateTime;
    private Place eventPlace;
    private List<String> usernames;

    public EventDto(String name, String dateTime, Place place){
        this.eventName=name;
        this.eventDateTime=dateTime;
        this.eventPlace=place;
        this.usernames=null;
    }

    public List<String> getUsernames() {
        return usernames;
    }

    public void setUsernames(List<String> usernames) {
        this.usernames = usernames;
    }

    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDateTime() {
        return eventDateTime;
    }
    public void setEventDateTime(String eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public Place getEventPlace() {
        return eventPlace;
    }
    public void setEventPlace(Place eventPlace) {
        this.eventPlace = eventPlace;
    }

}
