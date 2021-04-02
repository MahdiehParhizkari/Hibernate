package com.helman.Dao;

import com.helman.Entity.Customer;
import com.helman.Entity.Payment;
import com.helman.Entity.PaymentPK;
import com.helman.General.Logback;
import com.helman.General.Myentitymanager;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

public class Paymentdao {
    public Paymentdao(){}

    EntityManager entityManager = Myentitymanager.getEntityManager();
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

    public List<Payment> findAll() {
        try {
            CriteriaQuery<Payment> criteriaQuery = criteriaBuilder.createQuery(Payment.class);
            Root<Payment> p = criteriaQuery.from(Payment.class);
            criteriaQuery.select(p);
            Query q = entityManager.createQuery(criteriaQuery);
            List<Payment> list = q.getResultList();
            Logback.logger.info("{}.{}|Try:All are Fetched!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return list;
        }catch(Exception e){
            Logback.logger.info("{}.{}|Exception:{}",this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public Payment findById(PaymentPK pPK) {
        try {
            Payment payment = entityManager.find(Payment.class, pPK);
            Logback.logger.info("{}.{}|Try:Record is fetched!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return payment;
        }catch (Exception e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
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

    public PaymentPK insert(Payment p){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(p);
            entityManager.getTransaction().commit();
            Logback.logger.info("{}.{}|Try:Inserted", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return new PaymentPK(p.getCustomerNumber(), p.getCheckNumber());
        }catch (Exception e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public PaymentPK update(Payment p){
        try{
            Payment payment = findById(new PaymentPK(p.getCustomerNumber(), p.getCheckNumber()));
            entityManager.getTransaction().begin();
            payment.setPaymentDate(p.getPaymentDate());
            payment.setAmount(p.getAmount());
            entityManager.getTransaction().commit();
            Logback.logger.info("{}.{}|Try:Inserted",this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return new PaymentPK(p.getCustomerNumber(), p.getCheckNumber());
        }catch (Exception e){
            Logback.logger.error("{}.{}|Exception:{}",this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public Integer delete(Payment p){
        try{
            entityManager.getTransaction().begin();
            entityManager.remove(p);
            entityManager.getTransaction().commit();
            Logback.logger.info("{}.{}|Try:Deleted", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return 1;
        }catch (Exception e){
            Logback.logger.error("{}.{}|Exception:{}",this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }
}