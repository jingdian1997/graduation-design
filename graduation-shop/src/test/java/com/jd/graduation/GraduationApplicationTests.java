package com.jd.graduation;

import com.jd.graduation.Impl.UserServiceImpl;
import com.jd.graduation.VO.UserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GraduationApplicationTests {
    @Autowired
    private UserServiceImpl userService;

    @Test
    public void contextLoads() {
        UserVO userVO = userService.getWholeUser(7);
        System.out.println(userVO);
    }

}

