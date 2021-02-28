package com.helman.General;

//@project order
//@Author Mahdieh Parhizkari
//@Date 2/25/21
//@Time 9:20PM
//        Created by Intellije IDEA
//        Description:JPA-Criteria

import com.helman.Webservice.CustomerWs;
import com.helman.Webservice.EmployeeWs;
import com.helman.Webservice.PaymentWs;
import com.helman.Webservice.UserWs;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/rest")
public class Myrestsevice extends Application {
    public Set<Class<?>> Myrestservice(){
        return new HashSet<Class<?>>(Arrays.asList(
                UserWs.class, CustomerWs.class, EmployeeWs.class, PaymentWs.class));
    }
}