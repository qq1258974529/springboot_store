package com.cy.store.controller;

import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UsernameDuplicatedException;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController{
    @Autowired
    private IUserService userService;
    @RequestMapping("reg")
    public JsonResult<Void> reg(User user){
        userService.reg(user);
        return new JsonResult<>(OK);
    }
    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session){
        User data = userService.login(username, password);
        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",data.getUsername());
        System.out.println(getuidFromSession(session));
        System.out.println(getUsernameFromSession(session));

        return new JsonResult<User>(OK,data);
    }
    @RequestMapping(value = "change_password")
    public JsonResult<Void> changePassword(String oldPassword,String newPassword,HttpSession session){
        Integer uid = getuidFromSession(session);
        String username=getUsernameFromSession(session);
        userService.changePassword(uid,username,oldPassword,newPassword);
        return new JsonResult<>(OK);
    }
    @RequestMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session){
        User data = userService.getByUid(getuidFromSession(session));
        return new JsonResult<>(OK,data);
    }
    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user,HttpSession session){
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changeInfo(uid,username,user);
        return new JsonResult<>(OK);
    }

}
