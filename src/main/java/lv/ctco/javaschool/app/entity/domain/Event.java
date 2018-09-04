package lv.ctco.javaschool.app.entity.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue
    private Long id;

    private String eventName;
    private String eventStartTime;

    public Event() {
    }
    public Event(String eventName,String eventStartTime) {
        this.eventName=eventName;
        this.eventStartTime= eventStartTime;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName= eventName;
    }

    public String getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(String eventStartTime) {
        this.eventStartTime= eventStartTime;
    }
}


