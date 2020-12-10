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
        Transaction tx = neshast.beginTransaction();
        neshast.save(cust);
        tx.commit();
    }

    public void update(Customer cust){
        Transaction tx = neshast.beginTransaction();
        neshast.update(cust);
        tx.commit();
    }

    public void delete(Integer custnum){
        Customer c = findById(custnum);
        Transaction tx = neshast.beginTransaction();
        neshast.delete(c);
        tx.commit();
    }
}
