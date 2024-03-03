package com.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;


@Entity
@Table(name = "Health")
public class User {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id", columnDefinition = "varchar(32) COMMENT '数据主键ID'")
    private String id;

    @Column(name = "name", columnDefinition = "varchar(32) COMMENT '数据主键ID'")
    private String name;

    @Column(name = "email", columnDefinition = "varchar(32) COMMENT '数据主键ID'")
    private String email;

    @Column(name = "password", columnDefinition = "varchar(32) COMMENT '数据主键ID'")
    private String password;

    @Column(name = "heartRate", columnDefinition = "int(11) COMMENT '心率'")
    private Integer heartRate;

    @Column(name = "hrv", columnDefinition = "int(11) COMMENT '心率变异性'")
    private Integer hrv;

    @Column(name = "pressure", columnDefinition = "int(11) COMMENT '血压'")
    private Integer pressure;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "recordDate", columnDefinition = "datetime COMMENT '记录时间'")
    private Date recordDate;

    @Column(name = "delStatus", columnDefinition = "bit(1) COMMENT '删除标记（0：正常，1：删除）'")
    private boolean delStatus;

    @Column(name = "signature", columnDefinition = "varchar(32) COMMENT '数据主键ID'")
    private String signature;

    @Column(name = "status", columnDefinition = "varchar(32) COMMENT '数据主键ID'")
    private String status;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    public Integer getHrv() {
        return hrv;
    }

    public void setHrv(Integer hrv) {
        this.hrv = hrv;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public boolean isDelStatus() {
        return delStatus;
    }

    public void setDelStatus(boolean delStatus) {
        this.delStatus = delStatus;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User() {
        // 无参构造函数
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
