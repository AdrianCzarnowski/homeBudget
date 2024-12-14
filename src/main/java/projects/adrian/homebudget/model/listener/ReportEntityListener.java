package projects.adrian.homebudget.model.listener;

import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import projects.adrian.homebudget.model.entity.ReportEntity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Slf4j
public class ReportEntityListener {
    @PrePersist
    public void beforeCreate(ReportEntity reportEntity) {
        reportEntity.setStartTime(Timestamp.valueOf(LocalDateTime.now()));
        log.info("Report has been started on {}", reportEntity.getStartTime());
    }

    @PostPersist
    public void postCreate(ReportEntity reportEntity) {
        log.info("Report has been started on {}", reportEntity.getEndTime());
    }


    @PreUpdate
    public void beforeUpdate(ReportEntity reportEntity) {
        log.info("Report has been updated with id {}", reportEntity.getReportId());
    }

    @PostUpdate
    public void postUpdate(ReportEntity reportEntity) {
        log.info("Update time {}", LocalDateTime.now());
    }


    @PreRemove
    public void beforeRemove(ReportEntity reportEntity) {
        log.info("Report has been removed user {}", reportEntity.getUser());
    }

    @PostRemove
    public void postRemove(ReportEntity reportEntity) {
        log.info("Removed time {}", LocalDateTime.now());
    }
}
