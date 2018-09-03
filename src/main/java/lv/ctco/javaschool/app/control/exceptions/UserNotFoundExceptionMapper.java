package lv.ctco.javaschool.app.control.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UserNotFoundExceptionMapper implements ExceptionMapper<UserNotFoundException> {

    @Override
    public Response toResponse(UserNotFoundException ex) {
        return Response.status(404)
                .entity(ex.getMessage())
                .type("text/plain")
                .build();
    }
}
