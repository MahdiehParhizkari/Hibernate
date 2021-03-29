package com.helman.Entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "offices")
@JsonFilter("Officefilter")
@XmlRootElement(name = "OfficeXML")
@XmlAccessorType(XmlAccessType.FIELD)
public class Office{
    public Office(){ }

    private String officeCode;
    private String city;
    private String phone;
    private String addressLine1;
    private String addressLine2;
    private String state;
    private String country;
    private String postalCode;
    private String territory;

    @XmlTransient
    private List<Employee> employees;

    @Id
    @Column(name = "officeCode")
    public String getOfficeCode() {
        return officeCode;
    }
    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    @Column(name = "city")
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "addressLine1")
    public String getAddressLine1() {
        return addressLine1;
    }
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    @Column(name = "addressLine2")
    public String getAddressLine2() {
        return addressLine2;
    }
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    @Column(name = "state")
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    @Column(name = "country")
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "postalCode")
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Column(name = "territory")
    public String getTerritory() {
        return territory;
    }
    public void setTerritory(String territory) {
        this.territory = territory;
    }

    @OneToMany(mappedBy = "office")
    public List<Employee> getEmployees() {
        return employees;
    }
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Office{" +
                "officeCode='" + officeCode + '\'' +
                ", city='" + city + '\'' +
                ", phone='" + phone + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", territory='" + territory + '\'' +
                //", employees=" + employees +
                '}';
    }
    @Transient
    public Set<String> getfilters(){
        Set<String> hash_set = new HashSet<String>();
        hash_set.add("officeCode");
        hash_set.add("city");
        hash_set.add("phone");
        hash_set.add("addressLine1");
        hash_set.add("addressLine2");
        hash_set.add("state");
        hash_set.add("country");
        hash_set.add("postalCode");
        hash_set.add("territory");
        return hash_set;
    }
}