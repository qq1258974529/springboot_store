package com.cy.store.service;

import com.cy.store.entity.Address;

public interface IAddressService {

    void addNewAddress(Address address,Integer uid,String username);
}
