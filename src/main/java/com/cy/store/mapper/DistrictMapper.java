package com.cy.store.mapper;

import com.cy.store.entity.District;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DistrictMapper {
    /**
     * 根据父代号查询区域
     * @param parent
     * @return 父区域下所有的区域列表
     */
    List<District> findByParent(String parent);

    /**
     * 根据code查名称
     * @param code
     * @return 区域名称
     */
    String findNameByCode(String code);
}
