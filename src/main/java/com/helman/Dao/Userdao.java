package com.helman.Dao;

import com.helman.Entity.User;
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
        }catch (Exception e) {
            System.out.println(e.toString());
        }
        return users;
    }
    public User findById(Integer id){
        try {
            user = entityManager.find(User.class, id);
        }catch (Exception e) {
            System.out.println(e.toString());
        }
        return user;
    }
    public User login(String username){
        try {
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> u = criteriaQuery.from(User.class);
            criteriaQuery.select(u).where(criteriaBuilder.equal(u.get("username"), username));
            user =  entityManager.createQuery(criteriaQuery).getResultList().get(1);
        }catch (Exception e) {
            System.out.println(e.toString());
        }return user;
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

    public void insert(User user){
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    public void update(User user){
        try {
            entityManager.getTransaction().begin();
            user.setId(user.getId());
            user.setUsername(user.getUsername());
            user.setPassword(user.getPassword());
            user.setEmployeefk(user.getEmployeefk());
            entityManager.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
    public void delete(User user){
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(user);
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}