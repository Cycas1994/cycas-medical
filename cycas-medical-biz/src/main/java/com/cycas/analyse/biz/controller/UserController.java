package com.cycas.analyse.biz.controller;

import com.cycas.analyse.biz.entity.User;
import com.cycas.analyse.biz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xin.na
 * @since 2024/7/18 17:45
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("list")
    public List<User> list(){
        return userService.list();
    }

}
