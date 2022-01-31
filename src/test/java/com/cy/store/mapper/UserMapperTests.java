package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SuppressWarnings("ALL")
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void insert(){
//        System.out.println(userMapper);
        User user = new User();
        user.setUsername("tim");
        user.setPassword("123");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }
    @Test
    public void find(){
        User user01 = userMapper.findByUsername("user01");
        System.out.println(user01);
    }
    @Test
    public void updatePasswordByUid(){
        Integer integer = userMapper.updatePasswordByUid(8, "111", "test02", new Date());
        System.out.println(integer);
    }
    @Test
    public void findByUid(){
        User byUid = userMapper.findByUid(8);
        System.out.println(byUid);
    }
    @Test
    public void UpdateInfoByUid(){
        User user = new User();
        user.setUid(7);
        user.setPhone("15511110000");
        user.setEmail("test01@qq.com");
        user.setGender(1);
        userMapper.updateInfoByUid(user);
    }
}
