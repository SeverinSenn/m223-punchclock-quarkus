package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.ViewModel.OptionViewModel;
import ch.zli.m223.punchclock.ViewModel.ProjectViewModel;
import ch.zli.m223.punchclock.ViewModel.TimeViewModel;
import ch.zli.m223.punchclock.domain.Project;
import ch.zli.m223.punchclock.domain.Time;
import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.service.*;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/Time")
@RolesAllowed("User")
public class TimeController {

    @Inject
    private TimeService timeService;
    @Inject
    private AuthenticationService authenticationService;
    @Inject
    private ProjectService projectService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TimeViewModel> GetAll() {
        var user = authenticationService.getUser();
        var times = timeService.findAllByUser(user);
        return times.stream().map(x -> new TimeViewModel(x)).collect(Collectors.toList());
    }

    @DELETE
    @Path("/{id}")
    public void deleteEntry(@PathParam Long id){
        timeService.delete(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void Create(TimeViewModel time) {
        var newTime = new Time();
        time.MapTo(newTime);
        newTime.setProject(projectService.getEntryById(time.getProjectId()));
        newTime.setUser(authenticationService.getUser());
        timeService.createEntry(newTime);
    }

    @PUT
    public void update(TimeViewModel time){
        var updatingTime = timeService.getEntryById(time.getId());
        time.MapTo(updatingTime);
        updatingTime.setProject(projectService.getEntryById(time.getProjectId()));
        timeService.updateEntity(updatingTime);
    }

}
