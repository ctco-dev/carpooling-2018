package lv.ctco.javaschool.app.control;

import lv.ctco.javaschool.app.entity.domain.Trip;
import lv.ctco.javaschool.app.entity.domain.TripStatus;
import lv.ctco.javaschool.auth.entity.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

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

    public void setTripPlaces(int places, String id) {
        Optional<Trip> trip = findTripById(Long.parseLong(id));
        if (trip.isPresent()) {
            trip.get().setPlaces(places);
            em.persist(trip.get());
        } else {
            throw new IllegalStateException();
        }
    }

}
