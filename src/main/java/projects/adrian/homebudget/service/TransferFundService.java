package projects.adrian.homebudget.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projects.adrian.homebudget.model.dto.BudgetDto;
import projects.adrian.homebudget.model.dto.CategoryDto;


import java.util.UUID;

@Service
@AllArgsConstructor
public class TransferFundService {

    private BudgetService budgetService;

    @Transactional
    public void transferFundsBetweenCategories(UUID categoryFounder, UUID categoryReceiver, float amount) {

        //TODO - zrobić walidację dla wartości i czy budget istnieje oraz wystawić endpoint,
        // skorzystać z bliblioteki do matematyki aby opperację ładniej zapisać

        BudgetDto founderBudgetDto = budgetService.findByCategoryId(categoryFounder);
        BudgetDto receiverBudgetDto = budgetService.findByCategoryId(categoryReceiver);

        float amountFounderAfterTransfer = founderBudgetDto.amount() - amount;
        float amountReceiverAfterTransfer = receiverBudgetDto.amount() + amount;

        founderBudgetDto = setBudgetAmount(founderBudgetDto, amountFounderAfterTransfer);
        receiverBudgetDto = setBudgetAmount(receiverBudgetDto, amountReceiverAfterTransfer);

        budgetService.saveBudget(founderBudgetDto);
        budgetService.saveBudget(receiverBudgetDto);
    }

    private BudgetDto setBudgetAmount(BudgetDto budgetDto, float amount) {
        return new BudgetDto(budgetDto.budgetID(), budgetDto.userId(), budgetDto.category(),
                amount, budgetDto.monthDt(), budgetDto.yearDt(), budgetDto.startTime(), budgetDto.endTime(), budgetDto.generatedDate());
    }

}
