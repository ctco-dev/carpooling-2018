package lv.ctco.javaschool.auth.boundary;

import lv.ctco.javaschool.auth.control.UserStore;
import lv.ctco.javaschool.auth.entity.domain.Role;
import lv.ctco.javaschool.auth.entity.domain.User;
import lv.ctco.javaschool.auth.entity.dto.ErrorDto;
import lv.ctco.javaschool.auth.entity.dto.UserLoginDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AuthenticationApiTest extends Mockito {

    @Mock
    UserStore userStore;

    @Mock
    SecurityContext securityContext;

    @InjectMocks
    AuthenticationApi authApi;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void login() throws Exception {
        User u = new User();
        u.setUsername("User1");
        u.setPassword("12345");
        UserLoginDto uld = new UserLoginDto();
        uld.setUsername(u.getUsername());
        uld.setPassword(u.getPassword());
        HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockedResponse = mock(HttpServletResponse.class);
        when(mockedRequest.getParameter("username")).thenReturn("testUsername");
        when(mockedRequest.getParameter("password")).thenReturn("testPass");
        Response response = Response
                .status(Response.Status.UNAUTHORIZED)
                .entity(new ErrorDto())
                .build();
        assertEquals(response.getStatus(), authApi.login(uld, mockedRequest, mockedResponse).getStatus());
        verify(userStore, never()).createUser(anyString(), anyString(), anyString(), anyString(), anyString(), any(Role.class));
        authApi.login(new UserLoginDto("testUserame", "testPass", "testName", "testSurname", "testPass"), mockedRequest, mockedResponse);
        verify(securityContext, times(2)).authenticate(any(HttpServletRequest.class), any(HttpServletResponse.class), any(AuthenticationParameters.class));
    }

    @Test
    void register() throws Exception {
        HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockedResponse = mock(HttpServletResponse.class);
        when(mockedRequest.getParameter("username")).thenReturn("testUsername");
        when(mockedRequest.getParameter("password")).thenReturn("testPass");
        Response response = Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorDto())
                .build();
        assertEquals(response.getStatus(), authApi.register(new UserLoginDto(), mockedRequest, mockedResponse).getStatus());
        verify(userStore, never()).createUser(anyString(), anyString(), anyString(), anyString(), anyString(), any(Role.class));
        authApi.register(new UserLoginDto("testUserame", "testPass", "testName", "testSurname", "testPass"), mockedRequest, mockedResponse);
        verify(userStore, times(1)).createUser(anyString(), anyString(), anyString(), anyString(), anyString(), any(Role.class));
    }

    @Test
    void logout() throws Exception {
        HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
        when(mockedRequest.getParameter("username")).thenReturn("testUsername");
        when(mockedRequest.getParameter("password")).thenReturn("testPass");
        Response response = Response
                .status(Response.Status.OK)
                .entity(new ErrorDto())
                .build();
        assertEquals(response.getStatus(), authApi.logout(mockedRequest).getStatus());
        verify(mockedRequest, times(1)).logout();
    }

}