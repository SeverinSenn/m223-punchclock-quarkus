package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.ViewModel.OptionViewModel;
import ch.zli.m223.punchclock.ViewModel.ProjectViewModel;
import ch.zli.m223.punchclock.ViewModel.UserViewModel;
import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.service.ProjectService;
import ch.zli.m223.punchclock.service.UserService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/Project")
@RolesAllowed("Admin")
public class ProjectController {

    @Inject
    ProjectService projectService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/options")
    public List<OptionViewModel> GetAllOptions() {
        var projects = projectService.findAll();
        return projects.stream().map(x -> new OptionViewModel(x)).collect(Collectors.toList());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProjectViewModel> GetAll() {
        var projects = projectService.findAll();
        return projects.stream().map(x -> new ProjectViewModel(x)).collect(Collectors.toList());
    }

}
