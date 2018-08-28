package lv.ctco.javaschool.app.control;

import javafx.scene.control.Cell;
import lv.ctco.javaschool.app.entity.domain.Place;
import lv.ctco.javaschool.app.entity.domain.Trip;
import lv.ctco.javaschool.app.entity.domain.TripStatus;
import lv.ctco.javaschool.auth.entity.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class TripStore {

    @PersistenceContext
    private EntityManager em;

    public List<Trip> getAllTrips() {
        return em.createQuery("select t from Trip t", Trip.class)
                .getResultList();
    }

    public List<Trip> findTripsByStatus(TripStatus tripStatus) {
        return em.createQuery("select t from Trip t where t.tripStatus = :status", Trip.class)
                .setParameter("status", tripStatus)
                .getResultList();
    }

    public List<Trip> findTripsByUser(User user) {
        return em.createQuery("select t from Trip t where t.driver = :user", Trip.class)
                .setParameter("user", user)
                .getResultList();
    }
    public Trip createTrip(User driver, Place departure, Place destination,Integer places,String depatureTime, Boolean isEvent, TripStatus tripStatus ){
        Trip trip=new Trip();
        trip.setDriver(driver);
        trip.setDeparture(departure);
        trip.setDestination(destination);
        trip.setPlaces(places);
        trip.setDepartureTime(depatureTime);
        trip.setEvent(isEvent);
        trip.setTripStatus(tripStatus);
        em.persist(trip);
        return trip;
    }
}

