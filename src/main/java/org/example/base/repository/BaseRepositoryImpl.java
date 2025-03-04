package org.example.base.repository;


import lombok.AllArgsConstructor;
import org.example.base.entity.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.Optional;

@AllArgsConstructor
public abstract class BaseRepositoryImpl<T extends BaseEntity<ID>, ID extends Serializable>
        implements BaseRepository<T, ID> {

    protected SessionFactory sessionFactory;
    @Override
    public T saveOrUpdate(T entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            if (entity.getId() == null)
                session.persist(entity);
            else
                entity = session.merge(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public Optional<T> findById(ID id) {
        Session session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.get(getEntityClass(), id));
    }

    @Override
    public void delete(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(entity);
    }


    public abstract Class<T> getEntityClass();

}
