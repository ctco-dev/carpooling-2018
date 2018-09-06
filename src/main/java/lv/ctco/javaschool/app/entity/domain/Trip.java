package lv.ctco.javaschool.app.entity.domain;

import lv.ctco.javaschool.auth.entity.domain.User;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trips")
public class Trip {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "driver_id")
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

    @ManyToMany
    @JoinTable(name = "user_trip",
            joinColumns = @JoinColumn(name = "trip_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> passengers;

    public Trip() {
    }

    public Trip(User driver, Place departure, Place destination, int places, String departureTime, boolean isEvent, TripStatus tripStatus) {
        this.driver = driver;
        this.departure = departure;
        this.destination = destination;
        this.places = places;
        this.departureTime = departureTime;
        this.isEvent = isEvent;
        this.tripStatus = tripStatus;
        this.passengers = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<User> getPassengers() {
        return passengers;
    }
    public void setPassengers(List<User> passengers) {
        this.passengers = passengers;
    }
}
