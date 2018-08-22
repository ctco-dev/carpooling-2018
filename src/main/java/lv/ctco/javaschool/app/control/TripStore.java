package lv.ctco.javaschool.app.control;

import lv.ctco.javaschool.app.entity.domain.Trip;

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
}
