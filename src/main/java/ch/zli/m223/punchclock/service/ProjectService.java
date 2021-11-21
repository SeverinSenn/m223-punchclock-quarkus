package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Project;
import ch.zli.m223.punchclock.domain.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ProjectService {
    @Inject
    private EntityManager entityManager;

    @Transactional 
    public Project createEntry(Project project) {
        entityManager.persist(project);
        return project;
    }

    @SuppressWarnings("unchecked")
    public List<Project> findAll() {
        var query = entityManager.createQuery("FROM Project");
        return query.getResultList();
    }

    public Project getEntryById(Long Id){
        return entityManager.find(Project.class, Id);
    }

    @Transactional
    public void delete(Long id){
        Project project = getEntryById(id);
        entityManager.remove(project);
    }

    @Transactional
        public Project updateEntity(Project project){
        entityManager.merge(project);
        return project;
    }
}
