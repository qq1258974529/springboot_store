package com.cy.store.mapper;

import com.cy.store.entity.Address;
import com.cy.store.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

@Mapper
public interface UserMapper {
    /**插入用户的数据
     * @param user 用户的数据
     * @return 受影响的行数（增删改，都受影响的行数作为返回值，可以根据返回值来判断是否执行成功）
     */
    Integer insert(User user);

    /**
     * 根据用户名来查询用户的数据
     * @param username 用户名
     * @return 如果找到对应的用户名则返回这个用户的数据，如果没有找到则返回null值
     */
    User findByUsername(String username);

    /**
     * 根据用户的uid来修改密码
     * @param uid
     * @return
     */
    Integer updatePasswordByUid(Integer uid, String password, String modifiedUser, Date modifiedTime);

    /**
     * 根据用户的id查询用户的数据
     * @param uid
     * @return
     */
    User findByUid(Integer uid);

    /**
     * 更新用户的数据信息
     * @param user
     * @return
     */
    Integer updateInfoByUid(User user);

    /**
     * 修改用户的头像
     * @param uid
     * @param avatar
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updateAvatarByUid(Integer uid,String avatar, String modifiedUser, Date modifiedTime);
}
