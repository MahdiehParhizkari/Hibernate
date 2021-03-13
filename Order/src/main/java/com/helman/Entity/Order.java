package com.helman.Entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.helman.General.GregorianDate;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
@NamedQueries({
        @NamedQuery(name = "findallcity", query = "SELECT o FROM Order o where o.status = 'cancelled'")
})
@JsonFilter("Orderfilter")
public class Order {
    public Order(){}

    private Integer orderNumber;
    private Date orderDate;
    private Date requiredDate;
    private Date shippedDate;
    private String status;
    private String comments;
    private Integer customerNumber = null;

    private List<Orderdetail> orderdetails;
    private Customer customer;

    @Id
    @Column(name = "orderNumber")
    public Integer getOrderNumber() {
        return orderNumber;
    }
    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Column(name = "orderDate")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Column(name = "requiredDate")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    public Date getRequiredDate() {
        return requiredDate;
    }
    public void setRequiredDate(Date requiredDate) {
        this.requiredDate = requiredDate;
    }

    @Column(name = "shippedDate")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    public Date getShippedDate() {
        return shippedDate;
    }
    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    @Column(name = "status")
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "comments")
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }

    @Column(name = "customerNumber")
    public Integer getCustomerNumber() {
        return customerNumber;
    }
    public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }

    @OneToMany(mappedBy = "order")
    public List<Orderdetail> getOrderdetails() {
        return orderdetails;
    }
    public void setOrderdetails(List<Orderdetail> orderdetails) {
        this.orderdetails = orderdetails;
    }

    @ManyToOne
    @JoinColumn(name = "customerNumber", insertable = false, updatable = false)
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNumber=" + orderNumber +
                ", orderDate=" +GregorianDate.shamsiStr(GregorianDate.miladi2shamsi(orderDate)) +
                ", requiredDate=" +GregorianDate.shamsiStr(GregorianDate.miladi2shamsi(requiredDate)) +
                ", shippedDate=" +GregorianDate.shamsiStr(GregorianDate.miladi2shamsi( shippedDate)) +
                ", status='" + status + '\'' +
                ", comments='" + comments + '\'' +
                ", customerNumber=" + customerNumber +
                '}';
    }
    @Transient
    public Set<String> getfilters(){
        Set<String> hash_set = new HashSet<String>();
        hash_set.add("orderNumber");
        hash_set.add("orderDate");
        hash_set.add("requiredDate");
        hash_set.add("shippedDate");
        hash_set.add("status");
        hash_set.add("comments");
        hash_set.add("customerNumber");
        return hash_set;
    }
}