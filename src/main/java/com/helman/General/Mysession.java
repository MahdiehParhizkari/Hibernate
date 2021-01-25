package com.helman.General;

import com.helman.Entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;

import java.sql.Connection;
import java.sql.SQLException;

public class Mysession {

    private static final SessionFactory mysession;

    static {
        try{
            mysession = new Configuration().configure("hibernate.cfg.xml").
                    addAnnotatedClass(Office.class).
                    addAnnotatedClass(Product.class).
                    addAnnotatedClass(Productline.class).
                    addAnnotatedClass(Employee.class).
                    addAnnotatedClass(Customer.class).
                    addAnnotatedClass(Payment.class).
                    addAnnotatedClass(PaymentPK.class).
                    addAnnotatedClass(Orderdetail.class).
                    addAnnotatedClass(OrderdetailPK.class).
                    addAnnotatedClass(Order.class).
                    //addAnnotatedClass(User.class).
                    buildSessionFactory();
        }catch(Throwable e){
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Session getSession(){
        return mysession.openSession();
    }
    public static void closeSession(){
        mysession.getCurrentSession().close();
    }
    public static Connection getConnection() {
        final Connection[] con = new Connection[1];
        getSession().doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                con[0] =connection;
            }
        });
        return con[0];
    }
}
