package projects.adrian.homebudget.configuration;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import projects.adrian.homebudget.repository.UserRepository;

import java.util.Optional;

import static projects.adrian.homebudget.data.UserMockFactory.mockUserEntity;
import static projects.adrian.homebudget.data.UserTestData.USER_ID;

@TestConfiguration
public class TestMockConfiguration {

    @Primary
    @Bean
    public UserRepository mockUserRepository() {
        UserRepository repository = Mockito.mock(UserRepository.class);
        Mockito.when(repository.findById(USER_ID))
                .thenReturn(Optional.of(mockUserEntity()));
        return repository;
    }
}
