package com.cy.store.service;

import com.cy.store.entity.Address;

import java.util.List;

public interface IAddressService {

    void addNewAddress(Address address,Integer uid,String username);
    List<Address> getByUid(Integer uid);
    void setDefault(Integer aid,Integer uid,String username);
    void delete(Integer aid,Integer uid,String username);
    Address getByAid(Integer aid,Integer uid);
}
