package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.service.AuthenticationService;
import ch.zli.m223.punchclock.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;

@Path("/auth")
public class AuthenticationController {

    @Inject
    AuthenticationService authenticationService;

    @Inject
    UserService userService;

    @POST
    @Path("/Login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String Login(User user){
        if(authenticationService.CheckIfUserExists(user)){
            var fullUser = userService.getEntryByEmail(user.getEmail());
            return authenticationService.GenerateValidJwtToken(fullUser);
        }else{
            throw new NotAuthorizedException("");
        }

    }

}
