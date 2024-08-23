package projects.adrian.homebudget.service;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import projects.adrian.homebudget.cache.BudgetCacheManager;
import projects.adrian.homebudget.event.BudgetChangeEvent;
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
    private final BudgetCacheManager budgetCacheManager;

    private final ApplicationEventPublisher applicationEventPublisher;

    public List<BudgetDto> getAllBudgets() {
        return budgetRepository.findAll().stream().map(budgetMapper::toDto).toList();
    }

    public BudgetDto findByBudgetId(UUID uuid) {
        Optional<BudgetEntity> optionalBudgetEntity = budgetCacheManager.findById(uuid);
        return optionalBudgetEntity.map(budgetMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Can not find budget by given id " + uuid));
    }

    public BudgetDto saveBudget(BudgetDto budgetDto) {
        BudgetEntity budgetEntity = budgetMapper.toEntity(budgetDto);
        BudgetEntity savedEntity = budgetRepository.save(budgetEntity);
        applicationEventPublisher.publishEvent(new BudgetChangeEvent(this, budgetDto));
        budgetCacheManager.clearBudgetCache(budgetDto.budgetId());
        return budgetMapper.toDto(savedEntity);
    }

    public void deleteBudget(UUID budgetId) {
        if (!budgetRepository.existsById(budgetId)) {
            throw new RuntimeException("Transaction with ID " + budgetId + " does not exist");
        }
        budgetRepository.deleteById(budgetId);
    }

    public BudgetDto findByCategoryId(UUID categoryId) {
        Optional<BudgetEntity> optionalBudgetEntity = budgetRepository.findByCategoryCategoryId(categoryId);
        return optionalBudgetEntity.map(budgetMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Can not find budget by given category id " + categoryId));
    }

    public  boolean checkItemExistById(UUID uuid){
        return budgetRepository.existsById(uuid);
    }

    //TODO - exception obsługujący błedy przyjmujący HHTP status i wiadomość oraz Exeption Handler któy będzie łapał to i drukował klientowi to co chce
    //TODO - poprawić wszystkie mappery
    //TODO - napisać własny aspect czy bisuness object version który przyszedł jest większy od 0. ( Trzeba dobrać się do Body z requesta i pobrać business version. W adnotacji  nie dodaje, żadnych pól)
}
