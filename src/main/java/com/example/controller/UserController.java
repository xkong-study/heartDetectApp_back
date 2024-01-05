package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "user")
@Api(description = "人员管理")
public class UserController {

    @Autowired
    private UserService userService;

    //新增
    @ApiOperation(value = "新增心率和血压数据", notes = "添加一个新的心率和血压记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "data", value = "{\"heartRate\": 72, \"hrv\": 60, \"pressure\": 120}", dataType = "String", required = true, example = "{\"heartRate\": 72, \"hrv\": 60, \"pressure\": 120}")
    })
    @PostMapping(value = "/add")
    public String add(@RequestBody String data) {
        return userService.add(data);
    }


    @PostMapping(value = "/del")
    public String del(@RequestBody String data){
        return  userService.del(data);
    }

    //获取所有用户
    @PostMapping(value = "/list")
    public List<User> list(){
        return  userService.list();
    }

}
