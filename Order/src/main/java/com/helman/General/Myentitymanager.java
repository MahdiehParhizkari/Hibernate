package com.helman.General;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Myentitymanager {
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("orderdbconfig");

    public static EntityManager getEntityManager(){
        try{
            return entityManagerFactory.createEntityManager();
        }catch (Exception e){
            System.out.println(e);
        }return null;
    }
}
