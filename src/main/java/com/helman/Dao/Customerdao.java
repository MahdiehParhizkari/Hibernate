package com.helman.Dao;

import com.helman.Entity.Customer;
import com.helman.General.Mysession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class Customerdao {
    public Customerdao(){}

    Session neshast = Mysession.getSession();

    public List<Customer> findall(){
        return neshast.createQuery("from Customer").list();
    }
    public Customer findById(Integer custnum){
        return neshast.find(Customer.class, custnum);
    }

    public void insert (Customer cust){
        try{
            Transaction tx = neshast.beginTransaction();
            neshast.save(cust);
            tx.commit();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
    public void update(Customer cust){
        try{
            Transaction tx = neshast.beginTransaction();
            neshast.merge(cust);
            tx.commit();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
    public void delete(Integer custnum){
        try{
            Customer c = findById(custnum);
            Transaction tx = neshast.beginTransaction();
            neshast.delete(c);
            tx.commit();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
