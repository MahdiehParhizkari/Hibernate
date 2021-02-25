package com.helman.Dao;

import com.helman.Entity.Orderdetail;
import com.helman.Entity.OrderdetailPK;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class OrderdetaildaoTest {

    Orderdetaildao orderdetaildao = new Orderdetaildao();

    @Test
    public void findAllTest() {
        List<Orderdetail> list = orderdetaildao.findAll();
        for (Orderdetail temp : list) {
            System.out.println(temp);
        }
    }
    @Test
    public void findByIdTest() {
        OrderdetailPK orderdetailPK = new OrderdetailPK();
        orderdetailPK.setOrderNumber(10100);
        orderdetailPK.setProductCode("S18_1749");
        Orderdetail orderdetail = orderdetaildao.findById(orderdetailPK);
        System.out.println(orderdetail);
    }
    @Test
    public void someColumnTest() {
        List<?> list = orderdetaildao.someColumn();
        for (int i = 0; i < list.size(); i++) {
            Object[] row = (Object[]) list.get(i);
            System.out.println(row[0] + "," + row[1]);
        }
    }
    @Test
    public void whereClauseTest() {
        List<?> list = orderdetaildao.whereClause(10100);
        for (int i = 0; i < list.size(); i++) {
            Object[] row = (Object[]) list.get(i);
            System.out.println(row[0] + "," + row[1]);
        }
    }
    @Test
    public void aggregationTest() {
        List<?> list = orderdetaildao.aggregation("S18_1749");
        for (int i = 0; i < list.size(); i++) {
            Object[] row = (Object[]) list.get(i);
            System.out.println(row[0] + "," + row[1] + "," + row[2]);
        }
    }
    @Test
    public void joinedQueryTest() {
        List<?> list = orderdetaildao.joinedQuery();
        for (int i = 0; i < list.size(); i++) {
            Object[] row = (Object[]) list.get(i);
            System.out.println(row[0] + "," + row[1] + "," + row[2]);
        }
    }
    @Test
    public void joinedQuery1Test(){
        List<?> list = orderdetaildao.joinedQuery1("S18_1749");
        for (int i = 0; i < list.size(); i++) {
            Object[] row = (Object[]) list.get(i);
            System.out.println(row[0] + "," + row[1] + "," + row[2]);
        }
    }

    @Test
    public void insertTest(){
        Orderdetail orderdetail = new Orderdetail();
        orderdetail.setOrderNumber(10100);
        orderdetail.setProductCode("S10_1678");
        orderdetail.setQuantityOrdered(20);
        orderdetail.setPriceEach(new BigDecimal("20.20"));
        orderdetail.setOrderLineNumber(20);
        orderdetaildao.insert(orderdetail);
    }

    @Test
    public void updateTest(){
        OrderdetailPK odpk = new OrderdetailPK();
        odpk.setOrderNumber(10100);
        odpk.setProductCode("S10_1678");
        Orderdetail od = orderdetaildao.findById(odpk);
        orderdetaildao.update(od);
    }

    @Test
    public void deleteTest(){
        OrderdetailPK odpk = new OrderdetailPK();
        odpk.setOrderNumber(10100);
        odpk.setProductCode("S10_1678");
        Orderdetail od =orderdetaildao.findById(odpk);
        orderdetaildao.delete(od);
    }
}