package lv.ctco.javaschool.app.boundary;

import lv.ctco.javaschool.app.control.TripStore;
import lv.ctco.javaschool.app.entity.domain.Trip;
import lv.ctco.javaschool.app.entity.domain.TripStatus;
import lv.ctco.javaschool.app.entity.dto.ListTripDto;
import lv.ctco.javaschool.app.entity.dto.TripDto;
import lv.ctco.javaschool.auth.entity.domain.User;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

@Path("/trip")
@Stateless
public class TripApi {

    @Inject
    private TripStore tripStore;

    TripDto convertToTripDto(Trip trip) {
        User driver = trip.getDriver();
        TripDto dto = new TripDto();
        dto.setId(trip.getId());
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

    @POST
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "USER"})
    public void setTripPlaces(JsonObject field, @PathParam("id") String tripId) {
        for (Map.Entry<String, JsonValue> pair : field.entrySet()) {
            Integer places = ((JsonNumber) pair.getValue()).intValue();
            tripStore.setTripPlaces(places, tripId);
        }
    }
}
