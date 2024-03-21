package com.example.Repository;

import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    List<User> findAllByDelStatus(boolean delStatus);

    @Query("SELECT u FROM User u WHERE u.name = ?1 AND u.delStatus = false")
    List<User> findActiveUsersByName(String name);

    Optional<User> findByName(String name);

    Optional<User> findByDoctor(String doctor);

    List<User> findAllByDoctor(String doctor);

    List<User> findAllByName(String name);

    @Query("SELECT u FROM User u WHERE u.heartRate BETWEEN ?1 AND ?2 AND u.delStatus = false")

    List<User> findUsersByHeartRateRange(Integer minRate, Integer maxRate);

    Optional<User> findTopByDoctor(String doctor);

    Optional<User> findTopByName(String name);
}
