package ch.zli.m223.punchclock.service;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.punchclock.domain.User;

@ApplicationScoped
public class UserService {
    @Inject
    private EntityManager entityManager;

    @Transactional 
    public User createEntry(User user) {
        entityManager.persist(user);
        return user;
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        var query = entityManager.createQuery("FROM User");
        return query.getResultList();
    }

    public User getEntryById(Long Id){
        return entityManager.find(User.class, Id);
    }

    public User getEntryByEmail(String email){
        var query = entityManager.createQuery("FROM User WHERE email = :email")
                .setParameter("email",email);
        return (User) query.getSingleResult();
    }



    @Transactional
    public void delete(Long id){
        User user = getEntryById(id);
        entityManager.remove(user);
    }

    @Transactional
        public User updateEntity(User user){
        entityManager.merge(user);
        return user;
    }
}
