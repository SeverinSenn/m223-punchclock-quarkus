package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Project;
import ch.zli.m223.punchclock.domain.UserGroup;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class GroupService {
    @Inject
    private EntityManager entityManager;

    @Transactional 
    public UserGroup createEntry(UserGroup group) {
        entityManager.persist(group);
        return group;
    }

    @SuppressWarnings("unchecked")
    public List<UserGroup> findAll() {
        var query = entityManager.createQuery("FROM UserGroup");
        return query.getResultList();
    }

    public UserGroup getEntryById(Long Id){
        return entityManager.find(UserGroup.class, Id);
    }

    @Transactional
    public void delete(Long id){
        UserGroup group = getEntryById(id);
        entityManager.remove(group);
    }

    @Transactional
        public UserGroup updateEntity(UserGroup group){
        entityManager.merge(group);
        return group;
    }
}
