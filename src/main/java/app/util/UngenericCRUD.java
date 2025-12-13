package app.util;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class UngenericCRUD {

    @Inject
    EntityManager em;

    @Transactional
    public <T> List<T> getAll(Class<T> entityClass) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> rootEntry = cq.from(entityClass);
        CriteriaQuery<T> all = cq.select(rootEntry).where();
        TypedQuery<T> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    @Transactional
    public <T> T create(T entity) {
        em.persist(entity);
        return entity;
    }

    @Transactional
    public <T> T findEntityById(Class<T> entityClass, Long id) {
        return em.find(entityClass, id);
    }

    @Transactional
    public List getImagesForJournalId(Long id) {
        return em.createQuery("SELECT c.fileBase64 FROM ImageEntity c WHERE c.journalId = :id")
                .setParameter("id", id)
                .getResultList();
    }

    @Transactional
    public <T> T edit(T newEntity) {
        em.merge(newEntity);
        return newEntity;
    }


    @Transactional
    public <T> void delete(T entity) {
        em.remove(entity);
    }

    @Transactional
    public <T> T deleteById(Class<T> entityClass, Long id) {
        T entity = em.find(entityClass, id);
        em.remove(entity);
        return entity;
    }

}
