package lv.ctco.javaschool.app.entity.dto;

import lv.ctco.javaschool.app.entity.domain.Event;
import lv.ctco.javaschool.app.entity.domain.Place;
import lv.ctco.javaschool.app.entity.domain.TripStatus;

import java.util.Objects;

public class TripDto {

        private String driverInfo;
        private String driverPhone;
        private Place from;
        private Place to;
        private int places;
    private String time;
    private boolean isEvent;
    private TripStatus tripStatus;
    private String eventName;
    private String eventStartTime;

    public TripDto() {
    }

    public TripDto(String driverInfo, String driverPhone, Place from, Place to, int places, String time, boolean isEvent, TripStatus tripStatus,String eventName, String eventStartTime) {
        this.driverInfo = driverInfo;
        this.driverPhone = driverPhone;
        this.from = from;
        this.to = to;
        this.places = places;
        this.time = time;
        this.isEvent = isEvent;
        this.tripStatus = tripStatus;
        this.eventName=eventName;
        this.eventStartTime=eventStartTime;
    }

    public String getDriverInfo() {
        return driverInfo;
    }

    public void setDriverInfo(String driverInfo) {
        this.driverInfo = driverInfo;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public Place getFrom() {
        return from;
    }

    public void setFrom(Place from) {
        this.from = from;
    }

    public Place getTo() {
        return to;
    }

    public void setTo(Place to) {
        this.to = to;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isEvent() {
        return isEvent;
    }

    public void setEvent(boolean event) {
        isEvent = event;
    }

    public TripStatus getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(TripStatus tripStatus) {
        this.tripStatus = tripStatus;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(String eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripDto tripDto = (TripDto) o;
        return places == tripDto.places &&
                isEvent == tripDto.isEvent &&
                Objects.equals(driverInfo, tripDto.driverInfo) &&
                Objects.equals(driverPhone, tripDto.driverPhone) &&
                from == tripDto.from &&
                to == tripDto.to &&
                Objects.equals(time, tripDto.time) &&
                tripStatus == tripDto.tripStatus &&
                Objects.equals(eventName,tripDto.eventName) &&
                Objects.equals(eventStartTime,tripDto.eventStartTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(driverInfo, driverPhone, from, to, places, time, isEvent, tripStatus,eventName,eventStartTime);
    }
}
