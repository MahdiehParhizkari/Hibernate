package com.helman.Dao;

import com.helman.Entity.Order;
import com.helman.General.Mysession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class Orderdao {
    public Orderdao(){}
    Session neshast = Mysession.getSession();

    //ExecuteQuery
    public List<Order> findall(){
        return neshast.createQuery("from Order").list();
    }
    public Order findById(Integer orderNum){
        return neshast.find(Order.class, orderNum);
    }
    public List<Order> findallcity (){
        return neshast.createNamedQuery("findallcity").list();
    }

    //ExecuteUpdate
    public void insert(Order order){
        try{
            Transaction tx = neshast.beginTransaction();
            neshast.save(order);
            tx.commit();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
    public void update(Order order){
        try{
            Transaction tx = neshast.beginTransaction();
            neshast.update(order);
            tx.commit();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
    public void delete(Integer orderNum){
        try{
            Order o = findById(orderNum);
            Transaction tx = neshast.beginTransaction();
            neshast.delete(o);
            tx.commit();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
