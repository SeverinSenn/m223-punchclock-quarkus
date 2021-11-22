package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.ViewModel.OptionViewModel;
import ch.zli.m223.punchclock.ViewModel.ProjectViewModel;
import ch.zli.m223.punchclock.ViewModel.UserViewModel;
import ch.zli.m223.punchclock.domain.Project;
import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.service.GroupService;
import ch.zli.m223.punchclock.service.ProjectService;
import ch.zli.m223.punchclock.service.UserService;
import io.quarkus.security.identity.SecurityIdentity;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/Project")
@RolesAllowed("Admin")
public class ProjectController {

    @Inject
    ProjectService projectService;
    @Inject
    GroupService groupService;
    @Inject
    UserService userService;
    @Inject
    JsonWebToken jwt;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/options")
    @Operation(summary = "gets all Projects as a OptionViewModel", description = "This is used for the selects in the Frontend")
    public List<OptionViewModel> GetAllOptions() {
        var projects = projectService.findAll();
        return projects.stream().map(x -> new OptionViewModel(x)).collect(Collectors.toList());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/users")
    @PermitAll
    @Operation(summary = "gets all Projects for User", description = "This Request gets you all Projects for a user")
    public List<OptionViewModel> GetAllUserOptions() {
        var email = jwt.getClaim("upn").toString();
        var user = userService.getEntryByEmail(email);
        var project = user.getProjects().stream().map(x -> new OptionViewModel(x)).collect(Collectors.toList());
        for (var group: user.getGroups()) {
            var p = group.getProjects().stream().map(x -> new OptionViewModel(x)).collect(Collectors.toList());
            project = Stream.concat(project.stream(), p.stream()) .collect(Collectors.toList());
        }
        //clear list of Duplicate Element
        var res = new ArrayList<OptionViewModel>();
        for (var p: project) {
            if(!res.stream().anyMatch(x -> x.getValue().equalsIgnoreCase(p.getValue()))){
                res.add(p);
            }
        }
        return res;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "gets all Projects")
    public List<ProjectViewModel> GetAll() {
        var projects = projectService.findAll();
        return projects.stream().map(x -> new ProjectViewModel(x)).collect(Collectors.toList());
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete Project")
    public void deleteEntry(@PathParam Long id){
        projectService.delete(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates Project")
    public void Create(ProjectViewModel project) {
        var newProject = new Project();
        project.MapTo(newProject);
        newProject.setGroups(project.getGroupids().stream().map(x ->  groupService.getEntryById(x)).collect(Collectors.toList()));
        newProject.setUsers(project.getUserids().stream().map(x ->  userService.getEntryById(x)).collect(Collectors.toList()));
        projectService.createEntry(newProject);
    }

    @PUT
    @Operation(summary = "Updates Project")
    public void update(ProjectViewModel project){
        var updatingProject = projectService.getEntryById(project.getId());
        project.MapTo(updatingProject);
        updatingProject.setGroups(project.getGroupids().stream().map(x ->  groupService.getEntryById(x)).collect(Collectors.toList()));
        updatingProject.setUsers(project.getUserids().stream().map(x ->  userService.getEntryById(x)).collect(Collectors.toList()));
        projectService.updateEntity(updatingProject);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/GetProjectsNamesLen3List")
    @PermitAll
    @Operation(summary = "Gets a list of Projects Names",description = "gets you a List of Project names how have Admin and have a length of 3")
    public List<String> GetProjectsNamesLen3List(){
        var res = projectService.GetProjectsNamesLen3List();
        return res;
    }




}
