package com.laishihui.mybatispluse.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by tachai on 2019-07-24 19:56
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@RestController
@PropertySource(value = "classpath:custom.properties",encoding = "utf-8")
public class ReadConfigController {


    @Value("$(user.name)")
    private String userName;

    
}
