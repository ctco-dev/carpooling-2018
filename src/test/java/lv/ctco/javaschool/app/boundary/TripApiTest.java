package lv.ctco.javaschool.app.boundary;

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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TripApiTest {

    @Mock
    private TripStore tripStore;

    @Mock
    private UserStore userStore;

    @Mock
    EntityManager em;

    @InjectMocks
    private TripApi tripApi;

    private List<User> users;
    private User user1;
    private User user2;
    private User user3;
    private List<Trip> trips;
    private Trip trip1;
    private Trip trip2;
    private Trip trip3;
    private List<Event> events;
    private List<Event> emptyEvents;
    private Event event1;
    private Event event2;
    private Event event3;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    void setUp() {
        users = new ArrayList<>();
        user1 = new User("bastard", "pass1", "Hans", "Landa", "1111111");
        user2 = new User("vader", "pass2", "Anakin", "Skywalker", "2222222");
        user3 = new User("brother", "pass3", "Danila", "Bagrov", "3333333");
        Collections.addAll(users, user1, user2, user3);

        trips = new ArrayList<>();
        trip1 = new Trip(user1, Place.AGENSKALNS, Place.CTCO, 3, "09:00", false, TripStatus.ACTIVE);
        trip2 = new Trip(user2, Place.CTCO, Place.CENTRS, 2, "18:00", true, TripStatus.ACTIVE);
        trip3 = new Trip(user3, Place.IMANTA, Place.CTCO, 3, "08:30", false, TripStatus.ACTIVE);
        Collections.addAll(trips, trip1, trip2, trip3);

        emptyEvents=new ArrayList<>();
        events = new ArrayList<>();
        event1 = new Event("Team-building", "22/09/2018", "12:00", Place.BOLDERAJA);
        event2 = new Event("Christmas-party", "23/12/2018", "19:00", Place.CTCO);
        event3 = new Event("Garden-Party", "25/08/2018", "16:00", Place.CTCO);
        Collections.addAll(events, event1, event2, event3);

    }

    @Test
    @DisplayName("Check getting sorted list of TripDto and calling tripStore.findTripsByStatus()")
    void getActiveTrips() {
        List<TripDto> tripDtos = new ArrayList<>();
        TripDto tripDto1 = new TripDto("Landa Hans", "1111111", Place.AGENSKALNS, Place.CTCO, 3, "09:00", false, TripStatus.ACTIVE);
        TripDto tripDto2 = new TripDto("Skywalker Anakin", "2222222", Place.CTCO, Place.CENTRS, 2, "18:00", true, TripStatus.ACTIVE);
        TripDto tripDto3 = new TripDto("Bagrov Danila", "3333333", Place.IMANTA, Place.CTCO, 3, "08:30", false, TripStatus.ACTIVE);
        Collections.addAll(tripDtos, tripDto3, tripDto1, tripDto2);
        ListTripDto listTripDto = new ListTripDto();
        listTripDto.setTrips(tripDtos);
        when(tripStore.findTripsByStatus(any(TripStatus.class))).thenReturn(trips);
        int i = 0;
        for (TripDto tripDto :
                tripApi.getActiveTrips().getTrips()) {
            assertEquals(tripDtos.get(i), tripDto);
            i++;
        }
        verify(tripStore, times(1)).findTripsByStatus(TripStatus.ACTIVE);
    }

    @Test
    @DisplayName("Check getting sorted list of TripDto and calling tripStore.findTripsByUser() method with the correct argument")
    void getTripsForDriver() {
        List<Trip> driverTrips = new ArrayList<>();
        Trip trip4 = new Trip(user1, Place.IMANTA, Place.CTCO, 2, "08:00", false, TripStatus.ACTIVE);
        Trip trip5 = new Trip(user1, Place.CTCO, Place.IMANTA, 2, "19:00", false, TripStatus.ACTIVE);
        Collections.addAll(driverTrips, trip1, trip4, trip5);
        List<TripDto> tripDtos = new ArrayList<>();
        TripDto tripDto1 = new TripDto("Landa Hans", "1111111", Place.AGENSKALNS, Place.CTCO, 3, "09:00", false, TripStatus.ACTIVE);
        TripDto tripDto2 = new TripDto("Landa Hans", "1111111", Place.IMANTA, Place.CTCO, 2, "08:00", false, TripStatus.ACTIVE);
        TripDto tripDto3 = new TripDto("Landa Hans", "1111111", Place.CTCO, Place.IMANTA, 2, "19:00", false, TripStatus.ACTIVE);
        Collections.addAll(tripDtos, tripDto2, tripDto1, tripDto3);
        ListTripDto listTripDto = new ListTripDto();
        listTripDto.setTrips(tripDtos);
        when(tripStore.findTripsByUser(user1)).thenReturn(driverTrips);
        when(userStore.getCurrentUser()).thenReturn(user1);
        int i = 0;
        for (TripDto tripDto :
                tripApi.getTripsForDriver().getTrips()) {
            assertEquals(tripDtos.get(i), tripDto);
            i++;
        }
        verify(tripStore, times(1)).findTripsByUser(user1);
    }

    @Test
    @DisplayName("Check calling userStore.getCurrentUser(), tripStore.findTripById() methods with the correct arguments")
    void setTripPlacesAndUserTest() {
        User user = new User();
        JoinTripDto joinTripDto = new JoinTripDto();
        joinTripDto.setPlaces(2);
        trip1.setId(2L);
        trip1.setPassengers(users);
        when(userStore.getCurrentUser()).thenReturn(user);
        when(tripStore.findTripById(2L)).thenReturn(Optional.of(trip1));
        tripApi.setTripPlacesAndUser(joinTripDto, 2L);
        verify(userStore, times(1)).getCurrentUser();
        verify(tripStore, times(1)).findTripById(2L);
    }

    @Test
    @DisplayName("Check for throwing the ValidationException and calling userStore.getCurrentUser(), tripStore.findTripById() methods with the correct arguments")
    void setTripPlacesAndUserTestForFor405ResponseStatusCode() {
        JoinTripDto joinTripDto = new JoinTripDto();
        joinTripDto.setPlaces(2);
        trip2.setId(2L);
        trip2.setPassengers(users);
        when(userStore.getCurrentUser()).thenReturn(user1);
        when(tripStore.findTripById(2L)).thenReturn(Optional.of(trip2));
        assertThrows(ValidationException.class, () -> tripApi.setTripPlacesAndUser(joinTripDto, 2L));
        verify(userStore, times(1)).getCurrentUser();
        verify(tripStore, times(1)).findTripById(2L);
    }

    @Test
    @DisplayName("Check for throwing the ValidationException when setTripPlacesAndUser() method is called with a nonexistent trip id")
    void setTripPlacesAndUserTestForValidationException() {
        assertThrows(ValidationException.class, () -> tripApi.setTripPlacesAndUser(new JoinTripDto(), 42L));
    }

    @Test
    @DisplayName("Check getting sorted list of UserLoginDto and calling tripStore.findTripsById() and userStore.findUsersByTrip()")
    void getTripPassengersByTripIdTest() {
        List<UserLoginDto> userLoginDtos = new ArrayList<>();
        UserLoginDto userLoginDto1 = new UserLoginDto("bastard", "pass1", "Hans", "Landa", "1111111");
        UserLoginDto userLoginDto2 = new UserLoginDto("vader", "pass2", "Anakin", "Skywalker", "2222222");
        UserLoginDto userLoginDto3 = new UserLoginDto("brother", "pass3", "Danila", "Bagrov", "3333333");
        Collections.addAll(userLoginDtos, userLoginDto2, userLoginDto3, userLoginDto1);
        when(tripStore.findTripById(1L)).thenReturn(Optional.of(trip3));
        when(userStore.findUsersByTrip(trip3)).thenReturn(users);
        tripApi.getTripPassengersByTripId(1L);
        verify(tripStore, times(1)).findTripById(1L);
        verify(userStore, times(1)).findUsersByTrip(trip3);
        int i = 0;
        for (UserLoginDto userLoginDto :
                tripApi.getTripPassengersByTripId(1L)) {
            assertEquals(userLoginDtos.get(i), userLoginDto);
            i++;
        }
    }

    @Test
    @DisplayName("Check for throwing the ValidationException when getTripPassengersByTripId() method is called with a nonexistent trip id")
    void getTripPassengersByTripIdTestForUserNotFoundException() {
        assertThrows(ValidationException.class, () -> tripApi.getTripPassengersByTripId(42L));
    }

    @Test
    @DisplayName("Check getting Response.Status.CREATED and calling userStore.getCurrentUser(), em.persist() methods")
    void createNewTrip() {
        when(userStore.getCurrentUser()).thenReturn(user1);
        TripDto tripDto = new TripDto("Landa Hans", "1111111", Place.AGENSKALNS, Place.CTCO, 3, "09:00", false, TripStatus.ACTIVE);
        assertEquals(Response.Status.CREATED.getStatusCode(), tripApi.createNewTrip(tripDto).getStatus());
        verify(userStore, times(1)).getCurrentUser();
        verify(em, times(1)).persist(any(Trip.class));
    }

    @Test
    @DisplayName("Check getting Users for event by calling userStore.getAllUsers() and convert data into dto")
    void getAllUsersForEvent() {
        when(userStore.getAllUsers())
                .thenReturn(users);
        List<UserDto> result = tripApi.getUsersForEvent();
        assertEquals(3, result.size());

        UserDto userDto = result.get(0);
        assertEquals("Hans", userDto.getName());
        assertEquals("Landa", userDto.getSurname());

        UserDto userDto1 = result.get(1);
        assertEquals("Anakin", userDto1.getName());
        assertEquals("Skywalker", userDto1.getSurname());

        UserDto userDto2 = result.get(2);
        assertEquals("Danila", userDto2.getName());
        assertEquals("Bagrov", userDto2.getSurname());
    }

    @Test
    @DisplayName("Check getting Response.Status.CREATED for new event")
    void checkCreatingNewEvent() {
        EventDto eventDto = new EventDto("Team-building", "22/09/2018", "12:00", Place.BOLDERAJA);
        assertEquals(Response.Status.CREATED.getStatusCode(), tripApi.createNewEvent(eventDto).getStatus());
        verify(em, times(1)).persist(any(Event.class));
    }

    @Test
    @DisplayName("Check getting all events from db ")
    void checkGettigEvents() {
        when(tripStore.findAllEvents()).thenReturn(events);
        List<EventDto> result=tripApi.getAllEvents();
        assertEquals(3,result.size());

        EventDto eventDto=result.get(0);
        assertEquals("Team-building",eventDto.getEventName());
        assertEquals("22/09/2018",eventDto.getEventDate());
        assertEquals("12:00",eventDto.getEventTime());
        assertEquals(Place.BOLDERAJA,eventDto.getEventPlace());

        EventDto eventDto1=result.get(1);
        assertEquals("Christmas-party",eventDto1.getEventName());
        assertEquals("23/12/2018",eventDto1.getEventDate());
        assertEquals("19:00",eventDto1.getEventTime());
        assertEquals(Place.CTCO,eventDto1.getEventPlace());

        EventDto eventDto2=result.get(2);
        assertEquals("Garden-Party",eventDto2.getEventName());
        assertEquals("25/08/2018",eventDto2.getEventDate());
        assertEquals("16:00",eventDto2.getEventTime());
        assertEquals(Place.CTCO,eventDto2.getEventPlace());
    }

    @Test
    @DisplayName("Check if getting of event return empty dto")
    void checkIfGettingEventsReturnsEmptyDto(){
        when(tripStore.findAllEvents()).thenReturn(emptyEvents);
        List<EventDto> result=tripApi.getAllEvents();
        assertTrue(result.isEmpty());
    }

}