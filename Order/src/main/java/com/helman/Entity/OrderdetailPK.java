package com.helman.Entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;

@XmlRootElement(name = "OrderdetailPKXML")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderdetailPK implements Serializable {
    public OrderdetailPK(){}

    private Integer orderNumber;
    private String productCode;

    public OrderdetailPK(Integer orderNumber, String productCode) {
        this.orderNumber = orderNumber;
        this.productCode = productCode; }

    public Integer getOrderNumber() {
        return orderNumber;
    }
    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getProductCode() {
        return productCode;
    }
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @Override
    public String toString() {
        return "OrderdetailPK{" +
                "orderNumber=" + orderNumber +
                ", productCode='" + productCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderdetailPK that = (OrderdetailPK) o;
        return Objects.equals(orderNumber, that.orderNumber) &&
                Objects.equals(productCode, that.productCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber, productCode);
    }
}