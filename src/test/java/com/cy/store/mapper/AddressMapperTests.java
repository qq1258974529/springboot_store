package com.cy.store.mapper;

import com.cy.store.entity.Address;
import com.cy.store.entity.User;
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
public class AddressMapperTests {
    @Autowired
    private AddressMapper addressMapper;
    @Test
    public void insert(){
        Address address = new Address();
        address.setUid(1);
        address.setPhone("13382124115");
        address.setName("xxx");
        addressMapper.insert(address);
    }
    @Test
    public void coutByUid(){
        Integer integer = addressMapper.countByUid(1);
        System.out.println("-----"+integer);
    }

    @Test
    public void findByUid(){
        List<Address> byUid = addressMapper.findByUid(1);
        System.out.println(byUid);
    }
}
