package com.saha.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManager {
    private static EntityManagerFactory emFactory;

    private PersistenceManager() {}

    public static EntityManager getEntityManager() {
        if (emFactory == null) {
            // "controller-db" was the value of the name attribute of the persistence-unit element.
            emFactory = Persistence.createEntityManagerFactory("openapi-pu");
        }
        return emFactory.createEntityManager();
    }

    public static void close() {
        emFactory.close();
        emFactory = null;
    }
}
