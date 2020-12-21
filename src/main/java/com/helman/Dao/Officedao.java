package com.helman.Dao;

import com.helman.Entity.Employee;
import com.helman.Entity.Office;
import com.helman.General.Myentitymanager;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class Officedao {
    public Officedao(){
    }
    EntityManager entityManager = Myentitymanager.getEntityManager();
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

    public List<Office> findAll(){
        CriteriaQuery<Office> criteriaQuery = criteriaBuilder.createQuery(Office.class);
        Root<Office> officeRoot = criteriaQuery.from(Office.class);
        criteriaQuery.select(officeRoot);
        criteriaQuery.orderBy(criteriaBuilder.asc(officeRoot.get("officeCode")));
        Query q = entityManager.createQuery(criteriaQuery);
        return q.getResultList();
    }
    public Office findById(String officeCode){
        return entityManager.find(Office.class, officeCode);
    }
    public List<?> someColumn(){
        CriteriaQuery<?> criteriaQuery = criteriaBuilder.createQuery();
        Root<?> root = criteriaQuery.from(Office.class);
        criteriaQuery.multiselect(root.get("officeCode"), root.get("city"));
        Query q = entityManager.createQuery(criteriaQuery);
        return q.getResultList();
    }
    public List<?> whereClause(String input){
        CriteriaQuery<?> criteriaQuery = criteriaBuilder.createQuery();
        Root<?> root = criteriaQuery.from(Office.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("officeCode"), input));
        criteriaQuery.multiselect(root.get("officeCode"), root.get("city"));
        Query q = entityManager.createQuery(criteriaQuery);
        return q.getResultList();
    }
    public List<?> aggregation(){
        CriteriaQuery<?> criteriaQuery = criteriaBuilder.createQuery();
        Root<?> root = criteriaQuery.from(Office.class);
        criteriaQuery.multiselect(root.get("officeCode"), criteriaBuilder.count(root)).groupBy(root.get("officeCode"));
        Query q = entityManager.createQuery((criteriaQuery));
        return q.getResultList();
    }
    public List<?> joinedQuery(){
        CriteriaQuery<?> criteriaQuery = criteriaBuilder.createQuery();
        Root<Office> officeRoot = criteriaQuery.from(Office.class);
        Join<Office, Employee> employeeJoin = officeRoot.join("employees");
        criteriaQuery.multiselect(employeeJoin.get("firstName"), employeeJoin.get("lastName"), employeeJoin.get("officeCode"), officeRoot.get("city"));
        Query q = entityManager.createQuery(criteriaQuery);
        return q.getResultList();
    }

    //ExecuteUpdate : JPA
    public void insert (Office office){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(office);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
    public void update(Office office){
        try{
            entityManager.getTransaction().begin();
            office.setCity(office.getCity());
            office.setPhone(office.getPhone());
            office.setAddressLine1(office.getAddressLine1());
            office.setAddressLine2(office.getAddressLine2());
            office.setState(office.getState());
            office.setCountry(office.getCountry());
            office.setPostalCode(office.getPostalCode());
            office.setTerritory(office.getTerritory());
            entityManager.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
    public void delete(Office office){
        try{
            entityManager.getTransaction().begin();
            entityManager.remove(office);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
