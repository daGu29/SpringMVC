package com.mx.learn.common.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * UserController
 *
 * @author dagu29
 * @date 2020/2/8 0008
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @param session session值
     * @return Object
     */
    @RequestMapping(value = "/login.do",method=RequestMethod.POST)
    @ResponseBody
    public Object login(String username, String password, HttpSession session) {
        return null;
    }
}
