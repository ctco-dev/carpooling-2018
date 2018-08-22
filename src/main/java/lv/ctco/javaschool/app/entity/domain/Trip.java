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

    @Enumerated(EnumType.STRING)
    private Place from;

    @Enumerated(EnumType.STRING)
    private Place to;

    private int places;

    private String time;

    private boolean isEvent;

    @Enumerated(EnumType.STRING)
    private TripStatus tripStatus;
}
