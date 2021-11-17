package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.service.AuthenticationService;
import ch.zli.m223.punchclock.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.security.SecureRandom;
import java.util.List;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/User")
public class UserController {

    @Inject
    UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> GetAll() {
        return userService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getSingleEntry(@PathParam Long id) {
        return userService.getEntryById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User CreateUser(User user) {
        return userService.createEntry(user);
    }

    @PUT
    public void update(User user){
        userService.updateEntity(user);
    }

    @DELETE
    @Path("/{id}")
    public void deleteEntry(@PathParam Long id){
        userService.delete(id);
    }
}
