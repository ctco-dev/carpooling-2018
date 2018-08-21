package lv.ctco.javaschool.app.entity.domain;

import lv.ctco.javaschool.auth.entity.domain.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
public class Trip {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private User driver;

    private Place from;

    private Place to;

    private int places;

    private Date time;

    private boolean isEvent;
}
