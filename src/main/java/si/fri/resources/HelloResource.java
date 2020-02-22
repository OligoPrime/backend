package si.fri.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import si.fri.core.Hello;
import si.fri.db.HelloDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
public class HelloResource {
    private final String template;
    private final String defaultName;

    private final HelloDAO helloDAO;

    public HelloResource(String template, String defaultName, HelloDAO helloDAO) {
        this.template = template;
        this.defaultName = defaultName;
        this.helloDAO = helloDAO;
    }

    @POST
    @Timed
    @UnitOfWork
    public Hello sayHello(@QueryParam("name") Optional<String> name) {
        final String value = String.format(template, name.orElse(defaultName));
        Hello newHello = new Hello(value);
        return helloDAO.create(newHello);
    }

    @GET
    @Path("/all")
    @Timed
    @UnitOfWork
    public List<Hello> getAll() {
        return helloDAO.findAll();
    }
}
