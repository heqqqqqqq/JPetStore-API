package org.csu.mypetstore.api;

import org.csu.mypetstore.api.entity.Category;
import org.csu.mypetstore.api.persistence.CategoryMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MypetstoreApiDemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    void testMybatisPlus(){
        List<Category> categoryList=categoryMapper.selectList(null);//null表示没有条件
        System.out.println(categoryList);//可以直接打印，因为lombok为我们生成了一个toString()方法
        System.out.println(categoryList.size());
    }

}
