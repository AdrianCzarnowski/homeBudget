package projects.adrian.homebudget.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projects.adrian.homebudget.data.TestData;
import projects.adrian.homebudget.mapper.UserMapper;
import projects.adrian.homebudget.mapper.UserMapperImpl;
import projects.adrian.homebudget.model.dto.UserDto;
import projects.adrian.homebudget.model.entity.UserEntity;
import projects.adrian.homebudget.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

class UserServiceTest {
    //MethodName_StateUnderTest_ExpectedBehavior
    private UserService userService;
    private UserRepository userRepository;
    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userMapper = Mockito.mock(UserMapperImpl.class);
        userService = new UserService(userRepository, userMapper);
    }

    @Test
    void getAllUsers_AllUsersFound_passed() {
        //given

        UserEntity userEntity = Mockito.mock(UserEntity.class);
        UserDto userDto =  new UserDto(TestData.USER_ID, null, null, null, null, null, null, null, null, null);

        Mockito.when(userRepository.findAll())
                .thenReturn(List.of(userEntity));
        Mockito.when(userMapper.toDto(any()))
//                .thenCallRealMethod();
                .thenReturn(userDto);
        //when
        List<UserDto> result = userService.getAllUsers();
        //then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void findById_UserFoundById_passed() {
        //given
        Mockito.when(userRepository.findById(TestData.USER_ID))
                .thenReturn(Optional.of(new UserEntity()));
        Mockito.when(userMapper.toDto(any()))
                .thenReturn(new UserDto(null, null, null, null, null, null, null, null, null, null));
        //when
        UserDto result = userService.findById(TestData.USER_ID);
        //then
        Assertions.assertNotNull(result);
    }

    @Test
    void saveUser_UserSaved_passed() {
        //given
        UserEntity entity = new UserEntity();
        UserDto userDto = new UserDto(TestData.USER_ID, null, null, null, null, null, null, null, null, null);
        Mockito.when(userMapper.toEntity(any()))
                .thenReturn(entity);
        Mockito.when(userRepository.save(any()))
                .thenReturn(entity);
        Mockito.when(userMapper.toDto(entity))
                .thenReturn(userDto);
        //when
        UserDto result = userService.saveUser(userDto);
        //then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.userId(), TestData.USER_ID);
    }

    @Test
    void deleteUser_UserDeleted_passed() {
        //when
        userService.deleteUser(TestData.USER_ID);
        //then
        Mockito.verify(userRepository).deleteById(TestData.USER_ID);
    }
}