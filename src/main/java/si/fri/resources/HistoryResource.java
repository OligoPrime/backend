package si.fri.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import si.fri.core.History;
import si.fri.db.HistoryDAO;

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
    @Timed
    @UnitOfWork
    public List<History> getAll() {
        return historyDAO.findAll();
    }
}
