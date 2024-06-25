package projects.adrian.homebudget.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projects.adrian.homebudget.model.dto.BudgetDto;
import projects.adrian.homebudget.model.dto.CategoryDto;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

public class TransferFundServiceTest {
    private TransferFundService transferFundService;
    private BudgetService budgetService;

    @BeforeEach
    void setUp() {
        budgetService = Mockito.mock(BudgetService.class);
        transferFundService = new TransferFundService(budgetService);
    }

    @Test
    void transferFundsBetweenCategories_FundsTransferredSuccessfully() {
        //given
        UUID categoryFounder = UUID.randomUUID();
        UUID categoryReceiver = UUID.randomUUID();
        float transferAmount = 100.0f;

        CategoryDto categoryDto1 = new CategoryDto(categoryFounder, UUID.randomUUID(), "category1", "type1", "description1");
        CategoryDto categoryDto2 = new CategoryDto(categoryReceiver, UUID.randomUUID(), "category2", "type2", "description2");

        BudgetDto founderBudgetDto = new BudgetDto(UUID.randomUUID(), UUID.randomUUID(), categoryDto1, 500.0f, 1, 2024, null, null, null);
        BudgetDto receiverBudgetDto = new BudgetDto(UUID.randomUUID(), UUID.randomUUID(), categoryDto2, 300.0f, 1, 2024, null, null, null);

        Mockito.when(budgetService.findByCategoryId(categoryFounder)).thenReturn(founderBudgetDto);
        Mockito.when(budgetService.findByCategoryId(categoryReceiver)).thenReturn(receiverBudgetDto);

        Mockito.doAnswer(invocation -> {
            BudgetDto dto = invocation.getArgument(0);
            if (dto.category().categoryId().equals(categoryFounder)) {
                assertEquals(400.0f, dto.amount());
            } else if (dto.category().categoryId().equals(categoryReceiver)) {
                assertEquals(400.0f, dto.amount());
            }
            return null;
        }).when(budgetService).saveBudget(any(BudgetDto.class));

        //when
        transferFundService.transferFundsBetweenCategories(categoryFounder, categoryReceiver, transferAmount);

        //then
        Mockito.verify(budgetService).findByCategoryId(categoryFounder);
        Mockito.verify(budgetService).findByCategoryId(categoryReceiver);
        Mockito.verify(budgetService, Mockito.times(2)).saveBudget(any(BudgetDto.class));
    }

    @Test
    void transferFundsBetweenCategories_FounderBudgetNotFound_throwException() {
        //given
        UUID categoryFounder = UUID.randomUUID();
        UUID categoryReceiver = UUID.randomUUID();
        float transferAmount = 100.0f;
        //when
        Mockito.when(budgetService.findByCategoryId(categoryFounder)).thenThrow(new RuntimeException("Can not find budget by given category id " + categoryFounder));

        //then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> transferFundService.transferFundsBetweenCategories(categoryFounder, categoryReceiver, transferAmount));
        assertEquals("Can not find budget by given category id " + categoryFounder, exception.getMessage());
    }

    @Test
    void transferFundsBetweenCategories_ReceiverBudgetNotFound_throwException() {
        //given
        UUID categoryFounder = UUID.randomUUID();
        UUID categoryReceiver = UUID.randomUUID();
        float transferAmount = 100.0f;

        CategoryDto categoryDto1 = new CategoryDto(categoryFounder, UUID.randomUUID(), "category1", "type1", "description1");

        BudgetDto founderBudgetDto = new BudgetDto(UUID.randomUUID(), UUID.randomUUID(), categoryDto1, 500.0f, 1, 2024, null, null, null);
        //when
        Mockito.when(budgetService.findByCategoryId(categoryFounder)).thenReturn(founderBudgetDto);
        Mockito.when(budgetService.findByCategoryId(categoryReceiver)).thenThrow(new RuntimeException("Can not find budget by given category id " + categoryReceiver));

        //then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> transferFundService.transferFundsBetweenCategories(categoryFounder, categoryReceiver, transferAmount));
        assertEquals("Can not find budget by given category id " + categoryReceiver, exception.getMessage());
    }

}
