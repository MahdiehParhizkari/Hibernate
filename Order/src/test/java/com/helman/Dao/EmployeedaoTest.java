package com.helman.Dao;

import com.helman.Entity.Employee;
import com.helman.General.Mysession;
import org.hibernate.Session;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

public class EmployeedaoTest {
    Employeedao employeedao = new Employeedao();

    @Test
    public void findByJTall(){
        List<?> list = employeedao.findbyJT("London");
        for (int i = 0; i< list.size(); i++){
            Object[] customEmp = (Object[]) list.get(i);
            System.out.println(customEmp[0]+ " "+customEmp[1]);
        }
    }
    @Test
    public void findman(){
        List<?> list = employeedao.findman("lastname");
        for(int i = 0; i< list.size(); i++){
            Object[] emp = (Object[]) list.get(i);
            System.out.println(emp[0] +" "+emp[1]);
        }
    }
    @Test
    public void findall() {
        List<Employee> employeeList = employeedao.findall();
        for (Employee temp : employeeList){
            System.out.println(temp);
        }
    }
    @Test
    public void findById(){
        Employee employee = employeedao.findbyid(1143L);
        System.out.println(employee);
    }
    @Test
    public void find2(){
        List<Employee> employeeList = employeedao.namedQuery();
        for(Employee e:employeeList) {
            System.out.println(e);
        }
    }
    @Test
    public void namedSelect() {
        List<Employee> list = employeedao.besonderSelect(1143L);
        for (Employee temp : list){
            System.out.println(temp);
        }
    }

    @Test
    public void inserttest() {
        Employee employee = new Employee();
        //Employee employee = new Employee(11L,"ali","m","112","a.a@a.com","2",1043L,"fg");
        employee.setEmployeeNumber(1703L);
        employee.setLastName("Parhizkari");
        employee.setFirstName("Afshin");
        employee.setExtension("x2552");
        employee.setEmail("A.P@gmail.com");
        employee.setOfficeCode("3");
        employee.setReportsTo(1143L);
        employee.setJobTitle("sales Rep");

        employeedao.insert(employee);
    }
    @Test
    public void deletetest(){
        employeedao.delete(1703L);
    }
    @Test
    public void updatetest(){
        Employee e = employeedao.findbyid(1703L);
        e.setLastName("Helman");
        employeedao.update(e);
    }
}