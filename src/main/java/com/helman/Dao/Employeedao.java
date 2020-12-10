package com.helman.Dao;

import com.helman.Entity.Employee;
import com.helman.General.Mysession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;


public class Employeedao {
    public Employeedao(){
    }

    Session neshast = Mysession.getSession();

    public List<?> findbyJT(String jobsub){
       // List<?> list = neshast.createNamedQuery("managerEmp").setParameter("jtit", jobsub).list();
        //return list;
        return neshast.createNamedQuery("city").setParameter("city" , jobsub).list();
    }

    public List<?> findman(String lastname){
        List<?> list = neshast.createNamedQuery("reptoman").list();
        return list;
    }

    public List<Employee> findall(){
        return neshast.createQuery("from Employee").list();
    }

    public Employee findbyid(Long empnum){
        return neshast.find(Employee.class, empnum);
        //return neshast.get(Employee.class, empnum);
        //return neshast.load(Employee.class, empnum);
    }

    public List<Employee> namedQuery(){
        return neshast.createNamedQuery("all").list();
    }

    public List<?> besonderSelect(Long inputnumber){
        return neshast.createNamedQuery("Selectedquery").
                setParameter("empnum",inputnumber).setMaxResults(10).list();
    }


    public void insert(Employee employee){
        neshast.beginTransaction();
        neshast.save(employee);
        neshast.getTransaction().commit();
    }

    public void delete(Long id){
        Employee e=findbyid(id);
        Transaction tx = neshast.beginTransaction();
        neshast.delete(e);
        tx.commit();
    }

    public void update(Employee employee){
        Transaction tx = neshast.beginTransaction();
        neshast.update(employee);
        tx.commit();
    }
}
