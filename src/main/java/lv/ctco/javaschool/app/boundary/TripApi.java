package lv.ctco.javaschool.app.boundary;

import lv.ctco.javaschool.app.control.DateTimeCoverter;
import lv.ctco.javaschool.app.control.TripStore;
import lv.ctco.javaschool.app.control.exceptions.ValidationException;
import lv.ctco.javaschool.app.entity.domain.Event;
import lv.ctco.javaschool.app.entity.domain.Place;
import lv.ctco.javaschool.app.entity.domain.Trip;
import lv.ctco.javaschool.app.entity.domain.TripStatus;
import lv.ctco.javaschool.app.entity.dto.EventDto;
import lv.ctco.javaschool.app.entity.dto.JoinTripDto;
import lv.ctco.javaschool.app.entity.dto.ListTripDto;
import lv.ctco.javaschool.app.entity.dto.TripDto;
import lv.ctco.javaschool.auth.control.UserStore;
import lv.ctco.javaschool.auth.entity.domain.User;
import lv.ctco.javaschool.auth.entity.dto.UserDto;
import lv.ctco.javaschool.auth.entity.dto.UserLoginDto;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
@Path("/trip")
public class TripApi {
    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserStore userStore;

    @Inject
    private TripStore tripStore;


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
    @Path("/driver")
    @Produces("application/json")
    @RolesAllowed({"ADMIN", "USER"})
    public ListTripDto getTripsForDriver() {
        User currentUser = userStore.getCurrentUser();
        ListTripDto listTripDto = new ListTripDto();
        listTripDto.setTrips(tripStore.findTripsByUser(currentUser)
                .stream()
                .filter(e -> (e.getTripStatus().equals(TripStatus.ACTIVE)))
                .sorted(Comparator.comparing(Trip::getDepartureTime))
                .map(this::convertToTripDto)
                .collect(Collectors.toList()));
        return listTripDto;
    }

    private TripDto convertToTripDto(Trip trip) {
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

        List<String> passList = new ArrayList<>();
        if (trip.getPassengers() != null) {
            for (User u : trip.getPassengers()) {
                passList.add(u.getName() + " " + u.getSurname());
            }
        }
        dto.setPassengers(passList);
        return dto;
    }

    @POST
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "USER"})
    public void setTripPlacesAndUser(JoinTripDto joinTripDto, @PathParam("id") Long tripId) {
        User user = userStore.getCurrentUser();
        Optional<Trip> tripOptional = tripStore.findTripById(tripId);
        if (tripOptional.isPresent()) {
            Trip trip = tripOptional.get();
            if (trip.getPassengers().contains(user)) {
                throw new ValidationException("The user have already joined this trip");
            } else {
                List<User> passengers = trip.getPassengers();
                passengers.add(user);
                trip.setPassengers(passengers);
                trip.setPlaces(joinTripDto.getPlaces() - 1);
            }
        } else {
            throw new ValidationException("There is no such trip");
        }
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
    public Response createNewTrip(TripDto dto) {
        User user = userStore.getCurrentUser();
        Trip trip = new Trip();
        trip.setDriver(user);
        trip.setEvent(dto.isEvent());
        trip.setDeparture(dto.getFrom());
        trip.setDestination(dto.getTo());
        trip.setPlaces(dto.getPlaces());
        trip.setDepartureTime(dto.getTime());
        trip.setTripStatus(dto.getTripStatus());
        em.persist(trip);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}/passengers")
    @Produces("application/json")
    @RolesAllowed({"ADMIN", "USER"})
    public List<UserLoginDto> getTripPassengersByTripId(@PathParam("id") Long tripId) {
        Optional<Trip> tripOptional = tripStore.findTripById(tripId);
        if (tripOptional.isPresent()) {
            return userStore.findUsersByTrip(tripOptional.get())
                    .stream()
                    .sorted(Comparator.comparing(User::getName))
                    .map(this::convertToUserLoginDto)
                    .collect(Collectors.toList());
        } else {
            throw new ValidationException("There is no such trip");
        }
    }

    private UserLoginDto convertToUserLoginDto(User user) {
        UserLoginDto dto = new UserLoginDto();
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setPhoneNumber(user.getPhoneNumber());
        return dto;
    }

    private UserDto convertToUserDto(User user) {
        UserDto dto = new UserDto();
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        return dto;
    }

    @GET
    @Path("/events")
    @Produces("application/json")
    @RolesAllowed({"ADMIN", "USER"})
    public List<EventDto> getAllEventsForUser() {
        User user = userStore.getCurrentUser();
        return tripStore.findAllEventsForEventPage(user)
                .stream()
                .map(this::convertEventToEventDto)
                .collect(Collectors.toList());
    }

    private EventDto convertEventToEventDto(Event event) {
        EventDto dto = new EventDto();
        dto.setEventName(event.getEventName());
        dto.setEventDate(DateTimeCoverter.covertToDate(event.getEventDateTime()));
        dto.setEventTime(DateTimeCoverter.covertToTime(event.getEventDateTime()));
        dto.setEventPlace(event.getEventDestination());
        dto.setUsernames(new ArrayList<>());
        return dto;
    }

    @POST
    @RolesAllowed({"ADMIN", "USER"})
    @Path("/createEvent")
    public Response createNewEvent(EventDto dto) {
        if ((dto.getEventName().isEmpty()) || (dto.getEventDate().isEmpty())
                || (dto.getEventTime().isEmpty()) || (dto.getUsernames().isEmpty())) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        try {
            LocalDateTime eventDT = DateTimeCoverter.covertToDateTime(dto.getEventDate(), dto.getEventTime());
            if (LocalDateTime.now().isAfter(eventDT)) {
                return Response.status(Response.Status.PARTIAL_CONTENT).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        User creator = userStore.getCurrentUser();
        List<User> userList = new ArrayList<>();
        for (String u : dto.getUsernames()) {
            Optional<User> participant = userStore.findUserByNameAndSurname(u);
            participant.ifPresent(userList::add);
        }
        tripStore.addNewEvent(dto, creator, userList);

        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/getUsers")
    @Produces("application/json")
    @RolesAllowed({"ADMIN", "USER"})
    public List<UserDto> getUsersForEvent() {
        List<User> users = userStore.getAllUsers();
        return users.stream()
                .map(this::convertToUserDto)
                .collect(Collectors.toList());
    }

    @GET
    @Path("/deleteTrip/{id}")
    @RolesAllowed({"ADMIN", "USER"})
    public void deleteTrip(@PathParam("id") Long tripID) {
        Optional<Trip> foundTrip = tripStore.findTripById(tripID);
        System.out.println(tripID);
        if (foundTrip.isPresent()) {
            foundTrip.get().setTripStatus(TripStatus.FINISHED);
        } else
            throw new ValidationException("There is no such trip");
    }
}