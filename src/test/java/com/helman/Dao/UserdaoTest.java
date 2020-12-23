package com.helman.Dao;

import com.helman.Entity.User;
import org.junit.Test;

import java.util.List;

class UserdaoTest {
    Userdao userdao = new Userdao();

    @Test
    public void findAll() {
        List<User> userList = userdao.findAll();
        for (User temp : userList){
            System.out.println(temp);
        }
    }

    @Test
    public void findById() {
        User user = userdao.findById(1);
        System.out.println(user);
    }

    @Test
    public void login() {
        System.out.println(userdao.login("helman"));
    }

    @Test
    public void someColumn() {
        List<?> list = userdao.someColumn();
        for (int i=0; i<list.size(); i++){
            Object[] row = (Object[]) list.get(i);
            System.out.println(row[0]+" "+row[1]);
        }
    }

    @Test
    public void insert() {
        User user = new User();
        user.setId(3);
        user.setUsername("afy");
        user.setPassword("789");
        user.setEmployeefk(1056);
        userdao.insert(user);
    }

    @Test
    public void update() {
        User us= userdao.findById(3);
        us.setUsername("sady");
        us.setPassword("159");
        us.setEmployeefk(1088);
        userdao.update(us);
    }

    @Test
    public void delete() {
        User user = userdao.findById(3);
        userdao.delete(user);
    }
}