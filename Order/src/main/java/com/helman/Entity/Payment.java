package com.helman.Entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "payments")
@IdClass(PaymentPK.class)
@JsonFilter("Paymentfilter")
@XmlRootElement(name = "PaymentXML")
@XmlAccessorType(XmlAccessType.FIELD)
public class Payment {
    public Payment(){}

    private Integer customerNumber;
    private String checkNumber;
    private Date paymentDate;
    private BigDecimal amount;

    @XmlTransient
    private Customer customer;

    @Id
    @Column(name = "customerNumber")
    public Integer getCustomerNumber() {
        return customerNumber;
    }
    public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }

    @Id
    @Column(name = "checkNumber")
    public String getCheckNumber() {
        return checkNumber;
    }
    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    @Column(name = "paymentDate")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    public Date getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Column(name = "amount")
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @ManyToOne
    @JoinColumn(name = "customerNumber" , insertable = false, updatable = false)
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "customerNumber=" + customerNumber +
                ", checkNumber='" + checkNumber + '\'' +
                ", paymentDate=" + paymentDate +
                ", amount=" + amount +
                ", customer=" + customer +
                '}';
    }
    @Transient
    public Set<String> getfilters(){
        Set<String> hash_set = new HashSet<String>();
        hash_set.add("customerNumber");
        hash_set.add("checkNumber");
        hash_set.add("paymentDate");
        hash_set.add("amount");
        return hash_set;
    }
}