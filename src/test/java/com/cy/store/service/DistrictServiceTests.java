package com.cy.store.service;

import com.cy.store.entity.Address;
import com.cy.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SuppressWarnings("ALL")
@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictServiceTests {
    @Autowired
    private IDistrictService iDistrictService;
    @Test
    public void getParent(){
        List<District> parent = iDistrictService.getParent("86");
        System.out.println(parent);
    }
    @Test
    public void findNameByCode(){
        System.out.println(iDistrictService.findNameByCode("610000"));
    }
}
