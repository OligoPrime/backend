package si.fri.resources;

import io.dropwizard.hibernate.UnitOfWork;
import si.fri.core.Primer;
import si.fri.db.PrimerDAO;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/primers")
@Produces(MediaType.APPLICATION_JSON)
public class PrimerResource {

    private final PrimerDAO dao;

    public PrimerResource(PrimerDAO dao) {
        this.dao = dao;
    }

    @POST
    @Path("/fill")
    @UnitOfWork
    public Primer fillUsers(){
        return dao.create(new Primer("test"));
    }

    @GET
    @UnitOfWork
    public List<Primer> getAll(){
        return dao.findAll();
    }
}