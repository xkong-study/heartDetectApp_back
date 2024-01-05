package com.example.Repository;

import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,String>,JpaSpecificationExecutor<User> {


    //获取未删除的所有用户
    List<User> findAllByDelStatus(boolean delStatus);
}
