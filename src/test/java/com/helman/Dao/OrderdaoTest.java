package com.helman.Dao;

import com.helman.Entity.Order;
import com.helman.General.GregorianDate;
import org.junit.Test;
import java.util.List;

public class OrderdaoTest {

    Orderdao orderdao = new Orderdao();

    @Test
    public void findallcity(){
        List<Order> orderList = orderdao.findallcity();
        for(Order temp : orderList){
            System.out.println(temp);
        }
    }

    @Test
    public void findall() {
        List<Order> orderList = orderdao.findall();
        for (Order temp : orderList){
            System.out.println(temp);
        }
    }

    @Test
    public void insertTest(){
        Order order = new Order();
        order.setOrderNumber(10426);
        order.setOrderDate(GregorianDate.shamsi2miladi(1385,3-1,20));
        order.setRequiredDate(GregorianDate.shamsi2miladi(1386,3-1,20));
        order.setShippedDate(GregorianDate.shamsi2miladi(1387,3-1,20));
        order.setStatus("shipped");
        order.setComments("");
        order.setCustomerNumber(496);
        orderdao.insert(order);
        System.out.println(orderdao.findById(10426));
    }

    @Test
    public void updateTest(){
        Order order = orderdao.findById(10426);
        order.setStatus("Cancelled");
        order.setComments("Hi");
        orderdao.update(order);
    }

    @Test
    public void deleteTest(){
        orderdao.delete(10426);
    }
}