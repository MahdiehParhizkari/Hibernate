package com.helman.Entity;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "orderdetails")
@IdClass(OrderdetailPK.class)
public class Orderdetail {
    public Orderdetail(){
    }

    private Integer orderNumber;
    private String productCode;
    private Integer quantityOrdered;
    private BigDecimal priceEach;
    private Integer orderLineNumber;

    private Order order;
    private Product product;


    @Id
    @Column(name = "orderNumber")
    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Id
    @Column(name = "productCode")
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @Column(name = "quantityOrdered")
    public Integer getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(Integer quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    @Column(name = "priceEach")
    public BigDecimal getPriceEach() {
        return priceEach;
    }

    public void setPriceEach(BigDecimal priceEach) {
        this.priceEach = priceEach;
    }

    @Column(name = "orderLineNumber")
    public Integer getOrderLineNumber() {
        return orderLineNumber;
    }

    public void setOrderLineNumber(Integer orderLineNumber) {
        this.orderLineNumber = orderLineNumber;
    }

    @ManyToOne
    @JoinColumn(name = "orderNumber", insertable = false, updatable = false)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @ManyToOne
    @JoinColumn(name = "productCode", updatable = false, insertable = false)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Orderdetail{" +
                "orderNumber=" + orderNumber +
                ", productCode='" + productCode + '\'' +
                ", quantityOrdered=" + quantityOrdered +
                ", priceEach=" + priceEach +
                ", orderLineNumber=" + orderLineNumber +
                '}';
    }
}