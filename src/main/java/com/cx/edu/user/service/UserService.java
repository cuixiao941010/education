package com.cx.edu.user.service;


import com.cx.edu.base.service.BaseService;;
import com.cx.edu.entity.user.User;
import com.cx.edu.user.model.LoginCondition;
import com.cx.edu.user.model.LoginDTO;

public interface UserService extends BaseService<User, Long> {

    LoginDTO login(LoginCondition loginCondition);

    void logout();

}
