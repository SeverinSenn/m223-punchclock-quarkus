package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.ViewModel.LoginViewModel;
import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.service.AuthenticationService;
import ch.zli.m223.punchclock.service.UserService;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/auth")
public class AuthenticationController {

    @Inject
    AuthenticationService authenticationService;

    @Inject
    UserService userService;

    @POST
    @Path("/Login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public LoginViewModel Login(User user){
        var model = new LoginViewModel();
        if(user.getPasswort().isEmpty()){
            if(authenticationService.CheckIfUserExistsWithoutPW(user)){
                var fullUser = userService.getEntryByEmail(user.getEmail());
                if(fullUser.getPasswort() == null){
                    model.setHasPasswort(false);
                }
                model.setJwt(authenticationService.GenerateValidJwtToken(fullUser));
                return model;
            }else{
                throw new NotAuthorizedException("");
            }
        }else{
            if(authenticationService.CheckIfUserExists(user)){
                var fullUser = userService.getEntryByEmail(user.getEmail());
                model.setJwt(authenticationService.GenerateValidJwtToken(fullUser));
                return model;
            }else{
                throw new NotAuthorizedException("");
            }
        }
    }

    @POST
    @Path("/setpasswort")
    public void SetPasswort(User model){
        var user = authenticationService.getUser();
        user.setPasswort(model.getPasswort());
        userService.updateEntity(user);
    }

}
