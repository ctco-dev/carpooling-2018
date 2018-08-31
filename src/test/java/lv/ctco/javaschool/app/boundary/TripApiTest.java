package lv.ctco.javaschool.app.boundary;

import lv.ctco.javaschool.app.control.TripStore;
import lv.ctco.javaschool.app.control.exceptions.UserNotFoundException;
import lv.ctco.javaschool.app.entity.domain.Place;
import lv.ctco.javaschool.app.entity.domain.Trip;
import lv.ctco.javaschool.app.entity.domain.TripStatus;
import lv.ctco.javaschool.app.entity.dto.ListTripDto;
import lv.ctco.javaschool.app.entity.dto.TripDto;
import lv.ctco.javaschool.auth.control.UserStore;
import lv.ctco.javaschool.auth.entity.domain.User;
import lv.ctco.javaschool.auth.entity.dto.UserLoginDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.json.Json;
import javax.json.JsonObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TripApiTest {

    @Mock
    private TripStore tripStore;

    @Mock
    private UserStore userStore;

    @InjectMocks
    private TripApi tripApi;

    private List<TripDto> tripDtos;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    void setUp() {
        ListTripDto listTripDto = new ListTripDto();
        tripDtos = new ArrayList<>();
        TripDto tripDto1 = new TripDto("Landa Hans", "1111111", Place.AGENSKALNS, Place.CTCO, 3, "09:00", false, TripStatus.ACTIVE);
        TripDto tripDto2 = new TripDto("Skywalker Anakin", "2222222", Place.CTCO, Place.CENTRS, 2, "18:00", true, TripStatus.ACTIVE);
        TripDto tripDto3 = new TripDto("Bagrov Danila", "3333333", Place.IMANTA, Place.CTCO, 3, "08:30", false, TripStatus.ACTIVE);
        Collections.addAll(tripDtos, tripDto3, tripDto1, tripDto2);
        listTripDto.setTrips(tripDtos);
    }

    @Test
    @DisplayName("Check getting sorted list of TripDto and calling tripStore.findTripsByStatus()")
    void getActiveTrips() {
        List<Trip> trips = new ArrayList<>();
        Trip trip1 = new Trip(new User("Hans", "Landa", "1111111"), Place.AGENSKALNS, Place.CTCO, 3, "09:00", false, TripStatus.ACTIVE);
        Trip trip2 = new Trip(new User("Anakin", "Skywalker", "2222222"), Place.CTCO, Place.CENTRS, 2, "18:00", true, TripStatus.ACTIVE);
        Trip trip3 = new Trip(new User("Danila", "Bagrov", "3333333"), Place.IMANTA, Place.CTCO, 3, "08:30", false, TripStatus.ACTIVE);
        Collections.addAll(trips, trip1, trip2, trip3);
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
    @DisplayName("Check calling tripStore.setTripPlaces() method with the correct arguments")
    void setTripPlacesAndUser() {
        User user = new User();
        when(userStore.getCurrentUser()).thenReturn(user);
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("1", 2)
                .build();
        tripApi.setTripPlacesAndUser(jsonObject, "1");
        verify(tripStore, times(1)).setTripPlacesAndUser(2, user,"1");
    }

    @Test
    @DisplayName("Check getting sorted list of UserLoginDto and calling tripStore.findTripsById() and userStore.findUsersByTrip()")
    void getTripPassengersByTripIdTest() throws UserNotFoundException {
        List<UserLoginDto> userLoginDtos = new ArrayList<>();
        UserLoginDto userLoginDto1 = new UserLoginDto("bastard", "pass1", "Hans", "Landa", "1111111");
        UserLoginDto userLoginDto2 = new UserLoginDto("vader", "pass2", "Anakin", "Skywalker", "2222222");
        UserLoginDto userLoginDto3 = new UserLoginDto("brother", "pass3", "Danila", "Bagrov", "3333333");
        Collections.addAll(userLoginDtos, userLoginDto2, userLoginDto3, userLoginDto1);
        List<User> users = new ArrayList<>();
        User user1 = new User("bastard", "pass1", "Hans", "Landa", "1111111");
        User user2 = new User("vader", "pass2", "Anakin", "Skywalker", "2222222");
        User user3 = new User("brother", "pass3", "Danila", "Bagrov", "3333333");
        Collections.addAll(users, user1, user2, user3);
        Trip trip = new Trip();
        when(tripStore.findTripById(1L)).thenReturn(Optional.of(trip));
        when(userStore.findUsersByTrip(trip)).thenReturn(users);
        tripApi.getTripPassengersByTripId(1L);
        verify(tripStore, times(1)).findTripById(1L);
        verify(userStore, times(1)).findUsersByTrip(trip);
        int i = 0;
        for (UserLoginDto userLoginDto :
                tripApi.getTripPassengersByTripId(1L)) {
            assertEquals(userLoginDtos.get(i), userLoginDto);
            i++;
        }
    }

    @Test
    @DisplayName("Check for throwing the UserNotFoundException when getTripPassengersByTripId() method is called with a nonexistent trip id")
    void getTripPassengersByTripIdTestForNoSuchElementException() {
        assertThrows(UserNotFoundException.class, () -> tripApi.getTripPassengersByTripId(42L));
    }

}