package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.ViewModel.GroupViewModel;
import ch.zli.m223.punchclock.ViewModel.OptionViewModel;
import ch.zli.m223.punchclock.ViewModel.UserViewModel;
import ch.zli.m223.punchclock.domain.Project;
import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.domain.UserGroup;
import ch.zli.m223.punchclock.service.GroupService;
import ch.zli.m223.punchclock.service.ProjectService;
import ch.zli.m223.punchclock.service.UserService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.swing.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/Group")
@RolesAllowed("Admin")
public class GroupController {

    @Inject
    GroupService groupService;
    @Inject
    UserService userService;
    @Inject
    ProjectService projectService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/options")
    @Operation(summary = "gets all Groups as a OptionViewModel", description = "This is used for the selects in the Frontend")
    public List<OptionViewModel> GetAllOptions() {
        var projects = groupService.findAll();
        return projects.stream().map(x -> new OptionViewModel(x)).collect(Collectors.toList());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Gets All Groups", description = " gets all Groups as a GroupViewModel")
    public List<GroupViewModel> GetAll() {
        var groups = groupService.findAll();
        return groups.stream().map(x -> new GroupViewModel(x)).collect(Collectors.toList());
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delets Group")
    public void deleteEntry(@PathParam Long id){
        groupService.delete(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates Group")
    public void CreateGroup(GroupViewModel group) {
        var newUserGroup = new UserGroup();
        group.MapTo(newUserGroup);
        newUserGroup.setUsers(group.getUserids().stream().map(x ->  userService.getEntryById(x)).collect(Collectors.toList()));
        newUserGroup.setProjects(group.getProjectids().stream().map(x ->  projectService.getEntryById(x)).collect(Collectors.toList()));
        groupService.createEntry(newUserGroup);
    }

    @PUT
    @Operation(summary = "Updates Group")
    public void update(GroupViewModel group){
        var updatingUserGroup = groupService.getEntryById(group.getId());
        group.MapTo(updatingUserGroup);
        updatingUserGroup.setUsers(group.getUserids().stream().map(x ->  userService.getEntryById(x)).collect(Collectors.toList()));
        updatingUserGroup.setProjects(group.getProjectids().stream().map(x ->  projectService.getEntryById(x)).collect(Collectors.toList()));
        groupService.updateEntity(updatingUserGroup);
    }

}
