package lv.ctco.javaschool.app.boundary;

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
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Path("/trip")
public class TripApi {
    @PersistenceContext
    private EntityManager em;

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
    public List<Place> getAllPlaces() {
        return Arrays.asList(Place.values());
    }

    @POST
    @RolesAllowed({"ADMIN", "USER"})
    @Path("/createTrip")
    public void createNewTrip(JsonObject field) {
        User user = userStore.getCurrentUser();
        Trip trip = new Trip();
        trip.setDriver(user);
        for (Map.Entry<String, JsonValue> pair : field.entrySet()) {
            String addr = pair.getKey();
            String value = ((JsonString) pair.getValue()).getString();
            trip = setFieldsToTrip(trip, addr, value);
        }
        tripStore.addTrip(trip);
    }

    private Trip setFieldsToTrip(Trip trip, String addr, String value) throws IllegalArgumentException {
        boolean isEvent;
        switch (addr) {
            case ("departure"):
                Place departure = Place.valueOf(value);
                trip.setDeparture(departure);
                break;
            case ("destination"):
                Place destination = Place.valueOf(value);
                trip.setDestination(destination);
                break;
            case ("places"):
                int places = Integer.parseInt(value);
                trip.setPlaces(places);
                break;
            case ("departureTime"):
                trip.setDepartureTime(value);
                break;
            case ("isEvent"):
                isEvent = Boolean.parseBoolean(value);
                trip.setEvent(isEvent);
                break;
            case ("tripStatus"):
                TripStatus status = TripStatus.valueOf(value);
                trip.setTripStatus(status);
                break;
            default:
                throw new IllegalArgumentException(addr + value);
        }
        return trip;
    }
}

