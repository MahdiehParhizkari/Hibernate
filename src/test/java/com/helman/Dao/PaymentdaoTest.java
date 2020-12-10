package com.helman.Dao;

import com.helman.Entity.Payment;
import com.helman.Entity.PaymentPK;
import com.helman.General.GregorianDate;
import org.junit.Test;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PaymentdaoTest {
    Paymentdao paymentdao = new Paymentdao();

    @Test
    public void findAllTest(){
        List<Payment> list = paymentdao.findAll();
        for(Payment temp : list){
            System.out.println(temp);
        }
    }

    /*@Test
    public void findByIdTest(){
        PaymentPK pPK = new PaymentPK();
        pPK.setCheckNumber("HQ336336");
        pPK.setCustomerNumber(103);
        Payment payment = paymentdao.findById(pPK);
        System.out.println(payment);
    }*/

    @Test
    public void someColumnTest(){
        PaymentPK pPK = new PaymentPK();
        pPK.setCheckNumber("HQ336336");
        pPK.setCustomerNumber(103);
        List<?> list = paymentdao.someColumn(pPK);
        for (int i=0; i<list.size(); i++){
            Object[] row = (Object[]) list.get(i);
            System.out.println(row[0]+","+row[1]);
        }
    }

    @Test
    public void whereClauseTest(){
        List<?> list = paymentdao.whereClause(103);
        for (int i=0; i<list.size(); i++) {
            Object[] row = (Object[]) list.get(i);
            System.out.println(row[0] + "," + row[1]);
        }
    }

    @Test
    public void aggregationTest(){
        List<?> list = paymentdao.aggregation();
        for (int i=0; i<list.size(); i++) {
            Object[] row = (Object[]) list.get(i);
            System.out.println(row[0] + "," + row[1]);
        }
    }

    @Test
    public void joinedQueryTest(){
        List<?> list = paymentdao.joinedQuery(103);
        for (int i=0; i<list.size(); i++) {
            Object[] row = (Object[]) list.get(i);
            System.out.println(row[0] + "," + row[1]);
        }
    }

    @Test
    public void insertTest(){
        Payment payment = new Payment();
        payment.setCustomerNumber(103);
        payment.setCheckNumber("HQ336338");
        payment.setPaymentDate(new Date());
        payment.setAmount(new BigDecimal("842.27"));
        paymentdao.insert(payment);
    }

    @Test
    public void updateTest(){
        PaymentPK pPk = new PaymentPK();
        pPk.setCheckNumber("HQ336338");
        pPk.setCustomerNumber(103);
        Payment payment = paymentdao.findById(pPk);
        payment.setAmount(new BigDecimal("563.76"));
        payment.setPaymentDate(GregorianDate.shamsi2miladi(1388, 5-1, 15));
        paymentdao.update(payment);
    }

    @Test
    public void deleteTest(){
        PaymentPK pPK = new PaymentPK();
        pPK.setCustomerNumber(103);
        pPK.setCheckNumber("HQ336338");
        Payment p = paymentdao.findById(pPK);
        paymentdao.delete(p);
    }
}