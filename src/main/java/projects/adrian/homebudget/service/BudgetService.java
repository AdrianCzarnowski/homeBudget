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

    public List<BudgetDto> getAllBudgets() {
        return budgetRepository.findAll().stream().map(budgetMapper::toDto).toList();
    }

    public BudgetDto findByBudgetId(UUID uuid) {
        Optional<BudgetEntity> optionalBudgetEntity = budgetRepository.findById(uuid);
        return optionalBudgetEntity.map(budgetMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Can not find budget by given id " + uuid));
    }

    public BudgetDto saveBudget(BudgetDto budgetDto) {
        BudgetEntity budgetEntity = budgetMapper.toEntity(budgetDto);
        BudgetEntity savedEntity = budgetRepository.save(budgetEntity);
        return budgetMapper.toDto(savedEntity);
    }

    public void deleteBudget(UUID budgetId) {
        if (!budgetRepository.existsById(budgetId)) {
            throw new RuntimeException("Transaction with ID " + budgetId + " does not exist");
        }
        budgetRepository.deleteById(budgetId);
    }

    public BudgetDto findByCategoryId(UUID categoryId){
        Optional<BudgetEntity> optionalBudgetEntity = budgetRepository.findByCategoryCategoryId(categoryId);
        return optionalBudgetEntity.map(budgetMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Can not find budget by given category id " + categoryId));
    }
}
