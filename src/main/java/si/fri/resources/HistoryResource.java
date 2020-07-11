package si.fri.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import si.fri.core.History;
import si.fri.core.Roles;
import si.fri.db.HistoryDAO;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/history")
@Produces(MediaType.APPLICATION_JSON)
public class HistoryResource {

    private final HistoryDAO historyDAO;

    public HistoryResource(HistoryDAO historyDAO) {
        this.historyDAO = historyDAO;
    }

    @GET
    @Path("/all")
    @Timed
    @UnitOfWork
    @RolesAllowed({Roles.ADMIN, Roles.TECHNICIAN, Roles.RESEARCHER, Roles.STUDENT, Roles.GUEST})
    public List<History> getAll() {
        return historyDAO.findAll();
    }

    @GET
    @Path("/user")
    @Timed
    @UnitOfWork
    @RolesAllowed({Roles.ADMIN, Roles.TECHNICIAN, Roles.RESEARCHER, Roles.STUDENT})
    public List<History> getAll(@QueryParam("username") String username) {
        return historyDAO.findByUser(username);
    }

}
