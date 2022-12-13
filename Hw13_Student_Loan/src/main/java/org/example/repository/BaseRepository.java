package org.example.repository;

import org.example.configuration.SessionFactorySingleton;
import org.example.entity.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class BaseRepository<E extends BaseEntity<Long>> {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    String entityName;
    private final Class<E> type;

    public BaseRepository(String entityName, Class<E> cls) {
        this.entityName = entityName;
        this.type = cls;
    }

    public void add(E e) {
        Session session = sessionFactory.openSession();
        session.saveOrUpdate(e);
        session.close();
    }

    public E findById(Long Id) {
        return sessionFactory.openSession().find(type, Id);
    }
}
