package ch.zli.m223.punchclock.service;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ch.zli.m223.punchclock.domain.User;
import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.jwt.Claims;

@RequestScoped
public class AuthenticationService {
    @Inject
    private EntityManager entityManager;

    public Boolean CheckIfUserExists(User u){
        var query =
                entityManager.createQuery("SELECT COUNT(*) FROM User WHERE username = :username and passwort = :pw")
                .setParameter("username",u.getEmail())
                .setParameter("pw",u.getPasswort());
        var res = query.getSingleResult();
        return (Long)res == 1;
    }

    public String GenerateValidJwtToken(String username){
        String token =
            Jwt.issuer("https://zli.ch/issuer") 
            .upn(username) 
            .groups(new HashSet<>(Arrays.asList("User", "Admin"))) 
            .claim(Claims.birthdate.name(), "2001-07-13")
            .expiresIn(Duration.ofHours(1)) 
            .sign();
        return token;
    }

}
