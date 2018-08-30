package lv.ctco.javaschool.app.boundary;

import lv.ctco.javaschool.app.control.TripStore;
import lv.ctco.javaschool.app.entity.domain.Place;
import lv.ctco.javaschool.app.entity.domain.Trip;
import lv.ctco.javaschool.app.entity.domain.TripStatus;
import lv.ctco.javaschool.app.entity.dto.ListTripDto;
import lv.ctco.javaschool.app.entity.dto.TripDto;
import lv.ctco.javaschool.auth.entity.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TripApiTest {

    @Mock
    private TripStore tripStore;

    @InjectMocks
    private TripApi tripApi;

    private List<TripDto> tripDtos;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

//    @BeforeEach
//    void setUp() {
//        ListTripDto listTripDto = new ListTripDto();
//        tripDtos = new ArrayList<>();
//        TripDto tripDto1 = new TripDto("Landa Hans", "1111111", Place.AGENSKALNS, Place.CTCO, 3, "09:00", false, TripStatus.ACTIVE);
//        TripDto tripDto2 = new TripDto("Skywalker Anakin", "2222222", Place.CTCO, Place.CENTRS, 2, "18:00", true, TripStatus.ACTIVE);
//        TripDto tripDto3 = new TripDto("Bagrov Danila", "3333333", Place.IMANTA, Place.CTCO, 3, "08:30", false, TripStatus.ACTIVE);
//        Collections.addAll(tripDtos, tripDto3, tripDto1, tripDto2);
//        listTripDto.setTrips(tripDtos);
//    }
//
//    @Test
//    @DisplayName("Check getting sorted list of TripDto and calling tripStore.findTripsByStatus()")
//    void getActiveTrips() {
//        List<Trip> trips = new ArrayList<>();
//        Trip trip1 = new Trip(new User("Hans", "Landa", "1111111"), Place.AGENSKALNS, Place.CTCO, 3, "09:00", false, TripStatus.ACTIVE);
//        Trip trip2 = new Trip(new User("Anakin", "Skywalker", "2222222"), Place.CTCO, Place.CENTRS, 2, "18:00", true, TripStatus.ACTIVE);
//        Trip trip3 = new Trip(new User("Danila", "Bagrov", "3333333"), Place.IMANTA, Place.CTCO, 3, "08:30", false, TripStatus.ACTIVE);
//        Collections.addAll(trips, trip1, trip2, trip3);
//        when(tripStore.findTripsByStatus(any(TripStatus.class))).thenReturn(trips);
//        int i = 0;
//        for (TripDto tripDto :
//                tripApi.getActiveTrips().getTrips()) {
//            assertEquals(tripDtos.get(i), tripDto);
//            i++;
//        }
//        verify(tripStore, times(1)).findTripsByStatus(TripStatus.ACTIVE);
//    }
}