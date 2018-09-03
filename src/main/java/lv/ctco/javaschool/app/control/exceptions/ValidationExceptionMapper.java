package lv.ctco.javaschool.app.control.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    @Override
    public Response toResponse(ValidationException ex) {
        return Response.status(404)
                .entity(ex.getMessage())
                .type("text/plain")
                .build();
    }
}
