package projects.adrian.homebudget.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projects.adrian.homebudget.mapper.TransactionMapper;
import projects.adrian.homebudget.model.dto.TransactionDto;
import projects.adrian.homebudget.model.entity.TransactionEntity;
import projects.adrian.homebudget.repository.TransactionRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class TransactionServiceTest {
    private TransactionService transactionService;
    private TransactionMapper transactionMapper;
    private TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {
        transactionRepository = Mockito.mock(TransactionRepository.class);
        transactionMapper = Mockito.mock(TransactionMapper.class);
        transactionService = new TransactionService(transactionRepository, transactionMapper);
    }

    @Test
    void getAllTransactions_AllTransactionsFound_passed() {
        //given
        TransactionEntity transactionEntity = Mockito.mock(TransactionEntity.class);
        TransactionDto transactionDto = Mockito.mock(TransactionDto.class);

        Mockito.when(transactionRepository.findAll()).thenReturn(List.of(transactionEntity));
        Mockito.when(transactionMapper.toDto(any())).thenReturn(transactionDto);

        //when
        List<TransactionDto> result = transactionService.getAllTransactions();

        //then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(transactionDto, result.get(0));
    }

    @Test
    void getTransactionById_TransactionFoundById_passed() {
        //given
        UUID transactionId = UUID.randomUUID();
        TransactionEntity transactionEntity = Mockito.mock(TransactionEntity.class);
        TransactionDto transactionDto = Mockito.mock(TransactionDto.class);

        Mockito.when(transactionRepository.findById(transactionId)).thenReturn(Optional.of(transactionEntity));
        Mockito.when(transactionMapper.toDto(any())).thenReturn(transactionDto);

        //when
        TransactionDto result = transactionService.getTransactionById(transactionId);

        //then
        assertNotNull(result);
        assertEquals(transactionDto, result);
    }

    @Test
    void getTransactionById_TransactionNotFoundById_throwException() {
        //given
        UUID transactionId = UUID.randomUUID();

        Mockito.when(transactionRepository.findById(transactionId)).thenReturn(Optional.empty());

        //when + then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> transactionService.getTransactionById(transactionId));
        assertEquals("Can not find transaction by given id " + transactionId, exception.getMessage());
    }

    @Test
    void saveTransaction_TransactionSaved_passed() {
        //given
        TransactionEntity transactionEntity = Mockito.mock(TransactionEntity.class);
        TransactionDto transactionDto = Mockito.mock(TransactionDto.class);

        Mockito.when(transactionMapper.toEntity(any())).thenReturn(transactionEntity);
        Mockito.when(transactionRepository.save(any())).thenReturn(transactionEntity);
        Mockito.when(transactionMapper.toDto(any())).thenReturn(transactionDto);

        //when
        TransactionDto result = transactionService.saveTransaction(transactionDto);

        //then
        assertNotNull(result);
        assertEquals(transactionDto, result);
    }

    @Test
    void deleteTransaction_TransactionDeleted_passed() {
        //given
        UUID transactionId = UUID.randomUUID();

        //when
        transactionService.deleteTransaction(transactionId);

        //then
        Mockito.verify(transactionRepository).deleteById(transactionId);
    }

}
