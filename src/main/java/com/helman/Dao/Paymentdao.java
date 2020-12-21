package com.helman.Dao;

import com.helman.Entity.Customer;
import com.helman.Entity.Orderdetail;
import com.helman.Entity.Payment;
import com.helman.Entity.PaymentPK;
import com.helman.General.Myentitymanager;
import com.helman.General.Mysession;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

public class Paymentdao {
    public Paymentdao(){}

    EntityManager entityManager = Myentitymanager.getEntityManager();
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

    public List<Payment> findAll(){
        CriteriaQuery<Payment> criteriaQuery = criteriaBuilder.createQuery(Payment.class);
        Root<Payment> p = criteriaQuery.from(Payment.class);
        criteriaQuery.select(p);
        Query q = entityManager.createQuery(criteriaQuery);
        return q.getResultList();
    }
    public Payment findById(PaymentPK pPK){
        return entityManager.find(Payment.class,pPK);}
    public List<?> someColumn(PaymentPK pPk){
        CriteriaQuery<?> criteriaQuery = criteriaBuilder.createQuery();
        Root<?> p = criteriaQuery.from(Payment.class);
        criteriaQuery.multiselect(p.get("customerNumber"), p.get("checkNumber"));
        Predicate pre1=criteriaBuilder.equal(p.get("customerNumber"),pPk.getCustomerNumber());
        Predicate pre2=criteriaBuilder.equal(p.get("checkNumber"),pPk.getCheckNumber());
        criteriaQuery.where(criteriaBuilder.and(pre1,pre2));
        Query q = entityManager.createQuery(criteriaQuery);
        return q.getResultList();
    }
    public List<?> whereClause(Integer custNum){
        CriteriaQuery<?> criteriaQuery = criteriaBuilder.createQuery();
        Root<?> p = criteriaQuery.from(Payment.class);
        criteriaQuery.multiselect(p.get("customerNumber"), p.get("amount"));
        criteriaQuery.where(criteriaBuilder.equal(p.get("customerNumber"), custNum));
        Query q = entityManager.createQuery(criteriaQuery);
        return q.getResultList();
    }
    public List<?> aggregation(){
        CriteriaQuery<?> criteriaQuery = criteriaBuilder.createQuery();
        Root<?> p = criteriaQuery.from(Payment.class);
        criteriaQuery.multiselect(p.get("customerNumber"),criteriaBuilder.count(p.get("amount")));
        criteriaQuery.groupBy(p.get("customerNumber"));
        Query q = entityManager.createQuery(criteriaQuery);
        return q.getResultList();
    }
    public List<?> joinedQuery(Integer custNum){
        CriteriaQuery<?> criteriaQuery = criteriaBuilder.createQuery();
        Root<Payment> p = criteriaQuery.from(Payment.class);
        Join<Payment, Customer> c = p.join("customer");
        criteriaQuery.multiselect(p.get("customerNumber"), c.get("customerName"),
                c.get("contactLastName"), c.get("contactFirstName"));
        criteriaQuery.where(criteriaBuilder.equal(p.get("customerNumber"),custNum));
        Query q = entityManager.createQuery(criteriaQuery);
        return q.getResultList();
    }

    public void insert(Payment p){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(p);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
    public void update(Payment p){
        try{
            entityManager.getTransaction().begin();
            p.setPaymentDate(p.getPaymentDate());
            p.setAmount(p.getAmount());
            entityManager.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
    public void delete(Payment p){
        try{
            entityManager.getTransaction().begin();
            entityManager.remove(p);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }


}
