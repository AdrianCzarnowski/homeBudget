package projects.adrian.homebudget.model.listener;

import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import projects.adrian.homebudget.model.entity.TransactionEntity;
import projects.adrian.homebudget.model.entity.UserEntity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Slf4j
public class TransactionalEntityListener {
    @PrePersist
    public void beforeCreate(TransactionEntity transactionEntity) {
        transactionEntity.setTransactionDate(Timestamp.valueOf(LocalDateTime.now()));
        log.info("Transaction has been created with id {}", transactionEntity.getTransactionId());
    }

    @PostPersist
    public void postCreate(TransactionEntity transactionEntity) {
        log.info("Action after transactionId creation time {}", transactionEntity.getTransactionDate());
    }


    @PreUpdate
    public void beforeUpdate(TransactionEntity transactionEntity) {
        log.info("Transaction has been updated with id {}", transactionEntity.getTransactionId());
    }

    @PostUpdate
    public void postUpdate(TransactionEntity transactionEntity) {
        log.info("Update time {}", LocalDateTime.now());
    }


    @PreRemove
    public void beforeRemove(TransactionEntity transactionEntity) {
        log.info("Transaction has been removed user {}", transactionEntity.getUser());
    }

    @PostRemove
    public void postRemove(TransactionEntity transactionEntity) {
        log.info("Removed time {}", LocalDateTime.now());
    }

}
