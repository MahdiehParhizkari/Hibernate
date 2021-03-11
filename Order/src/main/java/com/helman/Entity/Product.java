package com.helman.Entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@JsonFilter("Productfilter")
public class Product {
    public Product(){
    }

    private String productCode;
    private String productName;
    private String productLine;
    private String productScale;
    private String productVendor;
    private String productDescription;
    private Integer  quantityInStock;
    private BigDecimal buyPrice;
    @JsonView
    private BigDecimal MSRP;

    private Productline productline;
    private List<Orderdetail> orderdetails;

    @Id
    @Column(name = "productCode")
    public String getProductCode() {
        return productCode;
    }
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @Column(name = "productName")
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }


    @Column(name = "productLine")
    public String getProductLine() {
        return productLine;
    }
    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    @Column(name = "productScale")
    public String getProductScale() {
        return productScale;
    }
    public void setProductScale(String productScale) {
        this.productScale = productScale;
    }

    @Column(name = "productVendor")
    public String getProductVendor() {
        return productVendor;
    }
    public void setProductVendor(String productVendor) {
        this.productVendor = productVendor;
    }

    @Column(name = "productDescription")
    public String getProductDescription() {
        return productDescription;
    }
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    @Column(name = "quantityInStock")
    public Integer getQuantityInStock() {
        return quantityInStock;
    }
    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    @Column(name = "buyPrice")
    public BigDecimal getBuyPrice() {
        return buyPrice;
    }
    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    @Column(name = "MSRP")
    public BigDecimal getMSRP() {
        return MSRP;
    }
    public void setMSRP(BigDecimal MSRP) {this.MSRP = MSRP;}

    @ManyToOne
    @JoinColumn(name = "productLine",referencedColumnName = "productLine",insertable = false,updatable = false)
    public Productline getProductline() {
        return productline;
    }
    public void setProductline(Productline productline) {
        this.productline = productline;
    }

    @OneToMany(mappedBy = "product")
    public List<Orderdetail> getOrderdetails() {
        return orderdetails;
    }
    public void setOrderdetails(List<Orderdetail> orderdetails) {
        this.orderdetails = orderdetails;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", productLine='" + productLine + '\'' +
                ", productScale='" + productScale + '\'' +
                ", productVendor='" + productVendor + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", quantityInStock=" + quantityInStock +
                ", buyPrice=" + buyPrice +
                ", MSRP=" + MSRP +
               // ", productline=" + productline +
                '}';
    }
}