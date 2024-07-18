package projects.adrian.homebudget.model.listener;

import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import projects.adrian.homebudget.model.entity.CategoryEntity;

import java.time.LocalDateTime;

@Slf4j
public class CategoryEntityListener {

    @PostPersist
    public void postCreate(CategoryEntity categoryEntity) {
        log.info("Category has been created with name {}", categoryEntity.getName());
    }


    @PreUpdate
    public void beforeUpdate(CategoryEntity categoryEntity) {
        log.info("Category has been updated with id {}", categoryEntity.getCategoryId());
    }

    @PostUpdate
    public void postUpdate(CategoryEntity categoryEntity) {
        log.info("Update time {}", LocalDateTime.now());
    }


    @PreRemove
    public void beforeRemove(CategoryEntity categoryEntity) {
        log.info("Category has been removed user {}", categoryEntity.getUser());
    }

    @PostRemove
    public void postRemove(CategoryEntity categoryEntity) {
        log.info("Removed time {}", LocalDateTime.now());
    }
}
