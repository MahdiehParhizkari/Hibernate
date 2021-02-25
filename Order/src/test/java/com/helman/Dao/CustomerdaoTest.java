package com.helman.Dao;

import com.helman.Entity.Customer;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class CustomerdaoTest {

    Customerdao customerdao = new Customerdao();

    @Test
    public void findall() {
        List<Customer> customerList = customerdao.findall();
        for (Customer temp: customerList){
            System.out.println(temp);
        }
    }

    @Test
    public void findByIdtest(){
        Customer customer = customerdao.findById(103);
        System.out.println(customer);
    }

    @Test
    public void inserttest(){
        Customer cust = new Customer();
        cust.setCustomerNumber(497);
        cust.setCustomerName("Aftab Crop.");
        cust.setContactLastName("Parhizkari");
        cust.setContactFirstName("Afshin");
        cust.setPhone("21 4435 1478");
        cust.setAddressLine1("Shateri 6");
        cust.setAddressLine2("");
        cust.setCity("NYC");
        cust.setState("NY");
        cust.setPostalCode("45982");
        cust.setCountry("USA");
        cust.setSalesRepEmployeeNumber(1286);
        cust.setCreditLimit(new BigDecimal(73800));
        customerdao.insert(cust);
    }

    @Test
    public void updatetest(){
        Customer customer = customerdao.findById(497);
        customer.setCustomerName("Helman Co.");
        customer.setPhone("21 4430 6374");
        customerdao.update(customer);
    }

    @Test
    public void deletetest(){
        customerdao.delete(497);

    }
}