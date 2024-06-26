package projects.adrian.homebudget.configuration;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import projects.adrian.homebudget.data.TransactionMockFactory;
import projects.adrian.homebudget.repository.TransactionRepository;
import projects.adrian.homebudget.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static projects.adrian.homebudget.data.TestData.TRANSACTION_ID;
import static projects.adrian.homebudget.data.UserMockFactory.mockUserEntity;
import static projects.adrian.homebudget.data.TestData.USER_ID;

@TestConfiguration
public class TestMockConfiguration {

    @Primary
    @Bean
    public UserRepository mockUserRepository() {
        UserRepository repository = Mockito.mock(UserRepository.class);
        Mockito.when(repository.findById(USER_ID))
                .thenReturn(Optional.of(mockUserEntity()));
        Mockito.when(repository.existsById(USER_ID))
                .thenReturn(true);
        Mockito.when(repository.save(any()))
                .thenReturn(mockUserEntity());
        Mockito.when(repository.findAll())
                .thenReturn(List.of(mockUserEntity()));
        return repository;
    }

    @Primary
    @Bean
    public TransactionRepository mockTransactionRepository(){
        TransactionRepository transactionRepository =  Mockito.mock(TransactionRepository.class);
        Mockito.when(transactionRepository.findById(TRANSACTION_ID))
                .thenReturn(Optional.of(TransactionMockFactory.mockTransactionEntity()));
        return transactionRepository;
    }
}
