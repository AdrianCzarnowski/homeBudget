package projects.adrian.homebudget.model.listener;

import jakarta.persistence.PrePersist;
import lombok.extern.slf4j.Slf4j;
import projects.adrian.homebudget.model.entity.BudgetEntity;

@Slf4j
public class BudgetEntityListener {

    @PrePersist
    public void beforeCreate(BudgetEntity budgetEntity){
        log.info("Budget entity has been created with id {}", budgetEntity);
    }
}
