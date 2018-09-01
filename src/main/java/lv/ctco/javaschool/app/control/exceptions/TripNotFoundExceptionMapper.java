package lv.ctco.javaschool.app.control.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class TripNotFoundExceptionMapper implements ExceptionMapper<TripNotFoundException> {

    @Override
    public Response toResponse(TripNotFoundException ex) {
        return Response.status(404)
                .entity(ex.getMessage())
                .type("text/plain")
                .build();
    }
}
