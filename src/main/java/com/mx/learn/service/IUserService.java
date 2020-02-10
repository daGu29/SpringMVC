package com.mx.learn.service;

import com.mx.learn.common.ServerResponse;
import com.mx.learn.pojo.User;

/**
 * IUserService
 *
 * @author dagu29
 * @date 2020/2/8 0008
 */
public interface IUserService {
    ServerResponse<User> login(String username, String password);

    ServerResponse<String> register(User user);

    ServerResponse<String> checkValid(String str, String type);

    ServerResponse selectQuestion(String username);

    ServerResponse<String> checkAnswer(String username,String question,String answer);

    ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken);

    ServerResponse<String> resetPassword(String passwordOld,String passwordNew,User user);

    ServerResponse<User> updateInformation(User user);

    ServerResponse<User> getInformation(Integer userId);
}
