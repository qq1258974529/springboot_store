package com.cy.store.service;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.ex.ServiceException;
import com.cy.store.service.impl.IUserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SuppressWarnings("ALL")
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Autowired
    private IUserService iUserService;
    @Test
    public void reg(){
        try {
            User user = new User();
            user.setUsername("yuanxin02");
            user.setPassword("123");
            iUserService.reg(user);
            System.out.println("ok");
        } catch (ServiceException e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void login(){
        User user = iUserService.login("test02","123");
        System.out.println(user);
    }


    @Test
    public void changePassword() {
        iUserService.changePassword(7,"test01","123","111");
    }

    @Test
    public void getByUid(){
        System.out.println(iUserService.getByUid(7));
    }
    @Test
    public void changeInfo(){
        User user = new User();
        user.setPhone("111111111");
        user.setEmail("123@qq.com");
        user.setGender(0);
        iUserService.changeInfo(7,"test01",user);

    }
}
