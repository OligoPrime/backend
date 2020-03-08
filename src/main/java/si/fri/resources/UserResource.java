package si.fri.resources;

import io.dropwizard.hibernate.UnitOfWork;
import si.fri.core.Roles;
import si.fri.core.User;
import si.fri.db.UserDAO;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserDAO dao;

    public UserResource(UserDAO dao) {
        this.dao = dao;
    }

    @POST
    @Path("/fill")
    @UnitOfWork
    public User fillUsers(){
        return dao.create(new User("test","t",Roles.ADMIN));
    }

    @GET
    @UnitOfWork
    public List<User> getAll(){
        return dao.findAll();
    }
}
