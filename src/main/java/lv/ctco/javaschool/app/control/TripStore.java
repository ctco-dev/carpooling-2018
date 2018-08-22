package lv.ctco.javaschool.app.control;

import lv.ctco.javaschool.app.entity.domain.Trip;
import lv.ctco.javaschool.app.entity.domain.TripStatus;

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
        List<Trip> trip = em.createQuery("select t from Trip t where t.tripStatus = :status", Trip.class)
                .setParameter("status", tripStatus)
                .getResultList();
        return trip.isEmpty() ? Optional.empty() : Optional.of(trip.get(0));
    }
}
