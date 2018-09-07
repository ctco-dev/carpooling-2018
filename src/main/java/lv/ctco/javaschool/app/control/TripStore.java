package lv.ctco.javaschool.app.control;

import lv.ctco.javaschool.app.entity.domain.Event;
import lv.ctco.javaschool.app.entity.domain.Trip;
import lv.ctco.javaschool.app.entity.domain.TripStatus;
import lv.ctco.javaschool.app.entity.dto.EventDto;
import lv.ctco.javaschool.auth.entity.domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class TripStore {

    @PersistenceContext
    private EntityManager em;

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

    public List<Event> findAllEventsForEventPage(User user){
        return em.createQuery(
                "select e from Event e "+
                        "where e.eventDateTime >= :newDT " +
                        "and e.isDeleted=:delFlag " , Event.class)
                .setParameter("newDT",   LocalDateTime.now() )
                .setParameter("delFlag",   false )
                .getResultStream()
                .filter(e -> ((e.getParticipants().contains(user)) || (e.getEventCreator().equals(user))) )
                .sorted(Comparator.comparing(Event::getEventDateTime))
                .collect(Collectors.toList());
    }

    public void addNewEvent(EventDto dto, User creator, List<User> userList) {
        Event event = new Event();
        event.setEventName( dto.getEventName() );
        event.setEventDateTime( DateTimeCoverter.covertToDateTime(dto.getEventDate(), dto.getEventTime()) );
        event.setEventDestination( dto.getEventPlace() );
        event.setEventCreator( creator );
        event.setParticipants(userList);
        em.persist(event);
    }

    public Optional<Event> findEventById(Long id) {
        return em.createQuery("select e from Event e where e.id = :id", Event.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }
}
