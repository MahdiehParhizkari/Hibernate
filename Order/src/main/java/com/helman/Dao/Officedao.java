package com.helman.Dao;

import com.helman.Entity.Employee;
import com.helman.Entity.Office;
import com.helman.General.Logback;
import com.helman.General.Myentitymanager;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class Officedao {
    public Officedao(){}
    EntityManager entityManager = Myentitymanager.getEntityManager();
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

    public List<Office> findAll() {
        try {
            CriteriaQuery<Office> criteriaQuery = criteriaBuilder.createQuery(Office.class);
            Root<Office> officeRoot = criteriaQuery.from(Office.class);
            criteriaQuery.select(officeRoot);
            criteriaQuery.orderBy(criteriaBuilder.asc(officeRoot.get("officeCode")));
            Query q = entityManager.createQuery(criteriaQuery);
            List<Office> list = q.getResultList();
            Logback.logger.info("{}.{}|try: All are fetched", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return list;
        }catch (Exception e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public Office findById(String officeCode) {
        try {
            Office office = entityManager.find(Office.class, officeCode);
            Logback.logger.info("{}.{}|Try:Record is fetched!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1]);
            return office;
        }catch(Exception e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public List<?> someColumn() {
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
    public String insert (Office office){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(office);
            entityManager.getTransaction().commit();
            Logback.logger.info("{}.{}|info:Inserted", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return office.getOfficeCode();
        }catch (Exception e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return "-1";
        }
    }
    public String update(Office office){
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
            Logback.logger.info("{}.{}|Try:Updated", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return office.getOfficeCode();
        }catch (Exception e){
            Logback.logger.error("{}.{}|Exception{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return "-1";
        }
    }
    public Integer delete(Office office){
        try{
            entityManager.getTransaction().begin();
            entityManager.remove(office);
            entityManager.getTransaction().commit();
            Logback.logger.info("{}.{}|Try:Deleted", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return 1;
        }catch (Exception e){
            Logback.logger.error("{}.{}|Excetion:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }
}
