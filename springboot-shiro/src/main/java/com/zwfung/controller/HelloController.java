package com.zwfung.controller;

import com.zwfung.entity.User;
import com.zwfung.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zw fung
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Resource
    private IUserService userService;

    @GetMapping
    public User helloUser() {
        return userService.getById(1L);
    }
}
