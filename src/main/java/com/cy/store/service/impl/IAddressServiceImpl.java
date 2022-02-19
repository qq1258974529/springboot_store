package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.IDistrictService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.Date;
import java.util.List;

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

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list = addressMapper.findByUid(uid);
        for (Address address : list) {
//            address.setUid(null);
//            address.setAid(null);
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setZip(null);
            address.setPhone(null);
            address.setTel(null);
            address.setCreatedTime(null);
            address.setCreatedUser(null);
            address.setModifiedTime(null);
            address.setModifiedUser(null);
        }
        return list;
    }


    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if (result == null){
            throw new AddressNotFoundException("收货地址不存在");
        }
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }
        Integer rows = addressMapper.updateNonDefault(uid);
        if(rows < 1){
            throw new UpdateException("更新数据产生未知的异常");
        }
        rows = addressMapper.updateDefaultByAid(aid, username, new Date());
        if(rows < 1){
            throw new UpdateException("更新数据产生未知的异常");
        }
    }


    @Override
    public void delete(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if(result == null){
            throw  new AddressNotFoundException("收货地址找不到异常");
        }
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问的异常");
        }

        Integer rows = addressMapper.deleteByAid(aid);
        if(rows != 1){
            throw new DeleteException("删除数据产生未知的异常");
        }
        Integer count = addressMapper.countByUid(uid);
        if(count == 0){
            return;
        }
        Address address = addressMapper.findLastModified(uid);
        if(result.getIsDefault() ==  0){
            return;
        }
        rows = addressMapper.updateDefaultByAid(address.getAid(),username,new Date());
        if(rows != 1){
            throw new UpdateException("更新数据时产生未知的异常");
        }
    }
}
