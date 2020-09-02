package com.laishihui.mybatispluse.demo.service;

import com.laishihui.mybatispluse.demo.dao.UserMapper;
import com.laishihui.mybatispluse.demo.domain.Collect;
import com.laishihui.mybatispluse.demo.entity.User;
import com.sun.deploy.net.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Create by tachai on 2019-07-18 15:55
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
public class CollectService {


    Collect collect = new Collect();

//    if(collect.)

    @Autowired
    private UserMapper dao;




    @Cacheable(value = "realTime",key = "'user_'+#id")
    public User findUserById(Integer id){
        return dao.selectById(id);
    }

//    allEntries =true 把命名空间realTime的缓存都删掉
    @CacheEvict(value = "realTime",allEntries = true)
    @Transactional
    public int inserUser(User user){
        return dao.insert(user);
    }

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;


    //使用双重检测锁解决热点缓存问题
    public Integer userCount(){
        // 获取操作redis 对象
        BoundValueOperations<Object,Object> ops=redisTemplate.boundValueOps("count");
        // 从缓存中读取数据
        Object count = ops.get();
        if(count == null){
            synchronized (this){
               count = ops.get();
               if(count == null){
                   // 从DB中查询
                   count = dao.selectCount(null);
                   // 将查询到的数据写入到Redis缓存，并设置到期时限
                   ops.set(count,10, TimeUnit.SECONDS);
               }
            }
        }
        return (Integer) count;
    }




    public static void main(String[] args) throws URISyntaxException {

//        URI  source =  new URI("http://www.baidu.com/abcieme/index.html");
//        URI C = source.resolve(new URI("./a/sfdf.html"));
//        String mm = null;
//        mm=source.toString();
//        System.out.println(source.toString());
//        System.out.println(C);


        String mm = "<h1 class=\"title\">井冈山会师的历史地位和伟大意义</h1>";
        List<String> ss = findByReg(mm,"井冈山会师",16);
        ss.forEach(e->{
            System.out.println(e);
        });
    }



    public static List<String> findByReg(String input,String pattern,int max){
        List<String> results = new ArrayList<>();
        if(input == null){
            return results;
        }

        if(pattern==null||"".equals(pattern)){
            results.add(input);
            return results;
        }

        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(input);
        int i=0;
        while (i++<max&&m.find()){
            results.add(m.group().trim());
        }
        return results;
    }
}
