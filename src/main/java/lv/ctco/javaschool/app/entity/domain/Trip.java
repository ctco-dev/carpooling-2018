package lv.ctco.javaschool.app.entity.domain;

import lv.ctco.javaschool.auth.entity.domain.User;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Trip {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User driver;

    private Place from;

    private Place to;

    private int places;

    private Date time;

    private boolean isEvent;

    private TripStatus tripStatus;
}
