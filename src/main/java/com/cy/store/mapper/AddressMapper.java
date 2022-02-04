package com.cy.store.mapper;

import com.cy.store.entity.Address;

public interface AddressMapper {
    /**
     * 插入用户收货地址
     * @param address
     * @return 受影响的行数
     */
    Integer insert(Address address);

    /**
     * 根据用户的id统计收获地址数量
     * @param uid
     * @return 当前用户的收获地址总数
     */
    Integer countByUid(Integer uid);
}
