package lv.ctco.javaschool.app.control;

import lv.ctco.javaschool.app.entity.domain.Event;
import lv.ctco.javaschool.app.entity.domain.Trip;
import lv.ctco.javaschool.app.entity.domain.TripStatus;
import lv.ctco.javaschool.auth.entity.domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Stateless
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

    public Optional<Trip> findTripById(Long id) {
        return em.createQuery("select t from Trip t where t.id = :id", Trip.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }

    public void addTrip(Trip trip) {
        em.persist(trip);
    }

    private String getCurrentTime(){
        LocalDateTime dt = LocalDateTime.now();
        return dt.format(DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm"));
    }

    public List<Event> findAllEvents(){
        return em.createQuery(
                        "select e from Event e " +
                           "where e.eventDateTime >= :newDT", Event.class)
                .setParameter("newDT",   getCurrentTime() )
                .getResultList();
    }

}
