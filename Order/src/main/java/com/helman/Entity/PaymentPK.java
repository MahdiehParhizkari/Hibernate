package com.helman.Entity;

import java.io.Serializable;
import java.util.Objects;

public class PaymentPK implements Serializable {
    public PaymentPK(){
    }

    private Integer customerNumber;
    private String checkNumber;

    public PaymentPK(Integer customerNumber, String checkNumber) {
        this.customerNumber = customerNumber;
        this.checkNumber = checkNumber;
    }

    public Integer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    @Override
    public String toString() {
        return "PaymentPK{" +
                "customerNumber=" + customerNumber +
                ", checkNumber='" + checkNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentPK paymentPK = (PaymentPK) o;
        return Objects.equals(customerNumber, paymentPK.customerNumber) &&
                Objects.equals(checkNumber, paymentPK.checkNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerNumber, checkNumber);
    }
}
