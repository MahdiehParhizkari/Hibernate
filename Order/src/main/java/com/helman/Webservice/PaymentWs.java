package com.helman.Webservice;

//@project order
//@Author Mahdieh Parhizkari
//@Date 2/25/21
//@Time 9:27PM
//        Created by Intellije IDEA
//        Description:JPA-Criteria


import com.helman.Dao.Paymentdao;
import com.helman.Entity.Payment;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/payment")
public class PaymentWs {
    private Paymentdao paymentdao = new Paymentdao();
    private List<Payment> paymentList = new ArrayList<>();

    @GET
    @Path("/findall")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Payment> findall(){
        paymentList.clear();
        paymentList = paymentdao.findAll();
        return paymentList;
    }

}
