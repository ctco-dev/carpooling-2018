package lv.ctco.javaschool.app.entity.domain;

import lv.ctco.javaschool.app.control.DateTimeCoverter;
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
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue
    private Long id;

    private String eventName;
    private LocalDateTime eventDateTime;

    @Enumerated(EnumType.STRING)
    private Place eventDestination;

    @ManyToMany
    @JoinTable(name = "user_event",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> participants;

    @ManyToOne
    private User eventCreator;

    private Boolean isDeleted = false;

    public Event() {
    }

    public Event(String eventName, String eventDate, String eventTime, Place eventDestination) {
        this.eventName = eventName;
        this.eventDateTime = DateTimeCoverter.covertToDateTime(eventDate, eventTime);
        this.eventDestination = eventDestination;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Place getEventDestination() {
        return eventDestination;
    }

    public void setEventDestination(Place eventDestination) {
        this.eventDestination = eventDestination;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public LocalDateTime getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(LocalDateTime eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public User getEventCreator() {
        return eventCreator;
    }

    public void setEventCreator(User eventCreator) {
        this.eventCreator = eventCreator;
    }

    public Boolean getDeletedStatus() {
        return isDeleted;
    }

    public void setDeletedStatus(Boolean deleted) {
        isDeleted = deleted;
    }
}
