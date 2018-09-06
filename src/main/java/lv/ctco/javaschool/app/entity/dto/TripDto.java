package lv.ctco.javaschool.app.entity.dto;

import lv.ctco.javaschool.app.entity.domain.Place;
import lv.ctco.javaschool.app.entity.domain.TripStatus;
import lv.ctco.javaschool.auth.entity.dto.UserLoginDto;

import java.util.List;
import java.util.Objects;

public class TripDto {

    private Long id;
    private String driverInfo;
    private String driverPhone;
    private Place from;
    private Place to;
    private int places;
    private String time;
    private boolean isEvent;
    private TripStatus tripStatus;
    private List<String> passengers;
    private Boolean hasJoined;
    private Boolean isDriver;

    public TripDto() {
    }

    public TripDto(String driverInfo, String driverPhone, Place from, Place to, int places, String time, boolean isEvent, TripStatus tripStatus) {
        this.driverInfo = driverInfo;
        this.driverPhone = driverPhone;
        this.from = from;
        this.to = to;
        this.places = places;
        this.time = time;
        this.isEvent = isEvent;
        this.tripStatus = tripStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public List<String> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<String> passengers) {
        this.passengers = passengers;
    }

    public Boolean getHasJoined() {
        return hasJoined;
    }

    public void setHasJoined(Boolean hasJoined) {
        this.hasJoined = hasJoined;
    }

    public Boolean getIsADriver() {
        return isDriver;
    }

    public void setIsADriver(Boolean driver) {
        isDriver = driver;
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
                tripStatus == tripDto.tripStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(driverInfo, driverPhone, from, to, places, time, isEvent, tripStatus);
    }
}
