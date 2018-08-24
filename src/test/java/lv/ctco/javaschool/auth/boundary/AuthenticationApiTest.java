package lv.ctco.javaschool.auth.boundary;

import lv.ctco.javaschool.auth.control.UserStore;
import lv.ctco.javaschool.auth.entity.domain.User;
import lv.ctco.javaschool.auth.entity.dto.UserLoginDto;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.omg.PortableInterceptor.SUCCESSFUL;

import javax.security.enterprise.SecurityContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


class AuthenticationApiTest extends Mockito {

    @Mock
    UserStore userStore;

    @Mock
    SecurityContext securityContext;

    @InjectMocks
    AuthenticationApi authApi;

    /*@Test
    void login() {

        User u = new User();
        u.setUsername("User1");
        u.setPassword("12345");

        UserLoginDto uld = new UserLoginDto();
        uld.setUsername(u.getUsername());
        uld.setPassword(u.getPassword());

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);



        //when(securityContext.authenticate(request, response, )).thenReturn();

       // assertEquals("User1", "12345", login(uld, request, response));

        verify(request, atLeastOnce()).getParameter("username");
    }*/

}