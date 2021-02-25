package com.helman.Dao;

import com.helman.Entity.Order;
import com.helman.Entity.Orderdetail;
import com.helman.Entity.OrderdetailPK;
import com.helman.Entity.Product;
import com.helman.General.Log4j;
import com.helman.General.Mysession;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class Orderdetaildao {
    public Orderdetaildao(){}

    EntityManager entityManager = Mysession.getSession();
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

    public List<Orderdetail> findAll() {
        try {
            CriteriaQuery<Orderdetail> criteriaQuery = criteriaBuilder.createQuery(Orderdetail.class);
            Root<Orderdetail> od = criteriaQuery.from(Orderdetail.class);
            criteriaQuery.select(od);
            Query q = entityManager.createQuery(criteriaQuery);
            List<Orderdetail> list = q.getResultList();
            Log4j.logger.info("{}.{}|Try: All are Fetched", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return list;
        }catch(Exception e){
            Log4j.logger.error("{}.{}|Exception:{}",this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public Orderdetail findById(OrderdetailPK odPk) {
        try {
            Orderdetail orderdetail = entityManager.find(Orderdetail.class, odPk);
            Log4j.logger.info("{}.{}|Try: Record is Fetched!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return orderdetail;
        }catch (Exception e){
            Log4j.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public List<?> someColumn(){
        CriteriaQuery<?> criteriaQuery = criteriaBuilder.createQuery();
        Root<?> od = criteriaQuery.from(Orderdetail.class);
        criteriaQuery.multiselect(od.get("quantityOrdered"), od.get("priceEach"));
        Query q = entityManager.createQuery(criteriaQuery);
        return q.getResultList();
    }
    public List<?> whereClause(Integer ordNum){
        CriteriaQuery<?> criteriaQuery = criteriaBuilder.createQuery();
        Root<?> od = criteriaQuery.from(Orderdetail.class);
        criteriaQuery.multiselect(od.get("orderNumber"), od.get("orderLineNumber"));
        criteriaQuery.where(criteriaBuilder.equal(od.get("orderNumber"), ordNum));
        Query q = entityManager.createQuery(criteriaQuery);
        return q.getResultList();
    }
    public List<?> aggregation(String procode){
        CriteriaQuery<?> criteriaQuery = criteriaBuilder.createQuery();
        Root<?> od = criteriaQuery.from(Orderdetail.class);
        criteriaQuery.multiselect(od.get("orderNumber"), od.get("productCode"), od.get("priceEach"));
        criteriaQuery.where(criteriaBuilder.equal(od.get("productCode"), procode));
        Query q = entityManager.createQuery(criteriaQuery);
        return q.getResultList();
    }
    public List<?> joinedQuery(){
        CriteriaQuery<?> criteriaQuery = criteriaBuilder.createQuery();
        Root<Orderdetail> od = criteriaQuery.from(Orderdetail.class);
        Join<Orderdetail, Order> o = od.join("order");
        criteriaQuery.multiselect(od.get("orderNumber"), od.get("priceEach"), o.get("status"));
        Query q = entityManager.createQuery(criteriaQuery);
        return q.getResultList();
    }
    public List<?> joinedQuery1(String proCode){
        CriteriaQuery<?> criteriaQuery = criteriaBuilder.createQuery();
        Root<Orderdetail> od = criteriaQuery.from(Orderdetail.class);
        Join<Orderdetail, Product> p = od.join("product");
        criteriaQuery.multiselect(p.get("productName") , od.get("priceEach"), od.get("priceEach"));
        criteriaQuery.where(criteriaBuilder.equal(od.get("productCode"), proCode));
        Query q = entityManager.createQuery(criteriaQuery);
        return q.getResultList();
    }

    public OrderdetailPK insert(Orderdetail od){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(od);
            entityManager.getTransaction().commit();
            Log4j.logger.info("{}.{}|Try: Inserted", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return new OrderdetailPK(od.getOrderNumber(), od.getProductCode());
        }catch (Exception e){
            Log4j.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public OrderdetailPK update(Orderdetail od){
        try{
            entityManager.getTransaction().begin();
            od.setQuantityOrdered(od.getQuantityOrdered());
            od.setPriceEach(od.getPriceEach());
            od.setOrderLineNumber(od.getOrderLineNumber());
            entityManager.getTransaction().commit();
            Log4j.logger.info("{}.{}|Try:Updated", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return new OrderdetailPK(od.getOrderNumber(), od.getProductCode());
        }catch (Exception e){
            Log4j.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public Integer delete(Orderdetail od){
        try{
            entityManager.getTransaction().begin();
            entityManager.remove(od);
            entityManager.getTransaction().commit();
            Log4j.logger.info("{}.{}|Try:Deleted",this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return 1;
        }catch (Exception e){
            Log4j.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            e.printStackTrace();
            return -1;
        }
    }
}
