package org.hibernate.bugs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JPAUnitTestCase {

    private EntityManagerFactory entityManagerFactory;

    private static Entity1 prepareData() {
        Entity1 entity1 = new Entity1();

        Set<Entity2> entities2 = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            Entity2 entity2 = new Entity2();
            entity2.setParent(entity1);
            entities2.add(entity2);

            // Add deeper children only to the first and the last items
            if (i == 0 || i == 9) {
                Set<Entity3> entities3 = new HashSet<>();
                for (int j = 0; j < 5; j++) {
                    Entity3 entity3 = new Entity3();
                    entity3.setParent(entity2);
                    entities3.add(entity3);
                }
                entity2.setChildren(entities3);
            }
        }
        entity1.setChildren(entities2);
        return entity1;
    }

    @BeforeEach
    public void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("templatePU");
    }

    @AfterEach
    public void destroy() {
        entityManagerFactory.close();
    }

    @Test
    public void hhh16043() {
        EntityManager em = entityManagerFactory.createEntityManager();
        Statistics statistics = entityManagerFactory.unwrap(SessionFactory.class).getStatistics();
        em.getTransaction().begin();

        Entity1 entity1 = prepareData();

        em.persist(entity1);
        em.flush();
        em.clear();

        statistics.clear();

        Entity1 fromDb = em.find(Entity1.class, entity1.getId());

        System.out.println(fromDb);
        for (Entity2 child2 : fromDb.getChildren()) {
            System.out.println("  " + child2);
            for (Entity3 child3 : child2.getChildren()) {
                System.out.println("    " + child3);
            }
        }

        try {
            assertEquals(3L, statistics.getPrepareStatementCount());
        } finally {
            em.getTransaction().commit();
            em.close();
        }
    }
}