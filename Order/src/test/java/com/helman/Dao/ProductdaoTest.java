package com.helman.Dao;

import com.helman.Entity.Product;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class ProductdaoTest {

    Productdao productdao = new Productdao();

    @Test
    public void findall() {
        List<Product> products = productdao.findall();
        for (Product temp: products){
            System.out.println(temp);
        }
    }

    @Test
    public void inserttest(){
        Product pro = new Product();
        pro.setProductCode("S12-1088");
        pro.setProductName("Titaniom");
        pro.setProductLine("Planes");
        pro.setProductScale("1:72");
        pro.setProductVendor("Carousel DieCast Legends");
        pro.setProductDescription("The scale is copyed from other item");
        pro.setQuantityInStock(342);
        pro.setBuyPrice(new BigDecimal(86.28));
        pro.setMSRP(new BigDecimal(48.91));
        productdao.insert(pro);
    }

    @Test
    public void updatetest(){
        Product pro = productdao.findById("S12-1088");
        pro.setProductName("taitaei");
        productdao.update(pro);
    }

    @Test
    public void deletetest(){
        productdao.delete("S12-1088");
    }
}