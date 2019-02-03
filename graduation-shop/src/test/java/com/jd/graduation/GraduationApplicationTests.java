package com.jd.graduation;

import com.jd.graduation.Impl.CategoryServiceImpl;
import com.jd.graduation.VO.CategoryVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GraduationApplicationTests {
    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void contextLoads() {
        CategoryVO categoryVO = categoryService.categoryTreeSet();
//        CategoryVO result = categoryService.findById(18);

        List<Integer> ids = categoryService.getAllCategoryIds(15);
        System.out.println("");
    }

}

