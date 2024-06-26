package projects.adrian.homebudget.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import projects.adrian.homebudget.configuration.TestMockConfiguration;
import projects.adrian.homebudget.constants.ApplicationConstants;
import projects.adrian.homebudget.data.TestData;
import projects.adrian.homebudget.helper.MockMvcHelper;
import projects.adrian.homebudget.model.dto.UserDto;


import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static projects.adrian.homebudget.data.UserMockFactory.mockUserDto;
import static projects.adrian.homebudget.helper.TestUtils.getUrl;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = TestMockConfiguration.class)
@ActiveProfiles(value = "unittest")
class UserControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvcHelper mockMvcHelper;

    @PostConstruct
    public void postConstruct() {
        mockMvcHelper = new MockMvcHelper(objectMapper);
    }

    @SneakyThrows
    @Test
    void getAllUsers() {
        //given
        String url = getUrl(ApplicationConstants.USER_API_URL, port);
        //when
        MvcResult mvcResult = mockMvc.perform(get(url))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();
        List<UserDto> userDto = mockMvcHelper.mapResponse(List.class, mvcResult);
        //then
        Assertions.assertNotNull(userDto);
        Assertions.assertFalse(userDto.isEmpty());
//        Assertions.assertEquals(userDto.userId(), UserTestData.USER_ID);
    }

    @Test
    @SneakyThrows
    void getById() {
        //given
        String url = getUrl(ApplicationConstants.USER_API_URL, port) + "/{uuid}";
        //when
        MvcResult mvcResult = mockMvc.perform(get(url, TestData.USER_ID))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();
        UserDto userDto = mockMvcHelper.mapResponse(UserDto.class, mvcResult);
        //then
        Assertions.assertNotNull(userDto);
//        Assertions.assertEquals(userDto.userId(), UserTestData.USER_ID);

    }

    @SneakyThrows
    @Test
    void createUser() {
        //given
        String url = getUrl(ApplicationConstants.USER_API_URL, port);
        //when
        MvcResult mvcResult = mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mockMvcHelper.getContent(mockUserDto(null))))
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andReturn();
        UserDto userDto = mockMvcHelper.mapResponse(UserDto.class, mvcResult);
        //then
        Assertions.assertNotNull(userDto);
        Assertions.assertNotNull(userDto.userId());
    }

    @SneakyThrows
    @Test
    void updateUser() {
        //given
        String url = getUrl(ApplicationConstants.USER_API_URL, port) + "/{uuid}";
        //when
        MvcResult mvcResult = mockMvc.perform(put(url, TestData.USER_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mockMvcHelper.getContent(mockUserDto())))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();
        UserDto userDto = mockMvcHelper.mapResponse(UserDto.class, mvcResult);
        //then
        Assertions.assertNotNull(userDto);
        Assertions.assertEquals(userDto.userId(), TestData.USER_ID);
    }

    @SneakyThrows
    @Test
    void deleteUser() {
        //given
        String url = getUrl(ApplicationConstants.USER_API_URL, port) + "/{uuid}";
        //when
        MvcResult mvcResult = mockMvc.perform(delete(url, TestData.USER_ID))
                .andExpect(status().is(HttpStatus.NO_CONTENT.value()))
                .andReturn();
        UserDto userDto = mockMvcHelper.mapResponse(UserDto.class, mvcResult);
        //then
        Assertions.assertNull(userDto);
    }


}