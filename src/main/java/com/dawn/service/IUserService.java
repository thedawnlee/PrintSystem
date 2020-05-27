package com.dawn.service;

import com.dawn.commons.ServerResponse;
import com.dawn.pojo.User;

public interface IUserService {

    ServerResponse getUserById(Integer userId);

    ServerResponse<User> login(String username, String password);

    ServerResponse<String> register(User user);

    ServerResponse<String> checkValid(String str,String type);

    ServerResponse resetUsername(User user , String username);

    ServerResponse resetEmail(User user , String email);

    ServerResponse resetPhone(User user , String phone);

    ServerResponse checkUsername(String username);
//    ServerResponse<String> selectQuestion(String username);
//
//    ServerResponse<String> checkAnswer(String username,String question,String answer);
//
//    ServerResponse<String> forgetResetPassword(String username,String passwordNew,String forgetToken);
//
    ServerResponse<String> resetPassword(String passwordOld,String passwordNew,User user);
//
//    ServerResponse<User> updateInformation(User user);
//
//    ServerResponse<User> getInformation(Integer userId);

    ServerResponse checkAdminRole(User user);

}
