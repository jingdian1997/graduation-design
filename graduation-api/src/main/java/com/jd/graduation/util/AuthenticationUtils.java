package com.jd.graduation.util;

import com.jd.graduation.entity.User;
import com.jd.graduation.model.AdminConfigVO;
import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

//@Component
public class AuthenticationUtils {
    private static RedisTemplate<String, Object> template = new RedisTemplate<>();
    public static final String COOKIE_NAME = "token";

    public static User getUserEntity(HttpServletRequest request){
        String key = getTokenFromCookie(request);
        if (key != null){
            User entity = (User) template.opsForValue().get(key);
            if (entity != null){
                //重设key，重置过期时间
                template.opsForValue().set(key, entity);
                return entity;
            }
        }

        return null;
    }

    public static AdminConfigVO getAdminEntity(HttpServletRequest request){
        String key = getTokenFromCookie(request);
        if (key != null){
            AdminConfigVO entity = (AdminConfigVO) template.opsForValue().get(key);
            if (entity != null){
                //重设key，重置过期时间
                template.opsForValue().set(key, entity);
                return entity;
            }
        }

        return null;
    }

    public static void set(String key, Object entity) {
        template.opsForValue().set(key, entity);
    }

    public static Object get(String key) {
        return template.opsForValue().get(key);
    }

    public static void delete(String key) {
    }

    //非注解方式获取cookie，解析token
    private static String getTokenFromCookie(HttpServletRequest request){
        Cookie[] cookies =  request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(COOKIE_NAME)){
                    return cookie.getValue();
                }
            }
        }

        return  null;
    }
}