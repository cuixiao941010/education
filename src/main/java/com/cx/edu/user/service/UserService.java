package com.cx.edu.user.service;


import com.cx.edu.base.service.BaseService;;
import com.cx.edu.entity.user.User;
import com.cx.edu.user.model.LoginCondition;
import com.cx.edu.user.model.LoginDTO;
import com.cx.edu.user.model.RegisterCondition;
import com.cx.edu.user.model.UsersDTO;

import java.util.List;

public interface UserService extends BaseService<User, Long> {

    LoginDTO login(LoginCondition loginCondition);

    void logout();

    void register(RegisterCondition registerCondition);

    List<UsersDTO> getUsers(Integer page, Integer pageSize, String userName, String mobile);

    void deleteUser(Long userId);

}
