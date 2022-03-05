package com.cy.store.service;

import com.cy.store.entity.Address;
import com.cy.store.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SuppressWarnings("ALL")
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceTests {
    @Autowired
    private IOrderService iOrderService;
    @Test
    public void create(){
        Integer[] cids = {2,3};
        Order order = iOrderService.create(6, 9, "test", cids);
        System.out.println(order);
    }

}
