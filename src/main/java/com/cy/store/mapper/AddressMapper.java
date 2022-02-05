package com.cy.store.mapper;

import com.cy.store.entity.Address;
import com.cy.store.entity.District;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
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

    /**
     * 根据用户的id查询用户的收货地址
     * @param uid
     * @return 收货地址
     */

    List<Address> findByUid(Integer uid);

}
