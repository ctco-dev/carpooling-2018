package lv.ctco.javaschool.app.entity.domain;

import lv.ctco.javaschool.auth.entity.domain.User;

import javax.persistence.*;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue
    private Long id;

    private String eventName;
    private String eventStartTime;


    private Event() {
    }
    public Event(String eventName,String eventStartTime) {
        this.eventName=eventName;
        this.eventStartTime= eventStartTime;
    }


    public String getEventName() {
        return eventName;
    }

    public void setEventName(String carModel) {
        this.eventName= eventName;
    }

    public String getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(String eventStartTime) {
        this.eventStartTime= eventStartTime;
    }
}


