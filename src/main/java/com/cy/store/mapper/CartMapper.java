package com.cy.store.mapper;

import com.cy.store.entity.Cart;
import com.cy.store.vo.CartVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface CartMapper {
    Integer insert(Cart cart);
    Integer updateNumByCid(Integer cid, Integer num, String modifiedUser, Date modifiedTime);
    Cart findByUidAndPid(Integer uid,Integer pid);
    List<CartVO> findVOByUid(Integer uid);
    Cart findByCid(Integer cid);
    List<CartVO> findVOByCid(Integer[] cids);
}
