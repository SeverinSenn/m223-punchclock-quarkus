package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.ViewModel.UserViewModel;
import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.service.AuthenticationService;
import ch.zli.m223.punchclock.service.GroupService;
import ch.zli.m223.punchclock.service.ProjectService;
import ch.zli.m223.punchclock.service.UserService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/User")
@RolesAllowed("Admin")
public class UserController {

    @Inject
    UserService userService;
    @Inject
    ProjectService projectService;
    @Inject
    GroupService groupService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserViewModel> GetAll() {
        var users = userService.findAll();
        return users.stream().map(x -> new UserViewModel(x)).collect(Collectors.toList());
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
    public void CreateUser(UserViewModel user) {
        var newUser = new User();
        user.MapTo(newUser);
        newUser.setProjects(user.getProjectids().stream().map(x ->  projectService.getEntryById(x)).collect(Collectors.toList()));
        newUser.setGroups(user.getGroupids().stream().map(x ->  groupService.getEntryById(x)).collect(Collectors.toList()));
        userService.createEntry(newUser);
    }

    @PUT
    public void update(UserViewModel user){
        var updatingUser = userService.getEntryById(user.getId());
        user.MapTo(updatingUser);
        updatingUser.setProjects(user.getProjectids().stream().map(x ->  projectService.getEntryById(x)).collect(Collectors.toList()));
        updatingUser.setGroups(user.getGroupids().stream().map(x ->  groupService.getEntryById(x)).collect(Collectors.toList()));
        userService.updateEntity(updatingUser);
    }

    @DELETE
    @Path("/{id}")
    public void deleteEntry(@PathParam Long id){
        userService.delete(id);
    }
}
