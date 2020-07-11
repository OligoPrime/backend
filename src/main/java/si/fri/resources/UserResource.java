package si.fri.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import si.fri.core.Roles;
import si.fri.core.User;
import si.fri.db.UserDAO;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<User> fill() {
        List<User> userList = new ArrayList<>();
        userList.add(dao.create(new User("admin","admin", Roles.ADMIN)));
        userList.add(dao.create(new User("technician","technician", Roles.TECHNICIAN)));
        userList.add(dao.create(new User("researcher","researcher", Roles.RESEARCHER)));
        userList.add(dao.create(new User("student","student", Roles.STUDENT)));
        userList.add(dao.create(new User("guest","guest", Roles.GUEST)));
        return userList;
    }

    @POST
    @Path("/add")
    @Produces(MediaType.TEXT_PLAIN)
    @UnitOfWork
    public Response add(NewUserJSON data) {
        if (data.username.isEmpty() || data.password.isEmpty() || data.role.isEmpty()  || data.name.isEmpty()  || data.workTitle.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        User u = new User(data.username,data.password,data.role,data.name,data.workTitle);
        dao.create(u);
        return Response.ok("Added user").build();
    }

    @POST
    @Path("/delete")
    @Produces(MediaType.TEXT_PLAIN)
    @UnitOfWork
    @RolesAllowed({Roles.ADMIN})
    public Response delete(String username, @Auth User user) {
        dao.delete(username, user);
        return Response.ok("Deleted user").build();
    }


    @GET
    @Path("/usernames")
    @UnitOfWork
    public List<String> getAllUsernames() {
        return dao.findAll().stream().filter(user -> !user.isRemoved()).map(User::getUsername).collect(Collectors.toList());
    }

    @GET
    @UnitOfWork
    public List<UserJSON> getAll() {
        return dao.findAll().stream().filter(user -> !user.isRemoved()).map(UserJSON::new).collect(Collectors.toList());
    }

    public static class NewUserJSON implements Serializable {
        @JsonProperty
        String username;
        @JsonProperty
        String password;
        @JsonProperty
        String role;
        @JsonProperty
        String name;
        @JsonProperty
        String workTitle;
    }

    public static class UserJSON implements Serializable {
        @JsonProperty
        String username;
        @JsonProperty
        String role;
        @JsonProperty
        String name;
        @JsonProperty
        String workTitle;

        public UserJSON(User u) {
            username = u.getUsername();
            role = u.getRole();
            name = u.getName();
            workTitle = u.getWorkTitle();
        }
    }


}
