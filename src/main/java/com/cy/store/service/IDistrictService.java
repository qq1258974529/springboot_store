package com.cy.store.service;
import com.cy.store.entity.Address;
import com.cy.store.entity.District;

import java.util.List;

public interface IDistrictService {
    /**
     * 根据父代号来查询区域信息（省市区）
     * @param parent
     * @return 父代号下的区域
     */
    List<District> getParent(String parent);
    String findNameByCode(String code);
}
