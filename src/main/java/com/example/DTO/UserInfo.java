package com.example.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


@Entity
public class UserInfo {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id", columnDefinition = "varchar(32) COMMENT '数据主键ID'")
    private String id;

    @Column(name = "name", columnDefinition = "varchar(32) COMMENT '数据主键ID'")
    private String name;

    @Column(name = "doctor", columnDefinition = "varchar(32) COMMENT '数据主键ID'")
    private String doctor;

    @Column(name = "heartRate", columnDefinition = "int(11) COMMENT '心率'")
    private Integer heartRate;

    @Column(name = "hrv", columnDefinition = "int(11) COMMENT '心率变异性'")
    private Integer hrv;

    @Column(name = "pressure", columnDefinition = "int(11) COMMENT '血压'")
    private Integer pressure;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "recordDate", columnDefinition = "datetime COMMENT '记录时间'")
    private Date recordDate;

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

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String status) {
        this.doctor = doctor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

}
