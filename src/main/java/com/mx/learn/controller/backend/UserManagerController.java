package com.mx.learn.controller.backend;

import com.mx.learn.common.Const;
import com.mx.learn.common.ServerResponse;
import com.mx.learn.pojo.User;
import com.mx.learn.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * UserManagerController
 *
 * @author dagu29
 * @date 2020/2/10 0010
 */
@Controller
@RequestMapping("/manager/user")
public class UserManagerController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session) {
        ServerResponse<User> response = iUserService.login(username, password);
        if (response.isSuccess()) {
            User user = response.getData();
            if (user.getRole() == Const.Role.ROLE_ADMIN) {
                session.setAttribute(Const.CURRENT_USER, user);
                return response;
            } else {
                return ServerResponse.createByErrorMessage("不是管理员权限");
            }
        }
        return response;
    }
}
