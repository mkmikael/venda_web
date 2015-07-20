package blacksoftware.webvenda.dao;

import java.lang.reflect.ParameterizedType;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;


public class DaoFactory {

    @Produces @Dependent
    public <T>DAO<T> createDAO(InjectionPoint injectionPoint, EntityManager entityManager) {
        ParameterizedType parameterizedType = (ParameterizedType) injectionPoint.getType();
        Class clazz = (Class) parameterizedType.getActualTypeArguments()[0];
        return new DAO<T>(clazz, entityManager);
    }
}
