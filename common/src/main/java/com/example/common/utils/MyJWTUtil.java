package com.example.common.utils;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * author ye
 * createDate 2022/8/19  20:27
 */
public class MyJWTUtil {
    private final static long EXPIRE_TIME =  1000 * 60 * 60 * 24;
    public static String createToken(String userId) {
        Map<String, Object> map = new HashMap<String, Object>(){
            private static final long serialVersionUID = 1L;
            {
                put("userId", userId);
                put("expire_time", System.currentTimeMillis() + EXPIRE_TIME);
            }
        };
        return JWTUtil.createToken(map, "001215".getBytes());
    }

    public static boolean isExpire(String token) {
        JWT jwt = JWTUtil.parseToken(token);
        return System.currentTimeMillis() > (long)jwt.getPayload("expire_time");
    }

    public static String getUserId(String token) {
        JWT jwt = JWTUtil.parseToken(token);
        return jwt.getPayload("userId").toString();
    }
}
