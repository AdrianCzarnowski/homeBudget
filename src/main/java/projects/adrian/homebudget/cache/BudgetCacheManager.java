package projects.adrian.homebudget.cache;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import projects.adrian.homebudget.model.entity.BudgetEntity;
import projects.adrian.homebudget.repository.BudgetRepository;

import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
@Slf4j
public class BudgetCacheManager {
    private BudgetRepository budgetRepository;

    @Cacheable(value = "budgets", key = "#uuid")
    public Optional<BudgetEntity> findById(UUID uuid){
        return budgetRepository.findById(uuid);
    }

    @CacheEvict(value = "budgets", key = "#uuid")
    public void clearBudgetCache(UUID uuid){
        log.info("Budget cache has been cleared {}", uuid);
    }
}
