package app;


import app.journalEntrys.entity.JournalEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class UngenericCRUD {

    @Inject
    EntityManager em;


    @Transactional
    public List<JournalEntity> getAll() {
        return em.createNativeQuery("SELECT * FROM journalentrysummary", JournalEntity.class)
                .getResultList();
    }


    @Transactional
    public <T> T create(T entity) {
        em.persist(entity);
        return entity;
    }

    @Transactional
    public JournalEntity findById(Long id) {
        return em.find(JournalEntity.class, id);
    }

    @Transactional
    public JournalEntity edit(JournalEntity newEntity, Object id) {
        JournalEntity managed = em.find(JournalEntity.class, id);

        managed.title = newEntity.getTitle();;
        managed.subtitle = newEntity.getSubtitle();;
        managed.content = newEntity.getContent();;
        managed.date = newEntity.getDate();;

        em.merge(managed);
        return managed;
    }


    @Transactional
    public <T> T delete(T entity) {
        em.remove(entity);
        return entity;
    }

    @Transactional
    public JournalEntity deleteById(int id) {
        JournalEntity entity = em.find(JournalEntity.class, id);
        em.remove(entity);
        return entity;
    }

}
