package projects.adrian.homebudget.model.listener;

import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import projects.adrian.homebudget.model.entity.BudgetEntity;
import projects.adrian.homebudget.model.entity.CategoryEntity;

import java.time.LocalDateTime;

@Slf4j
public class BudgetEntityListener {

    @PrePersist
    public void beforeCreate(BudgetEntity budgetEntity, CategoryEntity categoryEntity) {
        budgetEntity.setCategory(categoryEntity);
        log.info("Budget entity has been created with category name {}", categoryEntity.getName());
    }

    @PostPersist
    public void postCreate(BudgetEntity budgetEntity) {
        log.info("Budget was created at year {}", budgetEntity.getYearDt());
    }


    @PreUpdate
    public void beforeUpdate(BudgetEntity budgetEntity) {
        log.info("Budget entity has been updated with id {}", budgetEntity.getUser());
    }

    @PostUpdate
    public void postUpdate(BudgetEntity budgetEntity) {
        log.info("Update time {}", LocalDateTime.now());
    }


    @PreRemove
    public void beforeRemove(BudgetEntity budgetEntity) {
        log.info("Budget entity has been removed with userId name {}", budgetEntity.getUser());
    }

    @PostRemove
    public void postRemove(BudgetEntity budgetEntity) {
        log.info("Removed time {}", LocalDateTime.now());
    }

}
