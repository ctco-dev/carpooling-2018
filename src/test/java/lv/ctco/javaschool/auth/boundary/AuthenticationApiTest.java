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


import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
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
    void login() {

        AuthenticationApi aApi = new AuthenticationApi();

        User u = new User();
        u.setUsername("User1");
        u.setPassword("12345");

        UserLoginDto uld = new UserLoginDto();
        uld.setUsername(u.getUsername());
        uld.setPassword(u.getPassword());

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        Credential credential = new UsernamePasswordCredential(uld.getUsername(), uld.getPassword());

        when(securityContext.authenticate( request, response, AuthenticationParameters.withParams().credential(credential).newAuthentication(true)
                .rememberMe(false))).thenReturn(AuthenticationStatus.SUCCESS);


        assertEquals("User1", aApi.login(uld, request, response));

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