package com.moba.constant;

public class ConstantKey {
    /**
     * 签名key
     */
    public static final String SIGNING_KEY = "moba-@spring-security-@Jwt!&Secret^#";


    /**
     * token签名的有效期（秒）
     */
    public static final Long TOKEN_VALID_SENCOND = 30*60L;

    /**
     * jwt请求header的key
     */
    public static final String JWT_HEADER = "Authorization";

    /**
     * jwt请求header的key对于value的前缀
     */
    public static final String JWT_TOKEN_HEADER = "Bearer ";
}
