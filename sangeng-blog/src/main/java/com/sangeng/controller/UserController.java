package com.sangeng.controller;

import com.sangeng.annotation.SystemLog;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.User;
import com.sangeng.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@Api(tags = "用户接口")
public class UserController {

    @Autowired
    private UserService userService;

    //获取用户信息
    @GetMapping("/userInfo")
    public ResponseResult userInfo(){
        return userService.userInfo();
    }

    //注册用户
    @PostMapping("/register")
    public ResponseResult register(@RequestBody User user){
       return userService.register(user);
    }

    //修改用户信息
    @SystemLog(businessName = "更新用户信息")
    @RequestMapping(value="/userInfo",method=RequestMethod.PUT)
    public ResponseResult upadteUserInfo(@RequestBody User user){
        return userService.upadteUserInfo(user);
    }
}

