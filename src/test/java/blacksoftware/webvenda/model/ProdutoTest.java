/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blacksoftware.webvenda.model;

import blacksoftware.webvenda.dao.DaoFactory;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Avell G1511
 */
public class ProdutoTest {

    public ProdutoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        DaoFactory.environment = "test";
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    @Ignore
    public void testSave() {
    }
    
    @Test
    public void test() {
        EntityManager entityManager = DaoFactory.getEntityManager();
        Set<EntityType<?>> entities = entityManager.getEntityManagerFactory().getMetamodel().getEntities();
        for (EntityType<?> entity : entities) {
            System.out.println(entity.getName());
            for (Attribute object : entity.getAttributes()) {
                System.out.println("+" + object.getName() + " : " + object.getJavaType());
            }
        }
        entityManager.close();
    }
}
