package com.helman.Dao;

import com.helman.Entity.Order;
import com.helman.General.Log4j;
import com.helman.General.Mysession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class Orderdao {
    public Orderdao(){}
    Session neshast = Mysession.getSession();

    //ExecuteQuery
    public List<Order> findall() {
        try {
            List<Order> orderList = neshast.createQuery("from Order").list();
            Log4j.logger.info("{}.{}|Try:All are Fetched", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return orderList;
        }catch (Exception e){
            Log4j.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public Order findById(Integer orderNum){
        try{
            Order order = neshast.find(Order.class, orderNum);
            Log4j.logger.info("{}.{}|Try:record is fetched", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return order;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public List<Order> findallcity (){
        return neshast.createNamedQuery("findallcity").list();
    }

    //ExecuteUpdate
    public Integer insert(Order order){
        try{
            Transaction tx = neshast.beginTransaction();
            neshast.save(order);
            tx.commit();
            Log4j.logger.info("{}.{}|Try:Inserted", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return order.getOrderNumber();
        }catch (Exception e){
            Log4j.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }
    public Integer update(Order order){
        try{
            Transaction tx = neshast.beginTransaction();
            neshast.update(order);
            tx.commit();
            Log4j.logger.info("{}.{}|Try:Updated", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return order.getOrderNumber();
        }catch (Exception e){
            Log4j.logger.error("{}.{}|Exception:{}",this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }
    public Integer delete(Integer orderNum){
        try{
            Order o = findById(orderNum);
            Transaction tx = neshast.beginTransaction();
            neshast.delete(o);
            tx.commit();
            Log4j.logger.info("{}.{}|Try:Deleted", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return 1;
        }catch (Exception e){
            Log4j.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }
}
