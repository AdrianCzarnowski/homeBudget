package projects.adrian.homebudget.model.listener;

import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import projects.adrian.homebudget.model.entity.UserEntity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Slf4j
public class UserEntityListener {

    @PrePersist
    public void beforeCreate(UserEntity userEntity) {
        userEntity.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        log.info("User entity has been created with id {}", userEntity.getUserId());
    }

    @PostPersist
    public void postCreate(UserEntity userEntity) {
        log.info("Action after userId creation time {}", userEntity.getCreatedAt());
    }


    @PreUpdate
    public void beforeUpdate(UserEntity userEntity) {
        log.info("User entity has been updated with id {}", userEntity.getUserId());
    }

    @PostUpdate
    public void postUpdate(UserEntity userEntity) {
        log.info("Update time {}", LocalDateTime.now());
    }


    @PreRemove
    public void beforeRemove(UserEntity userEntity) {
        log.info("User entity has been removed with userId name {}", userEntity.getUserName());
    }

    @PostRemove
    public void postRemove(UserEntity userEntity) {
        log.info("Removed time {}", LocalDateTime.now());
    }

    @PostLoad
    public void postLoad(UserEntity userEntity){
        userEntity.setToken("token123");
    }
}
