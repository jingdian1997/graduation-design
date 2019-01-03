package com.jd.graduation.service;

import com.jd.graduation.entity.User;
import com.jd.graduation.model.AdminConfigVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.concurrent.TimeUnit;

@Service
public class AuthenticationService {
    private static final Integer EXPIRE_TIME = 60;
    private final RedisTemplate<String, Object> template;

    @Autowired
    public AuthenticationService(RedisTemplate<String, Object> template) {
        this.template = template;
    }

    public User getUserEntity(String key){
        if (key != null){
            User entity = (User) template.opsForValue().get(key);
            if (entity != null){
                //重设key，重置过期时间
                set(key, entity);
                return entity;
            }
        }

        return null;
    }

    public AdminConfigVO getAdminEntity(String key){
        if (key != null){
            AdminConfigVO entity = (AdminConfigVO) template.opsForValue().get(key);
            if (entity != null){
                //重设key，重置过期时间
                set(key, entity);
                return entity;
            }
        }

        return null;
    }

    public void set(String key, Object entity) {
        template.opsForValue().set(key, entity, EXPIRE_TIME, TimeUnit.MINUTES);
    }

    public Object get(String key) {
        return template.opsForValue().get(key);
    }

    public void delete(String key) {
        //一秒后过期，同时清空内容
        template.opsForValue().set(key, null, 1, TimeUnit.SECONDS);
    }

    /**
     * @param accountName 用户名
     * @return 用户名+毫秒时间经md5加密生成简易token
     */
    public String makeToken(String accountName) {
        long time = System.currentTimeMillis();
        accountName += time;
        return DigestUtils.md5DigestAsHex(accountName.getBytes());
    }
}