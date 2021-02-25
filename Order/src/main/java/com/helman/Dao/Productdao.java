package com.helman.Dao;


import com.helman.Entity.Product;
import com.helman.General.Log4j;
import com.helman.General.Mysession;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Productdao {
    public Productdao(){}

    Session neshast = Mysession.getSession();

    public List<Product> findall(){
        try{
            List<Product> productList = neshast.createQuery("from Product").list();
            Log4j.logger.info("{}.{}|Try: All are Fetched",this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return productList;
        }catch (Exception e){
            Log4j.logger.error("{}.{}|Exception:{}",this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public Product findById(String procode){
        try {
            Product product = neshast.find(Product.class, procode);
            Log4j.logger.info("{}.{}|Try:record is Fetched!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return product;
        }catch (Exception exp){
            Log4j.logger.error("{}.{}|Exception:{}",this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), exp.getMessage());
            exp.printStackTrace();
            return null;
        }
    }

    public String insert (Product prod){
        try{
            Transaction tx = neshast.beginTransaction();
            neshast.save(prod);
            tx.commit();
            Log4j.logger.info("{}.{}|Try:Inserted!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return prod.getProductCode();
        }catch (Exception e){
            Log4j.logger.error("{}.{}|Exception",this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return "-1";
        }
    }
    public String update(Product product){
        try{
            Transaction tx = neshast.beginTransaction();
            neshast.update(product);
            tx.commit();
            Log4j.logger.info("{}.{}|try:Updated!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return product.getProductCode();
        }catch (Exception e){
            Log4j.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            e.printStackTrace();
            return "-1";
        }
    }
    public Integer delete(String procode){
        try{
            Product product = findById(procode);
            Transaction tx = neshast.beginTransaction();
            neshast.delete(product);
            tx.commit();
            Log4j.logger.info("{}.{}|Try:Deleted!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return 1;
        }catch (Exception e){
            Log4j.logger.error("{}.{}|Exception:{}",this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }
}
