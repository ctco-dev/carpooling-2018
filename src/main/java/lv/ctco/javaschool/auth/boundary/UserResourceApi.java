package lv.ctco.javaschool.auth.boundary;

import lv.ctco.javaschool.app.entity.domain.Car;
import lv.ctco.javaschool.auth.control.UserStore;
import lv.ctco.javaschool.auth.entity.domain.User;
import lv.ctco.javaschool.auth.entity.dto.UserDto;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("/user")
public class UserResourceApi {
    @Inject
    private UserStore userStore;

    @PersistenceContext
    private EntityManager em;

    @GET
    @Path("/list")
    @Produces("application/json")
    @RolesAllowed("ADMIN")
    public List<User> getUserList() {
        return userStore.getAllUsers();
    }

    @POST
    @RolesAllowed({"ADMIN", "USER"})
    @Path("/update")
    public Response updateUserData(UserDto userDto) {
        User user = userStore.getCurrentUser();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setPhoneNumber(userDto.getPhoneNumber());
        Car car = user.getCar();
        car.setCarModel(userDto.getCarModel());
        car.setCarColor(userDto.getCarColor());
        car.setCarNumber(userDto.getCarNumber());
        em.merge(user);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/current")
    @Produces("application/json")
    @RolesAllowed({"ADMIN", "USER"})
    public UserDto getCurrentUserData() {
        UserDto userDto = new UserDto();
        User currentUser = userStore.getCurrentUser();
        Car car = currentUser.getCar();
        userDto.setName(currentUser.getName());
        userDto.setSurname(currentUser.getSurname());
        userDto.setPhoneNumber(currentUser.getPhoneNumber());
        userDto.setCarModel(car.getCarModel());
        userDto.setCarColor(car.getCarColor());
        userDto.setCarNumber(car.getCarNumber());
        return userDto;
    }
}
