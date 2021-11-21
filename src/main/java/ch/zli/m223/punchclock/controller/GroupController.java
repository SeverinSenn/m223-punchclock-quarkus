package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.ViewModel.OptionViewModel;
import ch.zli.m223.punchclock.service.GroupService;
import ch.zli.m223.punchclock.service.ProjectService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/Group")
@RolesAllowed("Admin")
public class GroupController {

    @Inject
    GroupService groupService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/options")
    public List<OptionViewModel> GetAllOptions() {
        var projects = groupService.findAll();
        return projects.stream().map(x -> new OptionViewModel(x)).collect(Collectors.toList());
    }

}
