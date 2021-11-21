package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Project;
import ch.zli.m223.punchclock.domain.Time;
import ch.zli.m223.punchclock.domain.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class TimeService {
    @Inject
    private EntityManager entityManager;

    @Transactional 
    public Time createEntry(Time time) {
        entityManager.persist(time);
        return time;
    }

    @SuppressWarnings("unchecked")
    public List<Time> findAll() {
        var query = entityManager.createQuery("FROM Time");
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Time> findAllByUser(User u) {
        var query = entityManager.createQuery("FROM Time WHERE USER_ID = :uid").setParameter("uid",u.getId());
        return query.getResultList();
    }

    public Time getEntryById(Long Id){
        return entityManager.find(Time.class, Id);
    }

    @Transactional
    public void delete(Long id){
        Time time = getEntryById(id);
        entityManager.remove(time);
    }

    @Transactional
        public Time updateEntity(Time time){
        entityManager.merge(time);
        return time;
    }
}
