package com.helman.Webservice;

/*
  @project order
  @Author Mahdieh Parhizkari
  @Date 3/24/21
  @Time 2:03 AM
  Created by Intellije IDEA
  Description: With Basic Authentication
*/

import com.helman.Dao.Employeedao;
import com.helman.Entity.Employee;
import com.helman.General.Log4j;
import com.helman.General.Logback;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.util.List;
import java.util.Map;

@WebService(name = "EmployeeInt", serviceName = "EmployeeSrv")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class EmployeeSop {
    Employeedao employeedao = new Employeedao();
    Security security = new Security();

    @Resource
    WebServiceContext wsctx;

    @WebMethod
    @WebResult(name = "Employees")
    public Employee[] findall(){
        try {
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String endcodUsrPwd = http_headers.get("Authorization").toString().replaceFirst("Basic ", "").
                    replaceFirst("\\[", "").replace("]","");
            if (!security.basicAuthCheck(endcodUsrPwd)) return null;

            List<Employee> employeeList = employeedao.findall();
            Employee[] ItemsArray = new Employee[employeeList.size()];
            Log4j.logger.info("{}.{}|Try:Send record to Soap", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return employeeList.toArray(ItemsArray);
        }catch (Exception e){
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    @WebMethod
    @WebResult(name = "Employee")
    public Employee findbyid(@WebParam(name = "employeeNumber") Long empnum){
        try{
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String encodUsrPwd = http_headers.get("Authorization").toString().replaceFirst("Basic ", "").
                    replaceFirst("\\[", "").replace("]","");
            if (!security.basicAuthCheck(encodUsrPwd)) return null;

            Employee employee = employeedao.findbyid(empnum);
            Log4j.logger.info("{}.{}|Try: Send record to Soap", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return employee;
        } catch (Exception e) {
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
/*
    <Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
    <Body>
        <insert xmlns="http://Webservice.helman.com/">
	        <Employee xmlns="">
		        <employeeNumber xmlns="">1001</employeeNumber>
		        <lastName xmlns="">Maraghi</lastName>
		        <firstName xmlns="">Shazy</firstName>
		        <extension xmlns="">x5800</extension>
		        <email xmlns="">shazy@gmail.com</email>
		        <officeCode xmlns="">1</officeCode>
		        <reportsTo xmlns="">1002</reportsTo>
		        <jobTitle xmlns="">IT</jobTitle>
    		</Employee>
        </insert>
    </Body>
</Envelope>
*/
    @WebMethod
    @WebResult(name= "ReturnStatus")
    public Long insert(@WebParam(name = "Employee") Employee employee){
        try {
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String encodUsrPwd = http_headers.get("Authorization").toString().replaceFirst("Basic ", "").
                    replaceFirst("\\[", "").replace("]", "");
            if (!security.basicAuthCheck(encodUsrPwd)) return null;

            Long returnStatus = employeedao.insert(employee);
            Log4j.logger.info("{}.{}|Try:Inserted", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return returnStatus;
        } catch (Exception e) {
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @WebMethod
    @WebResult(name = "ReturnStatus")
    public Long update(@WebParam(name = "Employee") Employee employee){
        try{
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String encodUsrPwd = http_headers.get("Authorization").toString().replaceFirst("Basic ", "").
                    replaceFirst("\\[", "").replace("]", "");
            if (!security.basicAuthCheck(encodUsrPwd)) return null;

            Long returnStatus = employeedao.update(employee);
            Log4j.logger.info("{}.{}|Try:Inserted", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return returnStatus;
        } catch (Exception e) {
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /*
    <Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
        <Body>
            <delete xmlns="http://Webservice.helman.com/">
                <employeeNumber xmlns="">1001</employeeNumber>
            </delete>
        </Body>
    </Envelope>
*/
    @WebMethod
    @WebResult(name = "ReturnStatus")
    public String delete(@WebParam(name = "employeeNumber") Long empnum){
        try{
            Map http_headers = (Map) wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            String encodUsrPwd=http_headers.get("Authorization").toString().replaceFirst("Basic ","")
                    .replaceFirst("\\[","").replace("]","");
            if(!security.basicAuthCheck(encodUsrPwd)) return null;

            Integer returnStatus = employeedao.delete(empnum);
            Log4j.logger.info("{}.{}|Try: deleted", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            return returnStatus.toString();
        } catch (Exception e) {
            Logback.logger.error("{}.{}|Exception:{}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
