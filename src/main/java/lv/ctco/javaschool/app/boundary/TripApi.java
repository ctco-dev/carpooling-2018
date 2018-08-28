package lv.ctco.javaschool.app.boundary;

import javafx.scene.control.Cell;
import lv.ctco.javaschool.app.control.TripStore;
import lv.ctco.javaschool.app.entity.domain.Place;
import lv.ctco.javaschool.app.entity.domain.Trip;
import lv.ctco.javaschool.app.entity.domain.TripStatus;
import lv.ctco.javaschool.app.entity.dto.ListTripDto;
import lv.ctco.javaschool.app.entity.dto.TripDto;
import lv.ctco.javaschool.auth.control.UserStore;
import lv.ctco.javaschool.auth.entity.domain.User;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.sql.Driver;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("/trip")
public class TripApi {
    private Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    @Inject
    private UserStore userStore;

    @Inject
    private TripStore tripStore;

    TripDto convertToTripDto(Trip trip) {
        User driver = trip.getDriver();
        TripDto dto = new TripDto();
        dto.setDriverInfo(driver.getSurname() + " " + driver.getName());
        dto.setDriverPhone(driver.getPhoneNumber());
        dto.setEvent(trip.isEvent());
        dto.setFrom(trip.getDeparture());
        dto.setTo(trip.getDestination());
        dto.setPlaces(trip.getPlaces());
        dto.setTime(trip.getDepartureTime());
        dto.setTripStatus(trip.getTripStatus());
        return dto;
    }

    @GET
    @Path("/active")
    @Produces("application/json")
    @RolesAllowed({"ADMIN", "USER"})
    public ListTripDto getActiveTrips() {
        ListTripDto listTripDto = new ListTripDto();
        listTripDto.setTrips(tripStore.findTripsByStatus(TripStatus.ACTIVE)
                .stream()
                .sorted(Comparator.comparing(Trip::getDepartureTime))
                .map(this::convertToTripDto)
                .collect(Collectors.toList()));
        return listTripDto;
    }

    @GET
    @Path("/places")
    @Produces("application/json")
    @RolesAllowed({"ADMIN", "USER"})
    public List<Place> getAllPlaces(){
            return Arrays.asList(Place.values());
    }

    @POST
    @Path("/create")
    @Produces("application/json")
    @RolesAllowed({"ADMIN","USER"})
    public void createNewTrip(TripDto createTrip)
    {
        User user=userStore.getCurrentUser();
        Place departure=createTrip.getFrom();
        Place destination=createTrip.getTo();
        Integer places=createTrip.getPlaces();
        String departureTime=createTrip.getTime();
        TripStatus tripStatus=createTrip.getTripStatus();
        Boolean isEvent=createTrip.isEvent();

        Trip trip=tripStore.createTrip(user,departure,destination,places,departureTime,isEvent,tripStatus);
        log.info(String.format("Trip is created %s", trip));
    }
}
