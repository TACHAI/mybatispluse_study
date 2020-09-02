package com.laishihui.mybatispluse.demo;

import com.laishihui.mybatispluse.demo.dao.UserMapper;
import com.laishihui.mybatispluse.demo.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Create by tachai on 2019-07-09 16:25
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void select(){
        List<User> userList = userMapper.selectList(null);

        Assert.assertEquals(5,userList.size());


        userList.forEach(System.out::println);
    }
}
