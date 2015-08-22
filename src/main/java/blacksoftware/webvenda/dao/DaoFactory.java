package blacksoftware.webvenda.dao;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DaoFactory {
    private static final Logger LOG = Logger.getLogger(DaoFactory.class.getName());

    private static EntityManagerFactory development;
    public static String environment = "development";

    public static EntityManagerFactory getFactory() {
        if (development == null) {
            try {
                LOG.info("INIT ENTITY MANAGER FACTORY");
                development = Persistence.createEntityManagerFactory(environment);
            } catch (Exception e) {
                LOG.log(Level.SEVERE, "ERRO ENTITY MANAGER FACTORY", e);
                if (development != null) development.close();
                development = null;
            }
        }
        return development;
    }

    public static EntityManager getEntityManager() {
        return getFactory().createEntityManager();
    }

//    @Produces
//    @Dependent
//    public <T> DAO<T> createDAO(InjectionPoint injectionPoint, EntityManager entityManager) {
//        ParameterizedType parameterizedType = (ParameterizedType) injectionPoint.getType();
//        Class clazz = (Class) parameterizedType.getActualTypeArguments()[0];
//        return new DAO<T>(clazz, entityManager);
//    }
}
