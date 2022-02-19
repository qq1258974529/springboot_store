package com.cy.store.service;

import com.cy.store.entity.Address;
import com.cy.store.entity.User;
import com.cy.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SuppressWarnings("ALL")
@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTests {
    @Autowired
    private IAddressService iAddressService;
    @Test
    public void addNewAddress(){
        Address address = new Address();
        address.setPhone("9999");
        address.setName("ssss");
        iAddressService.addNewAddress(address,1,"管理员");
    }
    @Test
    public void setDefault(){
        iAddressService.setDefault(5,9,"admin");
    }

    @Test
    public void delete(){
        iAddressService.delete(4,9,"管理员");
    }
}
