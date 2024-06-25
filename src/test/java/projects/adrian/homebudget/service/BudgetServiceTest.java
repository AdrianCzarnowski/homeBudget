package projects.adrian.homebudget.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projects.adrian.homebudget.mapper.BudgetMapper;
import projects.adrian.homebudget.model.dto.BudgetDto;
import projects.adrian.homebudget.model.entity.BudgetEntity;
import projects.adrian.homebudget.repository.BudgetRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


public class BudgetServiceTest {

    private BudgetService budgetService;
    private BudgetMapper budgetMapper;
    private BudgetRepository budgetRepository;


    @BeforeEach
    void setUp() {
        budgetRepository = Mockito.mock(BudgetRepository.class);
        budgetMapper = Mockito.mock(BudgetMapper.class);
        budgetService = new BudgetService(budgetRepository, budgetMapper);
    }

    @Test
    void getAllBudgets_AllBudgetsFound_passed() {
        //given
        BudgetEntity budgetEntity = Mockito.mock(BudgetEntity.class);
        BudgetDto budgetDto = Mockito.mock(BudgetDto.class);

        Mockito.when(budgetRepository.findAll()).thenReturn(List.of(budgetEntity));
        Mockito.when(budgetMapper.toDto(any())).thenReturn(budgetDto);

        //when
        List<BudgetDto> result = budgetService.getAllBudgets();

        //then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(budgetDto, result.get(0));
    }

    @Test
    void findByBudgetId_BudgetFoundById_passed() {
        //given
        UUID budgetId = UUID.randomUUID();
        BudgetEntity budgetEntity = Mockito.mock(BudgetEntity.class);
        BudgetDto budgetDto = Mockito.mock(BudgetDto.class);

        Mockito.when(budgetRepository.findById(budgetId)).thenReturn(Optional.of(budgetEntity));
        Mockito.when(budgetMapper.toDto(any())).thenReturn(budgetDto);

        //when
        BudgetDto result = budgetService.findByBudgetId(budgetId);

        //then
        assertNotNull(result);
        assertEquals(budgetDto, result);
    }

    @Test
    void findByBudgetId_BudgetNotFoundById_throwException() {
        //given
        UUID budgetId = UUID.randomUUID();

        Mockito.when(budgetRepository.findById(budgetId)).thenReturn(Optional.empty());

        //when + then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> budgetService.findByBudgetId(budgetId));
        assertEquals("Can not find budget by given id " + budgetId, exception.getMessage());
    }

    @Test
    void saveBudget_BudgetSaved_passed() {
        //given
        BudgetEntity budgetEntity = Mockito.mock(BudgetEntity.class);
        BudgetDto budgetDto = Mockito.mock(BudgetDto.class);

        Mockito.when(budgetMapper.toEntity(any())).thenReturn(budgetEntity);
        Mockito.when(budgetRepository.save(any())).thenReturn(budgetEntity);
        Mockito.when(budgetMapper.toDto(any())).thenReturn(budgetDto);

        //when
        BudgetDto result = budgetService.saveBudget(budgetDto);

        //then
        assertNotNull(result);
        assertEquals(budgetDto, result);
    }

    @Test
    void deleteBudget_BudgetDeleted_passed() {
        //given
        UUID budgetId = UUID.randomUUID();

        //when
        budgetService.deleteBudget(budgetId);

        //then
        Mockito.verify(budgetRepository).deleteById(budgetId);
    }

    @Test
    void findByCategoryId_BudgetFoundByCategoryId_passed() {
        //given
        UUID categoryId = UUID.randomUUID();
        BudgetEntity budgetEntity = Mockito.mock(BudgetEntity.class);
        BudgetDto budgetDto = Mockito.mock(BudgetDto.class);

        Mockito.when(budgetRepository.findByCategoryCategoryId(categoryId)).thenReturn(Optional.of(budgetEntity));
        Mockito.when(budgetMapper.toDto(any())).thenReturn(budgetDto);

        //when
        BudgetDto result = budgetService.findByCategoryId(categoryId);

        //then
        assertNotNull(result);
        assertEquals(budgetDto, result);
    }

    @Test
    void findByCategoryId_BudgetNotFoundByCategoryId_throwException() {
        //given
        UUID categoryId = UUID.randomUUID();

        Mockito.when(budgetRepository.findByCategoryCategoryId(categoryId)).thenReturn(Optional.empty());

        //when + then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> budgetService.findByCategoryId(categoryId));
        assertEquals("Can not find budget by given category id " + categoryId, exception.getMessage());
    }

}
