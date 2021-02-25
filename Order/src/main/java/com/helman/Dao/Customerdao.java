package com.helman.Dao;

import com.helman.Entity.Customer;
import com.helman.General.Logback;
import com.helman.General.Mysession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class Customerdao {
    public Customerdao(){}

    Session neshast = Mysession.getSession();

    public List<Customer> findall(){
        try {
            List<Customer> customers = neshast.createQuery("from Customer").list();
            Logback.logger.info("{}.{}|Try: All are Fetched", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return customers;
        }catch (Exception e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public Customer findById(Integer custnum){
        try {
            Customer customer = neshast.find(Customer.class, custnum);
            Logback.logger.info("{}.{}|Try: record is Fetched", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return customer;
        }catch (Exception e){
            e.printStackTrace();
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return null;
        }
    }

    public Integer insert (Customer cust){
        try{
            Transaction tx = neshast.beginTransaction();
            neshast.save(cust);
            tx.commit();
            Logback.logger.info("{}.{}|Try:Inserted", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
           return cust.getCustomerNumber();
        }catch (Exception e){
            System.out.println(e.toString());
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            return -1;
        }
    }
    public Integer update(Customer cust){
        try{
            Transaction tx = neshast.beginTransaction();
            neshast.merge(cust);
            tx.commit();
            Logback.logger.info("{}.{}|Try:Updated", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return cust.getCustomerNumber();
        }catch (Exception e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }
    public Integer delete(Integer custnum){
        try{
            Customer c = findById(custnum);
            Transaction tx = neshast.beginTransaction();
            neshast.delete(c);
            tx.commit();
            Logback.logger.info("{}.{}|Try:Deleted", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return 1;
        }catch (Exception e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }
}
