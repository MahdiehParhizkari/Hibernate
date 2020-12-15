package com.helman.Dao;


import com.helman.Entity.Product;
import com.helman.General.Mysession;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Productdao {
    public Productdao(){
    }

    Session neshast = Mysession.getSession();

    public List<Product> findall(){
        return neshast.createQuery("from Product").list();
    }
    public Product findById(String procode){
        return neshast.find(Product.class, procode);
    }

    public void insert (Product prod){
        try{
            Transaction tx = neshast.beginTransaction();
            neshast.save(prod);
            tx.commit();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
    public void update(Product product){
        try{
            Transaction tx = neshast.beginTransaction();
            neshast.update(product);
            tx.commit();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
    public void delete(String procode){
        try{
            Product product = findById(procode);
            Transaction tx = neshast.beginTransaction();
            neshast.delete(product);
            tx.commit();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
