package com.example.controller;

import com.example.DTO.LoginRequest;
import com.example.Repository.UserRepository;
import com.example.entity.User;
import com.example.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;


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

    @Autowired
    public UserRepository userRepository;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        System.out.println("Received login request: " + user);
        Optional<User> userOptional = userRepository.findByName(user.getName());

        if (userOptional.isPresent()) {
            User user1 = userOptional.get();
            if (user1.getPassword().equals(user.getPassword())) {
                return ResponseEntity.ok().body("Login successful for user: " + user.getName());
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User newUser) {
        User savedUser = userRepository.save(newUser);
        return ResponseEntity.ok(savedUser); // Consider returning a DTO instead for security
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateProfile(@RequestBody User userDetails) {
        System.out.println( userDetails);
        User user = userRepository.findByName(userDetails.getName()).orElse(null);
        if (user != null) {
            user.setEmail(userDetails.getEmail());
            user.setName(userDetails.getName());
            user.setSignature(userDetails.getSignature());
            user.setPassword(userDetails.getPassword());
            user.setStatus(userDetails.getStatus());
            userRepository.save(user);
            return ResponseEntity.ok().body("Profile updated successfully");
        }
        return ResponseEntity.notFound().build();
    }

}
