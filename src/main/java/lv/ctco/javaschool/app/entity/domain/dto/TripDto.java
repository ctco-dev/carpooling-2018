package lv.ctco.javaschool.app.entity.domain.dto;

import lv.ctco.javaschool.app.entity.domain.Place;
import lv.ctco.javaschool.app.entity.domain.TripStatus;
import lv.ctco.javaschool.auth.entity.domain.User;

public class TripDto {

    private User driver;
    private Place from;
    private Place to;
    private int places;
    private String time;
    private boolean isEvent;
    private TripStatus tripStatus;
}
