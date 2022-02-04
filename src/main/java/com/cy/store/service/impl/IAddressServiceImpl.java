package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.IDistrictService;
import com.cy.store.service.ex.AddressCountLimitException;
import com.cy.store.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.Date;
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class IAddressServiceImpl implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private IDistrictService districtService;
    @Value("${user.address.max-count}")
    private Integer maxCount;
    @Override
    public void addNewAddress(Address address, Integer uid, String username) {
        Integer count = addressMapper.countByUid(uid);
        if(count >= maxCount){
            throw new AddressCountLimitException("用户收货地址超出上限");
        }
        String ProvinceName = districtService.findNameByCode(address.getProvinceCode());
        String CityName = districtService.findNameByCode(address.getCityCode());
        String AreaName = districtService.findNameByCode(address.getAreaCode());
        address.setProvinceName(ProvinceName);
        address.setCityName(CityName);
        address.setAreaName(AreaName);

        address.setUid(uid);
        if(count == 0){
            address.setIsDefault(1);
        }else{
            address.setIsDefault(0);
        }
        address.setCreatedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());
        Integer rows = addressMapper.insert(address);
        if(rows!=1){
            throw new InsertException("插入用户的收货地址产生异常");
        }
    }
}
