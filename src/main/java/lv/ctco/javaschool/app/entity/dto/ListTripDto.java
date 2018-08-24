package lv.ctco.javaschool.app.entity.dto;

import java.util.List;

public class ListTripDto {
    List<TripDto> trips;

    public List<TripDto> getTrips() {
        return trips;
    }

    public void setTrips(List<TripDto> trips) {
        this.trips = trips;
    }
}
