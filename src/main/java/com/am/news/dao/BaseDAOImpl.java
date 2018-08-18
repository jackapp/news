package com.am.news.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public abstract class BaseDAOImpl<T> {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * This method is used to create an entity.
     *
     * @param object the record to be persisted
     */
    @Transactional
    public Object create(T object) {
        Object storeEntity = getEntityFromModel(object);
        entityManager.persist(storeEntity);
        return getEntityModel(storeEntity);
    }

    protected abstract Object getEntityModel(Object storeEntity);

    /**
     * This method is used to update an entity.
     *
     * @param object the record to be updated
     */
    @Transactional
    public void update(T object) {
        entityManager.merge(getEntityFromModel(object));
    }

    /**
     * This method is used to delete an entity.
     *
     * @param object the record to be deleted.
     */
    @Transactional
    public void delete(T object)
    {
        Object entity = getEntityFromModel(object);
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    /**
     * Writes all changes to the database before transaction is completed.
     *
     */
    @Transactional
    public void flush() {
        entityManager.flush();
    }

    /**
     * Gets the entity instance to be stored in database
     * @param object record for which entity object is required
     * @return respective entity instance.
     */
    protected abstract Object getEntityFromModel(T object);

}
