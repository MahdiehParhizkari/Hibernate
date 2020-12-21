package com.helman.Dao;

import com.helman.Entity.Office;
import org.junit.Test;

import java.util.List;

public class OfficedaoTest {
    Officedao officedao = new Officedao();

    @Test
    public void findAllTest(){
        List<Office> officeList = officedao.findAll();
        for (Office temp : officeList){
            System.out.println(temp);
        }
    }
    @Test
    public void findByIdTest(){
        System.out.println(officedao.findById("3"));
    }
    @Test
    public void someColumnTest(){
        List<?> list = officedao.someColumn();
        for (int i=0; i<list.size(); i++){
            Object[] row = (Object[]) list.get(i);
            System.out.println(row[0]+ "," +row[1]);
        }
    }
    @Test
    public void whereClauseTest(){
        List<?> list = officedao.whereClause("3");
        for (int i=0; i<list.size(); i++){
            Object[] row = (Object[]) list.get(i);
            System.out.println(row[0]+", "+row[1]);
        }
    }
    @Test
    public void joinedQueryTest(){
        List<?> list = officedao.joinedQuery();
        for (int i=0; i<list.size(); i++){
            Object[] row = (Object[]) list.get(i);
            System.out.println(row[1]+","+row[1]+","+row[2]+","+row[3]);
        }
    }


    @Test
    public void insertTest(){
        Office office = new Office();
        office.setOfficeCode("9");
        office.setCity("Florida");
        office.setPhone("+1 235 6799 3209");
        office.setAddressLine1("koche 1");
        office.setAddressLine2("Kh shahran");
        office.setState("level 1");
        office.setCountry("USA");
        office.setPostalCode("25876");
        office.setTerritory("NA");
        officedao.insert(office);
    }
    @Test
    public void updateTest(){
        Office office = officedao.findById("10");
        office.setState("level 6");
        office.setCity("qazvin");
        office.setPhone("44253448");
        office.setAddressLine1("shahran");
        office.setAddressLine2("toghani");
        office.setCountry("Irag");
        office.setPostalCode("2584569631");
        office.setTerritory("Teh");

        officedao.update(office);
    }
    @Test
    public void deleteTest(){
        Office office = officedao.findById("9");
        officedao.delete(office);
    }

}