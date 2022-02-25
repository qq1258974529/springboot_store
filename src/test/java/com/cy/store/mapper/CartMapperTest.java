package com.cy.store.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@SpringBootTest
public class CartMapperTest {
    @Autowired
    private CartMapper cartMapper;
    @Test
    public void findVOByUid(){
        System.out.println(cartMapper.findVOByUid(9));
    }
    @Test
    public void findByCid(){
        System.out.println(cartMapper.findByCid(1));
    }
    @Test
    public void findVOByCids(){
        Integer[] cids = {1,2,3,4};
        System.out.println(cartMapper.findVOByCid(cids));
    }
}
