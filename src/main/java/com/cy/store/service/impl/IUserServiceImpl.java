package com.cy.store.service.impl;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.websocket.Session;
import java.util.Date;
import java.util.UUID;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class IUserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    public IUserServiceImpl() {
        super();
    }

    @Override
    public void reg(User user) {
        String username = user.getUsername();
        String oldpassword = user.getPassword();
        //findByUsername(username)
        User result = userMapper.findByUsername(username);
        if(result != null){
            throw new UsernameDuplicatedException("用户名被占用");
        }
        //获取盐值（随机生成一个盐值）
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Password = getMD5Password(oldpassword, salt);
        //补全数据
        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);
        user.setPassword(md5Password);
        user.setSalt(salt);
        //执行注册业务功能的实现
        Integer rows = userMapper.insert(user);
        if(rows != 1){
            throw new InsertException("在用户注册过程中产生了未知的异常");
        }
    }

    @Override
    public User login(String username, String password) {
        User result = userMapper.findByUsername(username);
        if(result == null){
            throw new UserNotFoundException("用户数据不存在");
        }
        String oldpassword = result.getPassword();
        String salt = result.getSalt();
        String newMd5Password = getMD5Password(password, salt);
        if(!newMd5Password.equals(oldpassword)){
            throw new PasswordNotMatchException("用户密码错误");
        }
        if(result.getIsDelete() == 1){
            throw new UserNotFoundException("用户数据不存在");
        }
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        return user;
    }

    /**
     * md5加密算法
     */
    private String getMD5Password(String password,String salt){
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes());
        }
        return password;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User result = userMapper.findByUid(uid);
        if(result == null || result.getIsDelete() == 1){
            throw new UserNotFoundException("用户数据不存在");
        }
        String oldMd5Password = getMD5Password(oldPassword, result.getSalt());
        if(!result.getPassword().equals(oldMd5Password)){
            throw new PasswordNotMatchException("密码错误");
        }
        String newMd5Password = getMD5Password(newPassword, result.getSalt());
        Integer rows = userMapper.updatePasswordByUid(uid, newMd5Password, username, new Date());
        if(rows!=1){
            throw new UpdateException("更新数据异常");
        }
    }

    @Override
    public User getByUid(Integer uid) {
        User result = userMapper.findByUid(uid);
        if(result == null || result.getIsDelete() == 1){
            throw new UserNotFoundException("用户数据不存在");
        }
        User user = new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());
        return user;
    }

    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User result = userMapper.findByUid(uid);
        if(result==null||result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }
        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        Integer rows = userMapper.updateInfoByUid(user);
        if(rows != 1){
            throw new UpdateException("更新数据时产生未知的异常");
        }
    }


    @Override
    public void changeAvatar(Integer uid, String avatar, String username) {
        User result = userMapper.findByUid(uid);
        if(result == null | result.getIsDelete().equals(1)){
            throw new UserNotFoundException("用户数据不存在");
        }
        Integer rows = userMapper.updateAvatarByUid(uid, avatar, username, new Date());
        if(rows != 1){
            throw new UpdateException("更新用户头像产生的未知异常");
        }
    }
}
