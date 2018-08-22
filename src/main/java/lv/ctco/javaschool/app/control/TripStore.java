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

    public Optional<Trip> findTripByStatus(TripStatus tripStatus) {
        List<Trip> trips = em.createQuery("select t from Trip t where t.tripStatus = :status", Trip.class)
                .setParameter("status", tripStatus)
                .getResultList();
        return trips.isEmpty() ? Optional.empty() : Optional.of(trips.get(0));
    }

    public Optional<Trip> findTripByUser(User user) {
        List<Trip> trips = em.createQuery("select t from Trip t where t.driver = :user", Trip.class)
                .setParameter("user", user)
                .getResultList();
        return trips.isEmpty() ? Optional.empty() : Optional.of(trips.get(0));
    }

}
