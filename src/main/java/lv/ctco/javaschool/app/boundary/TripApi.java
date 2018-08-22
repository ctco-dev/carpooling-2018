package lv.ctco.javaschool.app.boundary;

import lv.ctco.javaschool.app.control.TripStore;
import lv.ctco.javaschool.app.entity.domain.Trip;
import lv.ctco.javaschool.app.entity.domain.TripStatus;
import lv.ctco.javaschool.app.entity.domain.dto.TripDto;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Path("/trip")
public class TripApi {

    @Inject
    private TripStore tripStore;

    @GET
    @Path("/active")
    @Produces("application/json")
    @RolesAllowed({"ADMIN", "USER"})
    public List<TripDto> getActiveTrips() {
        List<Trip> activeTrips = tripStore.findTripByStatus(TripStatus.ACTIVE);
        return activeTrips.stream().sorted(Comparator.comparing(Trip::getTime))
                .map(this::convertToTripDto)
                .collect(Collectors.toList());
    }

    private TripDto convertToTripDto(Trip trip) {
        TripDto dto = new TripDto();
        dto.setDriver(trip.getDriver());
        dto.setEvent(trip.isEvent());
        dto.setFrom(trip.getFrom());
        dto.setTo(trip.getTo());
        dto.setPlaces(trip.getPlaces());
        dto.setTime(trip.getTime());
        dto.setTripStatus(trip.getTripStatus());
        return dto;
    }
}
