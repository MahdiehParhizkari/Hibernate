package com.helman.Dao;

/*@project order
@Author Mahdieh Parhizkari
@Date 1/25/21
@Time 3:34PM
        Created by Intellije IDEA
        Description:JPA-Criteria*/

import com.helman.General.Mysession;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class JRsqlFunction {
    /*Remove all below tags in Reports
        <fieldDescription><![CDATA[]]></fieldDescription>*/
    public static void viewReport(String path, Map parameters) throws JRException, SQLException {
        JasperReport jreport = JasperCompileManager.compileReport(path);
        Connection connection = Mysession.getConnection() ;
            JasperPrint jprint = JasperFillManager.fillReport(jreport, parameters, connection);
        connection.close();
        JasperViewer.viewReport(jprint,false);
    }
}
