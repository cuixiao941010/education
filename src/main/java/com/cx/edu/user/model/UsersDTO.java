package com.cx.edu.user.model;

import com.cx.edu.entity.user.enums.RoleEnum;
import lombok.Data;

@Data
public class UsersDTO {

    private Long id;

    private RoleEnum role;

    private String userName;

    private String email;

    private String mobile;

}
