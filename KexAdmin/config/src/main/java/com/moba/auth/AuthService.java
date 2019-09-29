package com.moba.auth;

import com.moba.domain.UserInfo;

public interface AuthService {
    UserInfo register(UserInfo userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
    Boolean remove(String oldToken);
}