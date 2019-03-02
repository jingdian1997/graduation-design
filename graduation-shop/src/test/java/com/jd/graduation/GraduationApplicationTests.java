package com.jd.graduation;

import com.jd.graduation.DO.CartDO;
import com.jd.graduation.Impl.CartServiceImpl;
import com.jd.graduation.Impl.CategoryServiceImpl;
import com.jd.graduation.VO.CategoryVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GraduationApplicationTests {
    @Autowired
    private CartServiceImpl cartService;

    @Test
    public void contextLoads() {
//        CategoryVO categoryVO = categoryService.categoryTreeSet();
//        CategoryVO result = categoryService.findById(18);

//        List<Integer> ids = categoryService.getAllCategoryIds(15);
        List<Integer> ids = new ArrayList<>();
        ids.add(8);
        ids.add(9);
        List<CartDO> c = cartService.getInIds(ids, 1);
        System.out.println(c);
    }

}

