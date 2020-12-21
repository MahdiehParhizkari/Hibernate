package com.helman.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employees")
@NamedQueries({
        @NamedQuery(name = "managerEmp", query = "select e.lastName, e.firstName from Employee e " +
                "where e.jobTitle=:jtit"),

        @NamedQuery(name = "reptoman", query = "select emp.firstName, emp.lastName" +
                " from Employee emp inner join Employee boss on emp.reportsTo = boss.employeeNumber " +
                "where boss.lastName = 'Murphy'"),

        @NamedQuery(name = "city", query = "select e.lastName, e.firstName " +
                "from Employee e inner join fetch Office o on e.officeCode = o.officeCode where o.city=:city"),

        @NamedQuery(name = "first", query = "select e.employeeNumber,e.firstName, e.lastName " +
        " from Employee e " + "where e.employeeNumber=1143"),
        @NamedQuery(name = "Selectedquery" , query = "select e from Employee e where e.reportsTo=:empnum"),
        @NamedQuery(name = "all", query = "select e from Employee e where e.employeeNumber=1143")
})
public class Employee {
    public Employee(){
    }

    public Employee(Long employeeNumber, String lastName, String firstName, String extension, String email, String officeCode, Long reportsTo, String jobTitle) {
        this.employeeNumber = employeeNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.extension = extension;
        this.email = email;
        this.officeCode = officeCode;
        this.reportsTo = reportsTo;
        this.jobTitle = jobTitle;
    }

    private Long employeeNumber;
    private String lastName;
    private String firstName;
    private String extension;
    private String email;
    private String officeCode;
    private Long reportsTo;
    private String jobTitle;

    private Office office;
    private List<Customer> customers;
    private Employee manager;
    private List<Employee> employees;

    @Id
    @Column(name = "employeeNumber")
    public Long getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(Long employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    @Column(name = "lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "extension")
    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "officeCode")
    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    @Column(name = "reportsTo")
    public Long getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(Long reportsTo) {
        this.reportsTo = reportsTo;
    }

    @Column(name = "jobTitle")
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }


    @ManyToOne
    @JoinColumn(name = "officeCode",insertable = false,updatable = false)
    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }



    @OneToMany(mappedBy = "employee")
    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }



    @ManyToOne
    @JoinColumn(name = "reportsTo", referencedColumnName = "employeeNumber", updatable = false, insertable = false)
    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    @OneToMany(mappedBy = "manager")
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeNumber=" + employeeNumber +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", extension='" + extension + '\'' +
                ", email='" + email + '\'' +
                ", officeCode='" + officeCode + '\'' +
                ", reportsTo=" + reportsTo +
                ", jobTitle='" + jobTitle + '\'' +
                "\n, office=" + office +
                //"\n, customers=" + customers +
                 "\n, manager=" + manager +
                // "\n, employees=" + employees +
                '}';
    }
}
