package blacksoftware.webvenda.dao;

import blacksoftware.webvenda.util.ReflectionUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

public class DAO implements Serializable {

    private static final long serialVersionUID = 8765143660533424570L;
    private final EntityManager manager;

    public DAO() {
        manager = DaoFactory.getEntityManager();
    }

    public void close() {
        try {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().commit();
            }
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            manager.close();
        }
    }

    public void rollback() {
        manager.getTransaction().rollback();
    }
    
    public void begin() {
        manager.getTransaction().begin();
    }

    public void commit() {
        manager.getTransaction().commit();
    }

    public <T> void save(Class<T> clazz, T entity) {
        save(clazz, entity, false);
    }

    public <T> void save(Class<T> clazz, T entity, boolean flush) {
        if (ReflectionUtil.getIdFromAnnotation(entity) == null) {
            manager.persist(entity);
        } else {
            manager.merge(entity);
        }
        if (flush) {
            manager.getTransaction().begin();
            manager.getTransaction().commit();
        }
    }

    public <T> void delete(Class<T> clazz, T entity) {
        delete(clazz, entity, false);
    }

    public <T> void delete(Class<T> clazz, T entity, boolean flush) {
        manager.remove(manager.merge(entity));
        if (flush) {
            manager.getTransaction().begin();
            manager.getTransaction().commit();
        }
    }

    public <T> T get(Class<T> clazz, Object id) {
        return manager.find(clazz, id);
    }

    public <T> List<T> list(Class<T> clazz) {
        TypedQuery<T> query = manager.createNamedQuery(clazz.getSimpleName() + ".findAll", clazz);
        return query.getResultList();
    }

    public <T> List<T> findListNamedQuery(Class<T> clazz, String name, Map<String, Object> params) {
        return namedQuery(clazz, name, params).getResultList();
    }

    public <T> List<T> findListNamedQuery(Class<T> clazz, String name) {
        return namedQuery(clazz, name, null).getResultList();
    }

    public <T> T findOneNamedQuery(Class<T> clazz, String name, Map<String, Object> params) {
        return namedQuery(clazz, name, params).getSingleResult();
    }

    public <T> T findOneNamedQuery(Class<T> clazz, String name) {
        return namedQuery(clazz, name, null).getSingleResult();
    }

    private <T> TypedQuery<T> namedQuery(Class<T> clazz, String name, Map<String, Object> params) {
        TypedQuery<T> query = manager.createNamedQuery(name, clazz);
        if (params == null) {
            params = new HashMap<>();
        }
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (entry.getValue() instanceof Date) {
                Map.Entry<Date, TemporalType> dateType = (Map.Entry<Date, TemporalType>) entry.getValue();
                query.setParameter(entry.getKey(), (Date) dateType.getKey(), dateType.getValue());
            } else {
                query.setParameter(entry.getKey(), entry.getValue());
            }
        }
        return query;
    }
}
