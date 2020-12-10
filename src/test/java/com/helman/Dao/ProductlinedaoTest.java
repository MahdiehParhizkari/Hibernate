package com.helman.Dao;

import com.helman.Entity.Productline;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ProductlinedaoTest {

    Productlinedao productlinedao = new Productlinedao();

    @Test
    public void findallTest(){
        List<Productline> productlineList =productlinedao.findall();
        for(Productline protemp:productlineList)
        {System.out.println(protemp.toString());}
    }

    @Test
    public void findByIdTest(){
        System.out.println(productlinedao.findById("Ships"));
    }

    @Test
    public void someColumnTest(){
        List<?> list = productlinedao.someColumn();
        for(int i = 0; i< list.size(); i++){
            Object[] row =(Object[]) list.get(i);
            System.out.println(row[0] + ", " + row[1]);
        }
    }

    @Test
    public void whereClauseTest(){
        List<?> list = productlinedao.whereClause("Ships");
        for (int i=0; i< list.size(); i++){
            Object[] row = (Object[]) list.get(i);
            System.out.println(row[0] + " " + row[1]);
        }
    }

    @Test
    public void aggregationTest(){
        List<?> list = productlinedao.aggregation();
        for(int i = 0; i<list.size(); i++){
            Object[] row = (Object[]) list.get(i);
            System.out.println(row[0]+ " " + row[1]);
        }
    }

    @Test
    public void joinedQueryTest(){
        List<?> list = productlinedao.joinedQuery();
        for(int i=0; i<list.size() ; i++){
            Object[] row = (Object[]) list.get(i);
            System.out.println(row[0]+","+row[1]+","+row[2]+","+row[3]);
        }
    }

    @Test
    public void insertTest(){
        Productline pl = new Productline();
        pl.setProductLine("airplanes");
        pl.setTextDescription("B50");
        pl.setHtmlDescription("https://github.com/MahdiehParhizkari");
        try{
            pl.setImage(Files.readAllBytes(Paths.get("b52.jpg").toAbsolutePath()));
        }catch (Exception e){
            System.out.println(e.toString());
        }
        productlinedao.insert(pl);
        System.out.println(productlinedao.findById("airplane"));
    }

    @Test
    public void updateTest(){
        Productline pl = productlinedao.findById("airplane");
        pl.setTextDescription("update B50");
        pl.setHtmlDescription("https://github.com/MahdiehParhizkari");
        pl.setImage(pl.getImage());
        productlinedao.update(pl);
        System.out.println(productlinedao.findById("airplane"));
    }

    @Test
    public void deleteTest(){
        Productline pl = productlinedao.findById("airplane");
        productlinedao.delete(pl);
    }

}