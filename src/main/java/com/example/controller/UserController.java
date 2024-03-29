package com.example.controller;

import com.example.DTO.LoginRequest;
import com.example.DTO.UserInfo;
import com.example.Repository.UserRepository;
import com.example.entity.User;
import com.example.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.properties.ObjectProperty;
import lombok.extern.slf4j.Slf4j;
import java.awt.image.BufferedImage;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@Slf4j
@RequestMapping(value = "user")
@Api(description = "人员管理")
@CrossOrigin(origins = "*")
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
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String doctor = loginRequest.getDoctor();
        String name = loginRequest.getName();
        System.out.println(doctor);
        System.out.println(name);
        String password = loginRequest.getPassword();
        Optional<User> userOptional;

        if (doctor != null && !doctor.isEmpty()) {
            userOptional = userRepository.findTopByDoctor(doctor);
        } else {
            userOptional = userRepository.findTopByName(name);
        }

        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = userOptional.get();
        if (!password.equals(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Map<String, Object> userResponse = new HashMap<>();
        userResponse.put("name", user.getName());
        userResponse.put("avatar", user.getAvatar());
        if (doctor != null && !doctor.isEmpty()) {
            userResponse.put("doctor", user.getDoctor());
        }

        return ResponseEntity.ok().body(userResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("avatar") MultipartFile avatar) {
        try {
            User newUser = new User();
            newUser.setName(name);
            newUser.setEmail(email);
            newUser.setPassword(password);

            if (!avatar.isEmpty()) {
                InputStream inputStream = avatar.getInputStream();
                BufferedImage originalImage = ImageIO.read(inputStream);

                int newWidth = 100; // 调整为你需要的宽度
                int newHeight = (int) (originalImage.getHeight() * ((double) newWidth / originalImage.getWidth()));
                BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
                resizedImage.getGraphics().drawImage(originalImage.getScaledInstance(newWidth, newHeight, java.awt.Image.SCALE_SMOOTH), 0, 0, null);

                // 将压缩后的图像转换为字节数组
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ImageIO.write(resizedImage, "png", outputStream);

                newUser.setAvatar(outputStream.toByteArray());
                log.info(Arrays.toString(outputStream.toByteArray()));
                outputStream.close();
                inputStream.close();
            }

            User savedUser = userRepository.save(newUser);
            return ResponseEntity.ok(savedUser);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save user avatar");
        }
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

    @GetMapping("/listPatients/{doctorName}")
    public ResponseEntity<List<UserInfo>> getAllPatientsByDoctor(@PathVariable String doctorName) {
        System.out.println(doctorName);
        List<User> users = userRepository.findAllByDoctor(doctorName);
        List<UserInfo> userInfos = users.stream().map(user -> {
            UserInfo userInfo = new UserInfo();
            BeanUtils.copyProperties(user, userInfo);
            return userInfo;
        }).collect(Collectors.toList());
        if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userInfos);
    }

    @PostMapping("/sendAdvice")
    public ResponseEntity<?> sendAdvice(@RequestBody User user){
        List<User> users = userRepository.findAllByName(user.getName());
        String temp = user.getStatus();
        users.stream().map(item -> {
            item.setStatus(temp);
            userRepository.save(item);
            return item;
        }).collect(Collectors.toList());
        if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("success");
    }

}
