package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

    public static EntityManagerFactory factory = null;

    static {
        init();

    }

    private static void init(){
        try{
            if (factory== null){
                factory = Persistence.createEntityManagerFactory("persistenceUnitName");
            }

        }catch (Exception e){
            System.out.println("errou algo");
            e.printStackTrace();
        }
    }

    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    } // Provê a persistência

    public static Object getPrimaryKey(Object entity){ // Retorna a primary key
        return factory.getPersistenceUnitUtil().getIdentifier(entity);
    }
}