package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.service.ex.*;
import com.cy.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * 控制层类的基类
 */
public class BaseController {
    public static final int OK = 200;
    @ExceptionHandler({ServiceException.class,FileUploadException.class})
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result = new JsonResult<>(e);
        if(e instanceof UsernameDuplicatedException){
            result.setState(4000);
            result.setMessage("用户名已经被占用");
        }else if(e instanceof UserNotFoundException){
            result.setState(4001);
            result.setMessage("用户数据不存在的异常");
        }else if(e instanceof PasswordNotMatchException){
            result.setState(4002);
            result.setMessage("用户名的密码错误的异常");
        }else if(e instanceof AddressCountLimitException){
            result.setState(4003);
            result.setMessage("用户的收货地址超出上限的异常");
        }else if(e instanceof AddressNotFoundException){
            result.setState(4004);
            result.setMessage("用户的收货地址数据不存在");
        }else if(e instanceof AccessDeniedException){
            result.setState(4005);
            result.setMessage("用户的收货地址数据非法访问的异常");
        }else if(e instanceof InsertException){
            result.setState(5000);
            result.setMessage("注册时产生未知的异常");
        }else if(e instanceof UpdateException){
            result.setState(5001);
            result.setMessage("更新时产生未知的异常");
        }else if(e instanceof DeleteException){
            result.setState(5002);
            result.setMessage("删除时产生未知的异常");
        }else if(e instanceof FileEmptyException){
            result.setState(6000);
        }else if(e instanceof FileSizeException){
            result.setState(6001);
        }else if(e instanceof FileTypeException){
            result.setState(6002);
        }else if(e instanceof FileStateException){
            result.setState(6003);
        }else if(e instanceof FileUploadIOException){
            result.setState(6004);
        }
        return result;
    }
    protected final Integer getuidFromSession(HttpSession session){
        return Integer.parseInt(session.getAttribute("uid").toString());
    }
    protected final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }
}
