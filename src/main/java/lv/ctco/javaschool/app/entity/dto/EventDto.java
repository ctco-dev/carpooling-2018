package lv.ctco.javaschool.app.entity.dto;


import lv.ctco.javaschool.app.entity.domain.Place;

import java.util.ArrayList;
import java.util.List;

public class EventDto {
    private Long eventId;
    private String eventName;
    private String eventDate;
    private String eventTime;
    private Place eventPlace;
    private List<String> usernames;
    private Boolean iamCreator;


    public EventDto() {
    }

    public EventDto(String eventName, String eventDate, String eventTime, Place eventPlace) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventPlace = eventPlace;
        this.usernames = new ArrayList<>();
    }

    public Long getEventId() {
        return eventId;
    }
    public void setEventId(Long eventId) {
        this.eventId = eventId;
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

    public Place getEventPlace() {
        return eventPlace;
    }
    public void setEventPlace(Place eventPlace) {
        this.eventPlace = eventPlace;
    }

    public String getEventDate() { return eventDate; }
    public void setEventDate(String eventDate) { this.eventDate = eventDate; }

    public String getEventTime() { return eventTime; }
    public void setEventTime(String eventTime) { this.eventTime = eventTime; }

    public Boolean getIamCreator() {
        return iamCreator;
    }

    public void setIamCreator(Boolean isACreator) {
        this.iamCreator = isACreator;
    }
}
