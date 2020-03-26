package si.fri.resources;

import io.dropwizard.hibernate.UnitOfWork;
import si.fri.core.AmountAvailablePackSize;
import si.fri.core.Orientation;
import si.fri.core.PositionInReference;
import si.fri.core.Primer;
import si.fri.db.PrimerDAO;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public Primer fillPrimers() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date newDate = null;
        try {
            newDate = format.parse("31/12/2019");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Primer primer = new Primer("testname", "testsequence", Orientation.REVERSE, 42, "freezer123", 93, "box64", null, 65, 22, null, 42, AmountAvailableUnit.NANOM, newDate);
        return dao.create(null);
    }

    @GET
    @UnitOfWork
    public List<Primer> getAll(){
        return dao.findAll();
    }
}