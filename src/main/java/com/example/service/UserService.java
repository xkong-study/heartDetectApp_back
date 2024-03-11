package com.example.service;

import com.example.Repository.UserRepository;
import com.example.entity.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //新增
    public String add(String data) {
        JSONObject jsonObject = new JSONObject(data);
        int heartRate = jsonObject.optInt("heartRate", 0); // 默认值为0
        int hrv = jsonObject.optInt("hrv", 0); // 默认值为0
        int pressure = jsonObject.optInt("pressure", 0); // 添加新字段 pressure

        User user = new User();
        user.setHeartRate(heartRate);
        user.setHrv(hrv);
        user.setPressure(pressure); // 设置血压
        user.setRecordDate(new Date()); // 设置当前时间为记录时间
        user.setDelStatus(false); // 默认未删除

        userRepository.save(user);
        return "添加成功！";
    }

    public boolean login(String name, String password) {
        User user = userRepository.findByName(name).orElse(null);
        System.out.println(password);
        System.out.println("----");
        System.out.println(user);
        return user != null && user.getPassword().equals(password);
    }

    //删除
    public String del(String data){
        JSONObject jsonObject = new JSONObject(data);
        String id = jsonObject.isNull("id")?"":jsonObject.optString("id");
        //根据id用户对象
        User user = userRepository.findOne(id.trim());
        if (user ==null){
            return "获取信息失败！";
        }
        user.setDelStatus(true);
        userRepository.save(user);
        return "删除成功！";
    }


    //获取所有用户
    public List<User> list(){
        //根据id用户对象
        List<User> userList = userRepository.findAllByDelStatus(false);

        return userList;
    }

}
