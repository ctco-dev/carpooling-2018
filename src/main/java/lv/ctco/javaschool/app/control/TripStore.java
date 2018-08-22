package lv.ctco.javaschool.app.control;

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

    public List<Trip> findTripByStatus(TripStatus tripStatus) {
        return em.createQuery("select t from Trip t where t.tripStatus = :status", Trip.class)
                .setParameter("status", tripStatus)
                .getResultList();
    }

    public List<Trip> findTripByUser(User user) {
        return em.createQuery("select t from Trip t where t.driver = :user", Trip.class)
                .setParameter("user", user)
                .getResultList();
    }

}
