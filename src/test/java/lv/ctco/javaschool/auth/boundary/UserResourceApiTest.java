package lv.ctco.javaschool.auth.boundary;

import lv.ctco.javaschool.app.entity.domain.Car;
import lv.ctco.javaschool.auth.control.UserStore;
import lv.ctco.javaschool.auth.entity.domain.User;
import lv.ctco.javaschool.auth.entity.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserResourceApiTest {

    @Mock
    private UserStore userStore;

    @Mock
    private EntityManager em;

    @InjectMocks
    private UserResourceApi userResourceApi;

    private User user;
    private UserDto userDto;
    private Car userCar;


    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        user = new User("fighter", "pass3", "Tyler", "Durden", "4444444");
        userDto = new UserDto("Tyler", "Durden", "4444444", "BMV X5", "black", "BOOMER");
        userCar = new Car("BMV X5", "black", "BOOMER");
    }

    @Test
    @DisplayName("Check getting Response.Status.OK and calling userStore.getCurrentUser(), em.merge() methods with the correct argument")
    void updateUserData() {
        user.setCar(userCar);
        when(userStore.getCurrentUser()).thenReturn(user);
        assertEquals(Response.Status.OK.getStatusCode(), userResourceApi.updateUserData(userDto).getStatus());
        verify(userStore, times(1)).getCurrentUser();
        verify(em, times(1)).merge(user);
    }

    @Test
    @DisplayName("Check getting correct UserDto and calling userStore.getCurrentUser() method")
    void getCurrentUserData() {
        user.setCar(userCar);
        when(userStore.getCurrentUser()).thenReturn(user);
        assertEquals(userDto, userResourceApi.getCurrentUserData());
        verify(userStore, times(1)).getCurrentUser();
    }
}