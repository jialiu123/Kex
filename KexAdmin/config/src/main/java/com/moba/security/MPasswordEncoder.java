package com.moba.security;

import com.moba.utils.encrypt.DESPlusUtils;
import org.springframework.security.crypto.password.PasswordEncoder;


public class MPasswordEncoder implements PasswordEncoder {

    /**
     * 对密码进行加密并返回
     */
    @Override
    public String encode(CharSequence rawPassword) {
        String encPassword = "";
        try {
            DESPlusUtils des = new DESPlusUtils();
            encPassword = des.encrypt((String) rawPassword);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return encPassword;
    }

    /**
     * 验证密码是否正确
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {

        DESPlusUtils des;
        try {
            des = new DESPlusUtils();
            return encodedPassword.equals(des.encrypt((String) rawPassword));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;

    }
}