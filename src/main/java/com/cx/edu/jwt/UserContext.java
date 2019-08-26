package com.cx.edu.jwt;

import com.cx.edu.entity.user.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserContext implements AutoCloseable {

    private static final ThreadLocal<AuthorizedUser> CURRENT_USER = new ThreadLocal<>();

    public UserContext(AuthorizedUser user) {
        CURRENT_USER.set(user);
    }

    public static AuthorizedUser getCurrentUser() {
        return CURRENT_USER.get();
    }

    @Override
    public void close() {
        CURRENT_USER.remove();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AuthorizedUser {

        private Long id;
        private RoleEnum role;
        private String userName;

    }
}
