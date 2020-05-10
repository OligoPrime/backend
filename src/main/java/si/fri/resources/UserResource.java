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
import java.util.ArrayList;
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
    public List<User> fillUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(dao.create(new User("admin","admin", Roles.ADMIN)));
        userList.add(dao.create(new User("technician","technician", Roles.TECHNICIAN)));
        userList.add(dao.create(new User("researcher","researcher", Roles.RESEARCHER)));
        userList.add(dao.create(new User("guest","guest", Roles.GUEST)));
        return userList;
    }

    @GET
    @UnitOfWork
    public List<User> getAll(){
        return dao.findAll();
    }
}
