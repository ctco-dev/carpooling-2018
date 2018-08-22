package lv.ctco.javaschool.app.entity.domain;

import lv.ctco.javaschool.auth.entity.domain.User;

import javax.persistence.*;

@Entity
@Table(name = "trips")
public class Trip {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User driver;

    @Enumerated(EnumType.STRING)
    private Place departure;

    @Enumerated(EnumType.STRING)
    private Place destination;

    private int places;

    private String departureTime;

    private boolean isEvent;

    @Enumerated(EnumType.STRING)
    private TripStatus tripStatus;

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public Place getDeparture() {
        return departure;
    }

    public void setDeparture(Place from) {
        this.departure = from;
    }

    public Place getDestination() {
        return destination;
    }

    public void setDestination(Place to) {
        this.destination = to;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String time) {
        this.departureTime = time;
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
}
