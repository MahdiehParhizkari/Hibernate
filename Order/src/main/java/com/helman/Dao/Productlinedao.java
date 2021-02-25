package com.helman.Dao;

import com.helman.Entity.Product;
import com.helman.Entity.Productline;
import com.helman.General.Log4j;
import com.helman.General.Myentitymanager;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class Productlinedao {
    public Productlinedao(){
    }
    //ExecuteQuery : Criteria
    EntityManager entityManager = Myentitymanager.getEntityManager();
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

    public List<Productline> findall() {
        try {
            CriteriaQuery<Productline> criteriaQuery = criteriaBuilder.createQuery(Productline.class);
            Root<Productline> pl = criteriaQuery.from(Productline.class);//HQL: from Productline pl
            CriteriaQuery<Productline> myselect = criteriaQuery.select(pl);//HQL: select pl
            List<Productline> productlineList = entityManager.createQuery(myselect).getResultList();  // create sql
            Log4j.logger.info("{}.{}|Try: All are Fetched!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return productlineList;
        }catch (Exception e){
            Log4j.logger.error("{}.{}|Exception:{}",this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public Productline findById(String input){
        try{
            Productline proline = entityManager.find(Productline.class, input);
            Log4j.logger.info("{}.{}|Try:record is Fetched!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return proline;
        }catch (Exception e){
            Log4j.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public List<?> someColumn(){
        CriteriaQuery<?> criteriaQuery = criteriaBuilder.createQuery();
        Root<?> pl = criteriaQuery.from(Productline.class);
        CriteriaQuery<?> myselect =
                criteriaQuery.multiselect(pl.get("productLine"),pl.get("textDescription"));
        Query q = entityManager.createQuery(myselect);
        List<?> list = q.getResultList();
        return list;
    }
    public List<?> whereClause(String input){
        CriteriaQuery<?> criteriaQuery = criteriaBuilder.createQuery();
        Root<?> pl = criteriaQuery.from(Productline.class);
        criteriaQuery.where(criteriaBuilder.equal(pl.get("productLine"),input));
        criteriaQuery.multiselect(pl.get("productLine"),pl.get("textDescription"));
        Query q = entityManager.createQuery(criteriaQuery);
        List<?> list = q.getResultList();
        return list;
    }
    public List<?> aggregation(){
        CriteriaQuery<?> criteriaQuery = criteriaBuilder.createQuery();
        Root<?> pl = criteriaQuery.from(Productline.class);
        criteriaQuery.multiselect(pl.get("productLine"),criteriaBuilder.count(pl)).groupBy(pl.get("productLine"));
        Query q = entityManager.createQuery(criteriaQuery);
        return q.getResultList();
    }
    public List<?> joinedQuery(){
        CriteriaQuery<?> criteriaQuery = criteriaBuilder.createQuery();
        Root<Productline> pl = criteriaQuery.from(Productline.class);
        Join<Productline, Product> productJoin = pl.join("products");
        criteriaQuery.multiselect(productJoin.get("productCode"), productJoin.get("productName"),
                productJoin.get("productLine"), pl.get("productLine"));
        //criteriaQuery.where(criteriaBuilder.equal(productJoin.get("productLine"), pl.get("productLine")));
        Query q = entityManager.createQuery(criteriaQuery);
        return q.getResultList();
    }

    //ExecuteUpdate : JPA
    public String insert(Productline pl){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(pl);
            entityManager.getTransaction().commit();
            Log4j.logger.info("{}.{}|Try:Inserted", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return pl.getProductLine();
        }catch (Exception e){
            Log4j.logger.error("{}.{}|Exception:{}",this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return "-1";
        }
    }
    public String update (Productline pl){
        try{
            entityManager.getTransaction().begin();
            pl.setTextDescription(pl.getTextDescription());
            pl.setHtmlDescription(pl.getHtmlDescription());
            entityManager.getTransaction().commit();
            Log4j.logger.info("{}.{}|Try: Updated!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return pl.getProductLine();
        }catch (Exception e){
            Log4j.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return "-1";
        }
    }
    public Integer delete(Productline pl){
        try{
            entityManager.getTransaction().begin();
            entityManager.remove(pl);
            entityManager.getTransaction().commit();
            Log4j.logger.info("{}.{}|Try:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return 1;
        }catch (Exception e){
            Log4j.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(),e.getMessage());
            return -1;
        }
    }
}
