package com.cx.edu.user.model;

import com.cx.edu.entity.user.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    private Long id;

    private RoleEnum role;

    private String authorization;

}
