package com.helman.General;

//@project order
//@Author Mahdieh Parhizkari
//@Date 2/25/21
//@Time 9:20PM
//        Created by Intellije IDEA
//        Description:JPA-Criteria

import com.helman.Webservice.*;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/rest")
public class Myrestsevice extends Application {
    public Set<Class<?>> Myrestservice(){
        return new HashSet<Class<?>>(Arrays.asList(
                UserRst.class, CustomerRst.class, EmployeeRst.class, OfficeRst.class, OrderRst.class,
                OrderdetailRst.class, ProductRst.class, ProductlineRst.class, PaymentRst.class));
    }
}