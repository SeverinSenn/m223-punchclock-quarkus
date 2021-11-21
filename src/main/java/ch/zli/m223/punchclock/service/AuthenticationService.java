package ch.zli.m223.punchclock.service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ch.zli.m223.punchclock.domain.User;
import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;

@RequestScoped
public class AuthenticationService {
    @Inject
    private EntityManager entityManager;
    @Inject
    private UserService userService;
    @Inject
    private JsonWebToken jwt;

    public Boolean CheckIfUserExists(User u){
        var query =
                entityManager.createQuery("SELECT COUNT(*) FROM User WHERE email = :email and passwort = :passwort")
                .setParameter("email",u.getEmail())
                .setParameter("passwort",u.getPasswort());
        var res = query.getSingleResult();
        return (Long)res == 1;
    }

    public String GenerateValidJwtToken(User user){
        List<String> permissions = new ArrayList<String>();
        permissions.add("User");
        if(user.getIs_Admin()){
            permissions.add("Admin");
        }
        String token =
            Jwt.issuer("https://zli.ch/issuer") 
            .upn(user.getEmail())
            .groups(new HashSet<>(permissions))
            .claim(Claims.birthdate.name(), "2001-07-13")
            .expiresIn(Duration.ofHours(1)) 
            .sign();
        return token;
    }

    public User getUser(){
        var email = jwt.getClaim("upn").toString();
        return userService.getEntryByEmail(email);
    }

}
