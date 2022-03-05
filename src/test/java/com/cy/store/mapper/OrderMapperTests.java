package com.cy.store.mapper;

import com.cy.store.entity.Address;
import com.cy.store.entity.Order;
import com.cy.store.entity.OrderItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SuppressWarnings("ALL")
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderMapperTests {
    @Autowired
    private OrderMapper orderMapper;
    @Test
   public void insertOrderTest(){
        Order order = new Order();
        order.setUid(22);
        order.setRecvName("mingming");
        order.setRecvPhone("13382124115");
        orderMapper.insertOrder(order);
    }
    @Test
    public void insertOrderItemTest(){
//        OrderItem orderItem = new OrderItem();
//        orderItem.setId(1);
//        orderItem.setPid(10000003);
//        orderItem.setTitle("dddddd");
//        System.out.println(orderMapper.insertOrderItem(orderItem));
        OrderItem orderItem = new OrderItem();
        orderItem.setOid(1);
        orderItem.setPid(2);
        orderItem.setTitle("高档铅笔");
        Integer rows = orderMapper.insertOrderItem(orderItem);
        System.out.println("rows=" + rows);
    }
}
