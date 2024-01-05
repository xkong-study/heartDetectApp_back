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

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

}
