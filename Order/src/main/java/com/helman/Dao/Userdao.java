package com.helman.Dao;

import com.helman.Entity.User;
import com.helman.General.Logback;
import com.helman.General.Myentitymanager;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class Userdao {
    public Userdao() {}

    EntityManager entityManager = Myentitymanager.getEntityManager();
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    List<User> users=new ArrayList<>();
    User user = new User();
    List<?> list = new ArrayList<>();

    public List<User> findAll(){
        users.clear();
        try {
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> u = criteriaQuery.from(User.class);
            criteriaQuery.select(u);
            users= entityManager.createQuery(criteriaQuery).getResultList();
            Logback.logger.info("{}.{}|Try:All are Fetched!",this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return users;
        }catch (Exception e) {
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public User findById(Integer id){
        try {
            user = entityManager.find(User.class, id);
            Logback.logger.info("{}.{}|Try: record is Fetched!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return user;
        }catch (Exception e) {
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public User login(String username){
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> u = criteriaQuery.from(User.class);
            criteriaQuery.select(u).where(criteriaBuilder.equal(u.get("username"), username));
            return (User) entityManager.createQuery(criteriaQuery).getResultList().get(0);
    }
    public List<?> someColumn(){
        try {
            CriteriaQuery<?> criteriaQuery = criteriaBuilder.createQuery();
            Root<?> root = criteriaQuery.from(User.class);
            criteriaQuery.multiselect(root.get("username"), root.get("password"));
            list = entityManager.createQuery(criteriaQuery).getResultList();
        }catch (Exception e) {
            System.out.println(e.toString());
        } return list;
    }

    public Integer insert(User user){
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            Logback.logger.info("{}.{}|try:Inserted", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return user.getId();
        }catch (Exception e) {
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }
    public Integer update(User user){
        try {
            entityManager.getTransaction().begin();
            user.setId(user.getId());
            user.setUsername(user.getUsername());
            user.setPassword(user.getPassword());
            user.setEmployeefk(user.getEmployeefk());
            entityManager.getTransaction().commit();
            Logback.logger.info("{}.{}|Try: Updated",this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName());
            return user.getId();
        }catch (Exception e){
            Logback.logger.error("{}.{}|Exception:{}",this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName(),e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }
    public Integer delete(User user){
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(user);
            entityManager.getTransaction().commit();
            Logback.logger.info("{}.{}|Try: Deleted",this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName());
            return 1;
        }catch (Exception e) {
            Logback.logger.error("{}.{}|Exception:{}",this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName(),e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }
}