package com.helman.Entity;

import javax.persistence.*;
import javax.xml.bind.DatatypeConverter;
import java.util.List;

@Entity
@Table(name = "productlines")
public class Productline {
    public Productline(){
    }

    private String productLine;
    private String textDescription;
    private String htmlDescription;
    private byte[] image;
    private String photo;

    private List<Product> products=null;

    @Id
    @Column(name = "productLine")
    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    @Column(name = "textDescription")
    public String getTextDescription() {
        return textDescription;
    }

    public void setTextDescription(String textDescription) {
        this.textDescription = textDescription;
    }

    @Column(name = "htmlDescription")
    public String getHtmlDescription() {
        return htmlDescription;
    }

    public void setHtmlDescription(String htmlDescription) {
        this.htmlDescription = htmlDescription;
    }

    @Column(name = "image")
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Transient
    public String getPhoto() {
        return DatatypeConverter.printBase64Binary(image);
    }

    @OneToMany(mappedBy = "productLine")
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Productline{" +
                "productLine='" + productLine + '\'' +
                ", textDescription='" + textDescription + '\'' +
                ", htmlDescription='" + htmlDescription + '\'' +
                ", products=" + products +
                '}';
    }


}
