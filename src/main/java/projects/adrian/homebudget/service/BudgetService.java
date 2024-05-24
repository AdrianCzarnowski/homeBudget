package projects.adrian.homebudget.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import projects.adrian.homebudget.mapper.BudgetMapper;
import projects.adrian.homebudget.model.dto.BudgetDto;
import projects.adrian.homebudget.model.entity.BudgetEntity;
import projects.adrian.homebudget.repository.BudgetRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BudgetService {
    private final BudgetRepository budgetRepository;
    private final BudgetMapper budgetMapper;

    public List<BudgetEntity> getAllBudgets() {
        return budgetRepository.findAll();
    }
    public BudgetDto findById(UUID uuid) {
        Optional<BudgetEntity> optionalBudgetEntity = budgetRepository.findById(uuid);
        return optionalBudgetEntity.map(budgetMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Can not find user by given id " + uuid));
    }
}
