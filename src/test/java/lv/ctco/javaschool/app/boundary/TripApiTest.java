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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
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

    private List<User> users, emptyUsers;
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
    List<String> usernames = new ArrayList<>();

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    void setUp() {
        users = new ArrayList<>();
        emptyUsers = new ArrayList<>();
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
        event1 = new Event("Team-building", "22.09.2018", "12:00", Place.BOLDERAJA);
        event2 = new Event("Christmas-party", "23.12.2018", "19:00", Place.CTCO);
        event3 = new Event("Garden-Party", "25.08.2018", "16:00", Place.CTCO);
        Collections.addAll(events, event1, event2, event3);

        usernames.add(user1.getName()+" "+user1.getSurname());
        usernames.add(user2.getName()+" "+user2.getSurname());
        usernames.add(user3.getName()+" "+user3.getSurname());
    }

    @Test
    @DisplayName("Check getting sorted list of TripDto and calling tripStore.findTripsByStatus()")
    void getActiveTrips() {
        List<TripDto> tripDtos = new ArrayList<>();
        TripDto tripDto1 = new TripDto("Hans Landa", "1111111", Place.AGENSKALNS, Place.CTCO, 3, "09:00", false, TripStatus.ACTIVE);
        TripDto tripDto2 = new TripDto("Anakin Skywalker", "2222222", Place.CTCO, Place.CENTRS, 2, "18:00", true, TripStatus.ACTIVE);
        TripDto tripDto3 = new TripDto("Danila Bagrov", "3333333", Place.IMANTA, Place.CTCO, 3, "08:30", false, TripStatus.ACTIVE);
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
        TripDto tripDto1 = new TripDto("Hans Landa", "1111111", Place.AGENSKALNS, Place.CTCO, 3, "09:00", false, TripStatus.ACTIVE);
        TripDto tripDto2 = new TripDto("Hans Landa", "1111111", Place.IMANTA, Place.CTCO, 2, "08:00", false, TripStatus.ACTIVE);
        TripDto tripDto3 = new TripDto("Hans Landa", "1111111", Place.CTCO, Place.IMANTA, 2, "19:00", false, TripStatus.ACTIVE);
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
    void setUserForATripTest() {
        User user = new User();
        trip1.setId(2L);
        trip1.setPassengers(users);
        when(userStore.getCurrentUser()).thenReturn(user);
        when(tripStore.findTripById(2L)).thenReturn(Optional.of(trip1));
        tripApi.setUserForATrip(2L);
        verify(userStore, times(1)).getCurrentUser();
        verify(tripStore, times(1)).findTripById(2L);
    }

    @Test
    @DisplayName("Check for throwing the ValidationException and calling userStore.getCurrentUser(), tripStore.findTripById() methods with the correct arguments")
    void setUserForATripTestFor400ResponseStatusCode() {
        trip2.setId(2L);
        trip2.setPassengers(users);
        when(userStore.getCurrentUser()).thenReturn(user1);
        when(tripStore.findTripById(2L)).thenReturn(Optional.of(trip2));
        assertThrows(ValidationException.class, () -> tripApi.setUserForATrip(2L));
        verify(userStore, times(1)).getCurrentUser();
        verify(tripStore, times(1)).findTripById(2L);
    }

    @Test
    @DisplayName("Check for throwing the ValidationException when setTripPlacesAndUser() method is called with a nonexistent trip id")
    void setUserForATripTestForValidationException() {
        assertThrows(ValidationException.class, () -> tripApi.setUserForATrip(42L));
    }

    @Test
    @DisplayName("Check calling userStore.getCurrentUser(), tripStore.findTripById() methods with the correct arguments")
    void removeUserFromTripTest() {
        trip1.setId(2L);
        trip1.setPassengers(users);
        when(userStore.getCurrentUser()).thenReturn(user1);
        when(tripStore.findTripById(2L)).thenReturn(Optional.of(trip1));
        tripApi.removeUserFromTrip(2L);
        verify(userStore, times(1)).getCurrentUser();
        verify(tripStore, times(1)).findTripById(2L);
    }

    @Test
    @DisplayName("Check for throwing the ValidationException and calling userStore.getCurrentUser(), tripStore.findTripById() methods with the correct arguments")
    void removeUserFromTripTestFor400ResponseStatusCode() {
        trip2.setId(2L);
        trip2.setPassengers(emptyUsers);
        when(userStore.getCurrentUser()).thenReturn(user1);
        when(tripStore.findTripById(2L)).thenReturn(Optional.of(trip2));
        assertThrows(ValidationException.class, () -> tripApi.removeUserFromTrip(2L));
        verify(userStore, times(1)).getCurrentUser();
        verify(tripStore, times(1)).findTripById(2L);
    }

    @Test
    @DisplayName("Check for throwing the ValidationException when removeUserFromTrip() method is called with a nonexistent trip id")
    void removeUserFromTripTestForValidationException() {
        assertThrows(ValidationException.class, () -> tripApi.removeUserFromTrip(42L));
    }

/*    @GET
    @Path("/deleteEvent/{id}")
    @RolesAllowed({"ADMIN", "USER"})
    public void markEventAsDeleted(@PathParam("id") Long eventId) {
        Optional<Event> foundEvent = tripStore.findEventById(eventId);
        if (foundEvent.isPresent()) {
            foundEvent.get().setDeletedStatus(true);
        } else
            throw new ValidationException("There is no such event");
    }
*/


    @Test
    @DisplayName("Check calling userStore.getCurrentUser(), tripStore.findEventById() methods with the correct arguments")
    void markEventAsDeletedTest() {
        event1.setId(2L);
        event1.setDeletedStatus(true);
        when(tripStore.findEventById(2L)).thenReturn(Optional.of(event1));
        tripApi.markEventAsDeleted(2L);

        verify(tripStore, times(1)).findEventById(2L);
        assertFalse( event1.getDeletedStatus() );
    }


    @Test
    @DisplayName("Check for throwing the ValidationException when markEventAsDeleted() method is called with a nonexistent trip id")
    void markEventAsDeletedTestForValidationException() {
        assertThrows(ValidationException.class, () -> tripApi.markEventAsDeleted(42L));
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
    @DisplayName("Check that received Dto's list corresponds with list of events")
    void getAllEventsForUser() {
        when(userStore.getCurrentUser()).thenReturn(user1);
        List<Event> myList = new ArrayList<>();
        when(tripStore.findAllEventsForEventPage(any(User.class))).thenReturn(events);

        List<EventDto> result = tripApi.getAllEventsForUser();
        verify(userStore, times(1)).getCurrentUser();
        verify(tripStore, times(1)).findAllEventsForEventPage(any(User.class));
        assertEquals(3, result.size() );

        assertEquals( event1.getEventName(), result.get(0).getEventName() );
        assertEquals(DateTimeCoverter.covertToDate( event1.getEventDateTime()), result.get(0).getEventDate() );
        assertEquals(DateTimeCoverter.covertToTime( event1.getEventDateTime()), result.get(0).getEventTime() );
        assertEquals( event1.getEventDestination(), result.get(0).getEventPlace() );

        assertEquals( event2.getEventName(), result.get(1).getEventName() );
        assertEquals(DateTimeCoverter.covertToDate( event2.getEventDateTime()), result.get(1).getEventDate() );
        assertEquals(DateTimeCoverter.covertToTime( event2.getEventDateTime()), result.get(1).getEventTime() );
        assertEquals( event2.getEventDestination(), result.get(1).getEventPlace() );

        assertEquals( event3.getEventName(), result.get(2).getEventName() );
        assertEquals(DateTimeCoverter.covertToDate( event3.getEventDateTime()), result.get(2).getEventDate() );
        assertEquals(DateTimeCoverter.covertToTime( event3.getEventDateTime()), result.get(2).getEventTime() );
        assertEquals( event3.getEventDestination(), result.get(2).getEventPlace() );

        assertNotEquals( event2.getEventName(), result.get(0).getEventName() );
        assertNotEquals(DateTimeCoverter.covertToDate( event2.getEventDateTime()), result.get(0).getEventDate() );
        assertNotEquals(DateTimeCoverter.covertToTime( event2.getEventDateTime()), result.get(0).getEventTime() );
        assertNotEquals( event2.getEventDestination(), result.get(0).getEventPlace() );
    }

    @Test
    @DisplayName("Check if getting empty list of events return empty dto")
    void checkIfGettingEventsReturnsEmptyDto(){
        when(userStore.getCurrentUser()).thenReturn(user1);
        when(tripStore.findAllEventsForEventPage(any(User.class))).thenReturn(emptyEvents);
        List<EventDto> result=tripApi.getAllEventsForUser();
        verify(userStore, times(1)).getCurrentUser();
        verify(tripStore, times(1)).findAllEventsForEventPage(any(User.class));
        assertTrue(result.isEmpty());
    }

    private Optional<User> getUserByFullname(String fullname){
        if (fullname.equals(user1.getName() + " " + user1.getSurname())) return Optional.of(user1);
        if (fullname.equals(user2.getName() + " " + user2.getSurname())) return Optional.of(user2);
        if (fullname.equals(user3.getName() + " " + user3.getSurname())) return Optional.of(user3);
        return Optional.empty();
    }

    @Test
    @DisplayName("Check getting Response.Status.NO_CONTENT for new event")
    void createNewEventIsNoContent() {
        when(userStore.getCurrentUser()).thenReturn(user1);
        when(userStore.findUserByNameAndSurname(any(String.class)))
                .thenAnswer(invocation -> {
                    return getUserByFullname( invocation.getArguments()[0].toString());
                });

        EventDto eventDto = new EventDto("", "22.09.2018", "12:00", Place.BOLDERAJA);
        eventDto.setUsernames(usernames);
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), tripApi.createNewEvent(eventDto).getStatus());

        eventDto = new EventDto("Team-building", "", "12:00", Place.BOLDERAJA);
        eventDto.setUsernames(usernames);
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), tripApi.createNewEvent(eventDto).getStatus());

        eventDto = new EventDto("Team-building", "22.09.2018", "", Place.BOLDERAJA);
        eventDto.setUsernames(usernames);
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), tripApi.createNewEvent(eventDto).getStatus());

        eventDto = new EventDto("Team-building", "22.09.2018", "12:00", Place.BOLDERAJA);
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), tripApi.createNewEvent(eventDto).getStatus());

        eventDto = new EventDto("Team-building", "ffdfsf", "12:00", Place.BOLDERAJA);
        eventDto.setUsernames(usernames);
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), tripApi.createNewEvent(eventDto).getStatus());

        eventDto = new EventDto("Team-building", "22.09.2018", "sdfsdf", Place.BOLDERAJA);
        eventDto.setUsernames(usernames);
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), tripApi.createNewEvent(eventDto).getStatus());
    }

    @Test
    @DisplayName("Check getting Response.Status.PARTIAL_CONTENT for new event")
    void createNewEventIsPartialContent() {
        when(userStore.getCurrentUser()).thenReturn(user1);
        when(userStore.findUserByNameAndSurname(any(String.class)))
                .thenAnswer(invocation -> {
                    return getUserByFullname( invocation.getArguments()[0].toString());
                });

        LocalDateTime testDT = LocalDateTime.now().minusMinutes(1);

        EventDto eventDto = new EventDto("Team-building", DateTimeCoverter.covertToDate(testDT),
                DateTimeCoverter.covertToTime(testDT), Place.BOLDERAJA);
        eventDto.setUsernames(usernames);
        assertEquals(Response.Status.PARTIAL_CONTENT.getStatusCode(), tripApi.createNewEvent(eventDto).getStatus());
    }

    @Test
    @DisplayName("Check getting Response.Status.CREATED for new event")
    void createNewEventIsCreated(){
        when(userStore.getCurrentUser()).thenReturn(user1);
        when(userStore.findUserByNameAndSurname(any(String.class)))
                .thenAnswer(invocation -> {
                    return getUserByFullname( invocation.getArguments()[0].toString());
                });

        EventDto eventDto = new EventDto("Team-building", "22.09.2018", "12:00", Place.BOLDERAJA);
        eventDto.setUsernames(usernames);
        int returnedStatus = tripApi.createNewEvent(eventDto).getStatus();
        assertEquals(Response.Status.CREATED.getStatusCode(), returnedStatus);
        verify(userStore, times(1)).getCurrentUser();
        verify(userStore, times(usernames.size())).findUserByNameAndSurname(any(String.class));
        verify(tripStore, times(1)).addNewEvent(any(EventDto.class), any(User.class), anyList());
        assertEquals(Response.Status.CREATED.getStatusCode(), tripApi.createNewEvent(eventDto).getStatus());
    }
}