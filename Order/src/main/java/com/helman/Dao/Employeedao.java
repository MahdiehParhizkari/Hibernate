package com.helman.Dao;

import com.helman.Entity.Employee;
import com.helman.General.Logback;
import com.helman.General.Mysession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class Employeedao {
    public Employeedao(){
    }

    Session neshast = Mysession.getSession();

    public List<?> findbyJT(String jobsub){
        try {
            List<?> list = neshast.createNamedQuery("managerEmp").setParameter("jtit", jobsub).list();
            Logback.logger.info("{}.{}|Try:record is fetched", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return list;
        }catch (Exception e){
            Logback.logger.error("{}.{}|Exction:{}",this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public List<?> findman(String lastname){
        try {
            List<?> list = neshast.createNamedQuery("reptoman").list();
            Logback.logger.info("{}.{}.|Try: record is fetched", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return list;
        }catch (Exception e){
            Logback.logger.error("{}.{}|Exception:{}",this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public List<Employee> findall(){
        try {
            List<Employee> employeeList = neshast.createQuery("from Employee").list();
            Logback.logger.info("{}.{}|Try: All are fetched", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return employeeList;
        }catch (Exception e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public Employee findbyid(Long empid){
        try {
            Employee employee = neshast.find(Employee.class, empid);
            Logback.logger.info("{}.{}|Try: record is Fetched!", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return employee;
            //return neshast.get(Employee.class, empid);
            //return neshast.load(Employee.class, empid);
        }catch (Exception exp){
            Logback.logger.error("{}.{}|Exception:{}",this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), exp.getMessage());
            exp.printStackTrace();
            return null;
        }
    }
    public List<Employee> namedQuery(){
        return neshast.createNamedQuery("all").list();
    }
    public List<Employee> besonderSelect(Long inputnumber){
        return neshast.createNamedQuery("Selectedquery").
                setParameter("empnum",inputnumber).list();
    }

    public long insert(Employee employee){
        try{
            neshast.beginTransaction();
            neshast.save(employee);
            neshast.getTransaction().commit();
            Logback.logger.info("{}.{}|Try: Inserted", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return employee.getEmployeeNumber();
        }catch (Exception e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }
    public Integer delete(Long id){
        try{
            Employee e=findbyid(id);
            Transaction tx = neshast.beginTransaction();
            neshast.delete(e);
            tx.commit();
            Logback.logger.info("{},{}|Try:Deleted", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return 1;
        }catch (Exception e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }
    public Long update(Employee employee){
        try {
            Transaction tx = neshast.beginTransaction();
            //neshast.evict(employee);
            neshast.merge(employee);
            tx.commit();
            Logback.logger.info("{}.{}|Try:Updated", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return employee.getEmployeeNumber();
        }catch (Exception e){
            Logback.logger.error("{}.{}|Exception:{}",this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
